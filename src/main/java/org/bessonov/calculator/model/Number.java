package org.bessonov.calculator.model;

class Number implements Member {
    private double value;

    Number(double value) {
        this.value = value;
    }

    Number(String value) {
        this.value = Double.parseDouble(value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
