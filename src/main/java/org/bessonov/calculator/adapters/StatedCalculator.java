package org.bessonov.calculator.adapters;

import org.bessonov.calculator.adapters.states.*;
import org.bessonov.calculator.model.Calculator;
import org.bessonov.calculator.model.*;

class StatedCalculator implements CalculatorAdapter, StateContext {
    private CalculatorState state;
    private CalculatorState startState = new StartState(this);
    private CalculatorState numEnteringState = new NumEnteringState(this);
    private CalculatorState opEnteringState = new OpEnteringState(this);
    private CalculatorState openBracketState = new OpenBracketState(this);
    private CalculatorState closeBracketState = new CloseBracketState(this);
    private CalculatorState resultState = new ResultState(this);

    private Expression expression = new Expression();
    private BufferedNumber number = new BufferedNumber();
    private int bracketCounter = 0;
    private Double savedResult = null;

    {
        state = startState;
    }

    @Override
    public void closeAllBrackets() {
        while (Bracket.OPEN.isBracket(expression.getLast())) {
            expression.removeLast();
            decreaseBracketCounter();
        }
        while (bracketCounter > 0) {
            state.closeBracket(Bracket.CLOSE.getSymbol());
        }
    }

    @Override
    public String getExpressionString() {
        return expression.toString();
    }

    @Override
    public String getEnteringNumber() {
        return number.toString();
    }

    @Override
    public void setStartState() {
        state = startState;
    }

    @Override
    public void setNumEnteringState() {
        state = numEnteringState;
    }

    @Override
    public void setOpEnteringState() {
        state = opEnteringState;
    }

    @Override
    public void setOpenBracketState() {
        state = openBracketState;
    }

    @Override
    public void setCloseBracketState() {
        state = closeBracketState;
    }

    @Override
    public void setResultState() {
        state = resultState;
    }

    @Override
    public void pushBufferNumber() {
        expression.add(number);
        number.setEmpty();
    }

    @Override
    public void appendExpression(String value) {
        expression.add(value);
    }

    @Override
    public void appendNumber(String value) {
        number.getValue().append(value);
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    public BufferedNumber getBufferedNumber() {
        return number;
    }

    @Override
    public int getBracketCounter() {
        return bracketCounter;
    }

    @Override
    public void increaseBracketCounter() {
        bracketCounter++;
    }

    @Override
    public void decreaseBracketCounter() {
        bracketCounter--;
    }

    @Override
    public void addNum(String value) {
        state.addNum(value);
    }

    @Override
    public void addPoint() {
        state.addPoint(".");
    }

    @Override
    public void sum() {
        state.addOperation(
                String.format(" %s ", BinaryOperation.SUM.getSymbol())
        );
    }

    @Override
    public void sub() {
        state.addOperation(
                String.format(" %s ", BinaryOperation.SUB.getSymbol())
        );
    }

    @Override
    public void mul() {
        state.addOperation(
                String.format(" %s ", BinaryOperation.MUL.getSymbol())
        );
    }

    @Override
    public void div() {
        state.addOperation(
                String.format(" %s ", BinaryOperation.DIV.getSymbol())
        );
    }

    @Override
    public double getResult() {
        state.result();
        savedResult = Calculator.calculate(expression.toString());
        return savedResult;
    }

    @Override
    public void clear() {
        expression.clear();
        number.setZero();
        bracketCounter = 0;
        savedResult = null;
        setStartState();
    }

    @Override
    public String getSavedResult() {
        if (savedResult == null) {
            return "null";
        }
        return savedResult.toString();
    }

    @Override
    public void openBracket() {
        state.openBracket(Bracket.OPEN.getSymbol());
    }

    @Override
    public void closeBracket() {
        state.closeBracket(Bracket.CLOSE.getSymbol());
    }

}
