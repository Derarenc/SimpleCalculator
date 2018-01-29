package org.bessonov.calculator.adapters;

public interface StateContext {
     void setStartState();

     void setNumEnteringState();

     void setOpEnteringState();

     void setOpenBracketState();

     void setCloseBracketState();

     void setResultState();

     Expression getExpression();

     BufferedNumber getBufferedNumber();

     int getBracketCounter();

     String getSavedResult();

     void increaseBracketCounter();

     void decreaseBracketCounter();

     void pushBufferNumber();

     void appendExpression(String value);

     void appendNumber(String value);

     void closeAllBrackets();

     void clear();
}
