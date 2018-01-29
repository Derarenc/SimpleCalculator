package org.bessonov.calculator.apps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bessonov.calculator.controllers.MainController;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private Parent root;
    private final String MAIN_VIEW = "/views/mainView.fxml";

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Calculator");
        initRootLayout();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(MAIN_VIEW));
            root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/style.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
