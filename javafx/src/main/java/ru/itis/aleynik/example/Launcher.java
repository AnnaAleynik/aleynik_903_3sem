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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Example");

        MainController controller = loader.getController();
        Scene scene = primaryStage.getScene();
        scene.setOnKeyPressed(controller.keyEventEventHandler);

        primaryStage.show();
    }
}
