package org.bessonov.calculator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.bessonov.calculator.adapters.CalculatorAdapter;

public class DisplayController {
    @FXML
    private Label subDisplayLabel;

    @FXML
    private Label displayLabel;

    @FXML
    public void initialize() {
        displayLabel.setText("0");
        subDisplayLabel.setText("");
    }

    public void setDisplay(String s) {
        displayLabel.setText(s);
    }

    public void setSubDisplay(String s) {
        subDisplayLabel.setText(s);
    }
}
