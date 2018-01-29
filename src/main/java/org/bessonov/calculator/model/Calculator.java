package org.bessonov.calculator.model;

import java.util.*;

public class Calculator {
    private static Set<Operation> operations = new HashSet<>();

    static {
        operations.addAll(EnumSet.allOf(BinaryOperation.class));
    }

    public static double calculate(String expression) {
        LinkedList<Member> stack = toRpn(expression);
        stack = calculate(stack);
        if (stack.peek() == null) {
            return 0d;
        }
        if (!(stack.peek() instanceof Number)) {
            throw new NullPointerException("Something has gone wrong");
        }
        Number result = (Number) stack.peek();
        return result.getValue();
    }

    // Convert expression to reverse polish notation
    private static LinkedList<Member> toRpn(String expression) {
        LinkedList<Member> output = new LinkedList<>();
        LinkedList<PrioritizedMember> stack = new LinkedList<>();
        LinkedList<String> list = toLinkedList(expression.toCharArray());
        ListIterator<String> input = list.listIterator();
        StringBuilder bufferNumber = new StringBuilder();
        int bracketCounter = 0;
        Member lastOne = null;

        while (input.hasNext()) {
            String ch = input.next();
            if (tryToParse(ch) != null || ch.equals(".")) {
                bufferNumber.append(ch);
                continue;
            } else if (bufferNumber.length() > 0) {
                Number number = new Number(bufferNumber.toString());
                output.push(number);
                lastOne = number;
                bufferNumber.setLength(0);
                bufferNumber.trimToSize();
            }
            if (Bracket.OPEN.isBracket(ch)) {
                if (lastOne instanceof Number) {
                    input.previous();
                    ch = BinaryOperation.MUL.getSymbol();
                } else {
                    stack.push(Bracket.OPEN);
                    bracketCounter++;
                    continue;
                }
            }
            if (Bracket.CLOSE.isBracket(ch)) {
                while (!(stack.peek() instanceof Bracket)) {
                    if (stack.isEmpty()) {
                        throw new NullPointerException("open bracket was not found");
                    }
                    output.push(stack.pop());
                }
                bracketCounter--;
                stack.pop();
                if (isNextNumber(input) || isNextOpenBracket(input)) {
                    ch = BinaryOperation.MUL.getSymbol();
                } else {
                    continue;
                }
            }
            for (Operation op : operations) {
                if (op.getSymbol().equals(ch)) {
                    if (!stack.isEmpty() &&
                            stack.peek().getPriority() > op.getPriority()) {
                        output.push(stack.pop());
                    }
                    stack.push(op);
                    lastOne = op;
                    break;
                }
            }
        }
        if (bracketCounter != 0) {
            throw new NullPointerException("numbers of open and close brackets are different");
        }
        if (bufferNumber.length() > 0) {
            output.push(new Number(bufferNumber.toString()));
        }
        while (!stack.isEmpty()) {
            output.push(stack.pop());
        }
        return output;
    }

    private static boolean isNextNumber(ListIterator<String> iterator) {
        String next = getNextString(iterator);
        return next != null && next.matches("\\d+");
    }

    private static boolean isNextOpenBracket(ListIterator<String> iterator) {
        String next = getNextString(iterator);
        return Bracket.OPEN.isBracket(next);
    }

    private static String getNextString(ListIterator<String> iterator) {
        if (!iterator.hasNext()) {
            return null;
        }
        String next = iterator.next();
        iterator.previous();
        return next;
    }

    private static LinkedList<String> toLinkedList(char[] chars) {
        LinkedList<String> list = new LinkedList<>();
        for (char aChar : chars) {
            if (aChar != ' ') {
                list.add(String.valueOf(aChar));
            }
        }
        return list;
    }

    private static Double tryToParse(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static LinkedList<Member> calculate(LinkedList<Member> stack) {
        Member head = stack.peek();
        if (head instanceof Operation) {
            Operation op = (Operation) head;
            stack.pop();
            if (stack.peek() instanceof Operation) {
                calculate(stack);
            }
            if (op instanceof BinaryOperation) {
                Number right = (Number) stack.pop();
                if (stack.peek() instanceof Operation) {
                    calculate(stack);
                }
                if (!(stack.peek() instanceof Number)) {
                    throw new NullPointerException("missed operand");
                }
                Number left = (Number) stack.pop();
                stack.push(op.execute(left, right));
            }
        }
        return stack;
    }
}
