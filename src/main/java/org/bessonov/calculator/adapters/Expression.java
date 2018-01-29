package org.bessonov.calculator.adapters;

import java.util.LinkedList;

public class Expression extends LinkedList<String> {
    public boolean add(BufferedNumber number) {
        this.add(number.toString());
        return true;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        this.forEach(out::append);
        return out.toString();
    }
}
