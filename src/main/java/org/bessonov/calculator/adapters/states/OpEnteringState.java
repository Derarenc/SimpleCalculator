package org.bessonov.calculator.adapters.states;

import org.bessonov.calculator.adapters.StateContext;

public class OpEnteringState extends CalculatorState {

    public OpEnteringState(StateContext statedCalculator) {
        super(statedCalculator);
    }

    @Override
    public void addOperation(String operation) {
        context.getExpression().removeLast();
        super.addOperation(operation);
    }

    @Override
    public void result() {
        context.getExpression().removeLast();
        super.result();
    }
}
