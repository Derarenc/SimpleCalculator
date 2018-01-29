package org.bessonov.calculator.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CalculatorTest {
    private Map<String, Double> expressions = new HashMap<String, Double>() {{
        put("2 + 9 / 3 - 25", -20d);
        put("2 + 9 / (3 - 25)", 1.59d);
        put("2 + 9 / (3 * (25 - 10))", 2.2d);
    }};

    private void calculate(String Expression, double expected) {
        double calculated = Calculator.calculate(Expression);
        try {
            assertEquals(expected, calculated, 0.01);
        } catch (AssertionError e) {
            System.err.println("Expression: " + Expression);
            throw e;
        }
    }

    @Test
    public void sumTest() {
        calculate("1 + 2", 3);
    }

    @Test
    public void subTest() {
        calculate("3 - 2", 1);
    }

    @Test
    public void mulTest() {
        calculate("2 * 3", 6);
    }

    @Test
    public void divTest() {
        calculate("9 / 3", 3);
    }

    @Test
    public void calculateTest() {
        expressions.forEach(this::calculate);
    }

    @Test
    public void nullTest() {
        calculate("", 0);
    }

    @Test(expected = NullPointerException.class)
    public void unfinishedExpressionTest() {
        calculate("2 + ", 0);
    }

    @Test(expected = NumberFormatException.class)
    public void twoPointTest() {
        calculate("2..0", 0d);
    }

    @Test(expected = NumberFormatException.class)
    public void alonePointTest() {
        calculate(". + 4", 0d);
    }

    @Test(expected = NullPointerException.class)
    public void differentNumbersOfBracketsTest() {
        calculate("2 * (3 + 5 / (1 + 4)", 0d);
    }

    @Test
    public void emptyBracketsTest1() {
        calculate("()", 0d);
    }

    @Test(expected = NullPointerException.class)
    public void emptyBracketsTest2() {
        calculate("() + 3", 0d);
    }

    @Test
    public void numberBeforeBracketTest() {
        calculate("3 ( 7 + 1 ) ", 24d);
    }

    @Test
    public void numberAfterBracketTest() {
        calculate("( 7 + 1 ) 3", 24d);
    }

    @Test
    public void bracketMulBracketTest() {
        calculate("(5 + 2)(2 + 2)", 28d);
    }
}