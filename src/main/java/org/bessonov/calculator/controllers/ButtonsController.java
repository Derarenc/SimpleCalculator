package org.bessonov.calculator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.bessonov.calculator.adapters.CalculatorAdapter;

import java.util.HashSet;
import java.util.Set;

public class ButtonsController extends ButtonsFxml {
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        Set<Button> numButtons = new HashSet<Button>() {{
            add(numButton0);
            add(numButton1);
            add(numButton2);
            add(numButton3);
            add(numButton4);
            add(numButton5);
            add(numButton6);
            add(numButton7);
            add(numButton8);
            add(numButton9);
        }};
        numButtons.forEach(button -> {
            button.setOnAction(event -> mainController.addNum(button.getText()));
            button.getStyleClass().add("num-button");
        });
        sumButton.setOnAction(event -> mainController.sum());
        subButton.setOnAction(event -> mainController.sub());
        mulButton.setOnAction(event -> mainController.mul());
        divButton.setOnAction(event -> mainController.div());
        resultButton.setOnAction(event -> mainController.result());
        clearButton.setOnAction(event -> mainController.clear());
        dotButton.setOnAction(event -> mainController.addPoint());
        lBracketButton.setOnAction(event -> mainController.openBracket());
        rBracketButton.setOnAction(event -> mainController.closeBracket());
    }
}
