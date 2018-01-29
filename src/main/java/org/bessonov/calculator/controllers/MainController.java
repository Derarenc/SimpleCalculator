package org.bessonov.calculator.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import org.bessonov.calculator.adapters.AdapterFactory;
import org.bessonov.calculator.adapters.CalculatorAdapter;

public class MainController {
    @FXML
    private Parent displayView;

    @FXML
    private Parent buttonsView;

    @FXML
    private DisplayController displayViewController;

    @FXML
    private ButtonsController buttonsViewController;

    private CalculatorAdapter adapter = AdapterFactory.getStatedCalculator();

    @FXML
    private void initialize() {
        buttonsViewController.setMainController(this);
    }

    public void addNum(String value) {
        adapter.addNum(value);
        displayViewController.setDisplay(adapter.getEnteringNumber());
        displayViewController.setSubDisplay(adapter.getExpressionString());
    }

    public void sum() {
        adapter.sum();
        displayViewController.setSubDisplay(adapter.getExpressionString());
        displayViewController.setDisplay("+");
    }

    public void sub() {
        adapter.sub();
        displayViewController.setSubDisplay(adapter.getExpressionString());
        displayViewController.setDisplay("-");
    }

    public void mul() {
        adapter.mul();
        displayViewController.setSubDisplay(adapter.getExpressionString());
        displayViewController.setDisplay("*");
    }

    public void div() {
        adapter.div();
        displayViewController.setSubDisplay(adapter.getExpressionString());
        displayViewController.setDisplay("/");
    }

    public void result() {
        double result = adapter.getResult();
        displayViewController.setSubDisplay(adapter.getExpressionString());
        displayViewController.setDisplay("= " + result);
    }

    public void clear() {
        adapter.clear();
        displayViewController.setSubDisplay(adapter.getExpressionString());
        displayViewController.setDisplay(adapter.getEnteringNumber());
    }

    public void addPoint() {
        adapter.addPoint();
        displayViewController.setSubDisplay(adapter.getExpressionString());
        displayViewController.setDisplay(adapter.getEnteringNumber());
    }

    public void openBracket() {
        adapter.openBracket();
        displayViewController.setSubDisplay(adapter.getExpressionString());
        displayViewController.setDisplay(adapter.getEnteringNumber());
    }

    public void closeBracket() {
        adapter.closeBracket();
        displayViewController.setSubDisplay(adapter.getExpressionString());
        displayViewController.setDisplay(adapter.getEnteringNumber());
    }
}
