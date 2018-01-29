package org.bessonov.calculator.adapters.states;

import org.bessonov.calculator.adapters.StateContext;

public class ResultState extends CalculatorState {
    public ResultState(StateContext statedCalculator) {
        super(statedCalculator);
    }

    @Override
    public void addNum(String value) {
        context.clear();
        context.getBufferedNumber().setEmpty();
        super.addNum(value);
    }

    @Override
    public void addOperation(String operation) {
        String buffer = context.getSavedResult();
        context.clear();
        context.getBufferedNumber().setEmpty();
        context.appendExpression(buffer);
        super.addOperation(operation);
    }

    @Override
    public void addPoint(String value) {
        context.clear();
        context.getBufferedNumber().setZero();
        super.addPoint(value);
    }

    @Override
    public void openBracket(String bracket) {
        String buffer = context.getSavedResult();
        context.clear();
        context.getBufferedNumber().setValue(buffer);
        super.openBracket(bracket);
        context.pushBufferNumber();
    }
}
