package org.bessonov.calculator.model;

import java.util.HashSet;
import java.util.Set;

public enum Bracket implements PrioritizedMember {
    OPEN("("),
    CLOSE(")");

    private String symbol;

    Bracket(String symbol) {
        this.symbol = symbol;
    }

    public int getPriority() {
        return 0;
    }

    public boolean isBracket(String s) {
        return symbol.equals(s);
    }

    public String getSymbol() {
        return symbol;
    }
}
