package ru.itis.aleynik.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxml = "/fxml/Main.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Example");

        primaryStage.show();
    }
}
