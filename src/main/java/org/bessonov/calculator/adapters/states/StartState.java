package org.bessonov.calculator.adapters.states;

import org.bessonov.calculator.adapters.StateContext;

public class StartState extends CalculatorState {
    public StartState(StateContext statedCalculator) {
        super(statedCalculator);
    }

    @Override
    public void addNum(String value) {
        context.getBufferedNumber().setEmpty();
        super.addNum(value);
    }

    @Override
    public void addOperation(String operation) {
        context.pushBufferNumber();
        super.addOperation(operation);
    }

    @Override
    public void openBracket(String bracket) {
        context.getBufferedNumber().setEmpty();
        super.openBracket(bracket);
    }
}
