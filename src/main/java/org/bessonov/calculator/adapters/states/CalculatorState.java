package org.bessonov.calculator.adapters.states;

import org.bessonov.calculator.adapters.StateContext;

public abstract class CalculatorState {
    StateContext context;

    CalculatorState(StateContext context) {
        this.context = context;
    }

    public void addNum(String value) {
        context.appendNumber(value);
        context.setNumEnteringState();
    }

    public void addOperation(String operation) {
        context.appendExpression(operation);
        context.setOpEnteringState();
    }

    public void openBracket(String bracket) {
        context.appendExpression(bracket);
        context.increaseBracketCounter();
        context.setOpenBracketState();
    }

    public void closeBracket(String bracket) {
        if (context.getBracketCounter() <= 0) {
            return;
        }
        context.appendExpression(bracket);
        context.decreaseBracketCounter();
        context.setCloseBracketState();
    }

    public void result() {
        context.closeAllBrackets();
        context.setResultState();
    }

    public void addPoint(String value) {
        if (context.getBufferedNumber().getValue().indexOf(value) > -1) {
            System.err.println("Multiple points detected");
            return;
        }
        if (context.getBufferedNumber().isEmpty()) {
            context.getBufferedNumber().setZero();
        }
        context.appendNumber(value);
        context.setNumEnteringState();
    }
}
