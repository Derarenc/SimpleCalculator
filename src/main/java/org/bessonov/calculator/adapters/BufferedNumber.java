package org.bessonov.calculator.adapters;

public class BufferedNumber {
    private StringBuilder value = new StringBuilder();

    BufferedNumber() {
        value.append("0");
    }

    public StringBuilder getValue() {
        return value;
    }

    public void setValue(StringBuilder value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = new StringBuilder(value);
    }

    public void setZero() {
        value.setLength(0);
        value.trimToSize();
        value.append("0");
    }

    public void setEmpty() {
        value.setLength(0);
        value.trimToSize();
    }

    public boolean isZero() {
        return value.toString().equals("0");
    }

    public boolean isEmpty() {
        return value.length() == 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
