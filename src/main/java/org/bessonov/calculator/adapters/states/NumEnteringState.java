package org.bessonov.calculator.adapters.states;

import org.bessonov.calculator.adapters.StateContext;

public class NumEnteringState extends CalculatorState {
    public NumEnteringState(StateContext statedCalculator) {
        super(statedCalculator);
    }

    @Override
    public void addOperation(String operation) {
        context.pushBufferNumber();
        super.addOperation(operation);
    }

    @Override
    public void result() {
        context.pushBufferNumber();
        super.result();
    }

    @Override
    public void openBracket(String bracket) {
        super.openBracket(bracket);
        context.pushBufferNumber();
    }

    @Override
    public void closeBracket(String bracket) {
        context.pushBufferNumber();
        super.closeBracket(bracket);
    }
}
