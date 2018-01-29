package org.bessonov.calculator.adapters.states;

import org.bessonov.calculator.adapters.StateContext;

public class OpenBracketState extends CalculatorState {
    public OpenBracketState(StateContext statedCalculator) {
        super(statedCalculator);
    }

    @Override
    public void closeBracket(String bracket) {
        // empty
    }

    @Override
    public void result() {
        context.setStartState();
        super.result();
    }
}
