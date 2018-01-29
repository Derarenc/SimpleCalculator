package org.bessonov.calculator.model;

import java.util.function.DoubleBinaryOperator;

public enum BinaryOperation implements Operation {
    SUM("+", 1, (a, b) -> a + b),
    SUB("-", 1, (a, b) -> a - b),
    MUL("*", 2, (a, b) -> a * b),
    DIV("/", 2, (a, b) -> a / b);

    private String symbol;
    private int priority;
    private DoubleBinaryOperator doubleBinaryOperator;

    BinaryOperation(String symbol, int priority, DoubleBinaryOperator doubleBinaryOperator) {
        this.symbol = symbol;
        this.priority = priority;
        this.doubleBinaryOperator = doubleBinaryOperator;
    }

    public Number execute(Number... args) {
        double value = doubleBinaryOperator.applyAsDouble(
                args[0].getValue(),
                args[1].getValue()
        );
        return new Number(value);
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }
}
