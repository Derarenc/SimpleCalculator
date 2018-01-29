package org.bessonov.calculator.model;

interface Operation extends PrioritizedMember {
    Number execute(Number ... args);

    String getSymbol();
}
