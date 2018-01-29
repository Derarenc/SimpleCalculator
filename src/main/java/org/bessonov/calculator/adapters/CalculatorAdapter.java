package org.bessonov.calculator.adapters;

public interface CalculatorAdapter {
    void addNum(String value);

    void addPoint();

    void sum();

    void sub();

    void mul();

    void div();

    void clear();

    void openBracket();

    void closeBracket();

    double getResult();

    String getExpressionString();

    String getEnteringNumber();
}
