package org.bessonov.calculator.adapters;

public class AdapterFactory {
    public static CalculatorAdapter getStatedCalculator() {
        return new StatedCalculator();
    }
}
