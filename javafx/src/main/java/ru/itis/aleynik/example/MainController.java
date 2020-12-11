package ru.itis.aleynik.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane pane;

    @FXML
    private Button pooButton;
    @FXML
    private Circle player;
    @FXML
    private Label login;

    public EventHandler<KeyEvent> keyEventEventHandler = event -> {
        if (event.getCode() == KeyCode.A) {
            player.setLayoutX(player.getLayoutX() - 5);
        }
        if (event.getCode() == KeyCode.D) {
            player.setLayoutX(player.getLayoutX() + 5);
        }
        if (event.getCode() == KeyCode.S) {
            player.setLayoutY(player.getLayoutY() - 5);
        }
        if (event.getCode() == KeyCode.W) {
            player.setLayoutY(player.getLayoutY() + 5);
        }
        if (event.getCode() == KeyCode.LEFT) {
            player.setLayoutX(player.getLayoutX() - 5);
        }
        if (event.getCode() == KeyCode.J) {
            Circle bullet = new Circle(player.getLayoutX(), player.getLayoutY(), 5, Color.BLACK);
            pane.getChildren().add(bullet);

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.005), animation -> {
                bullet.setLayoutX(bullet.getLayoutX() + 2);
            }));

            timeline.setCycleCount(100);
            timeline.play();
        }

    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pooButton.setOnAction(ev -> {
            login.setText("To shoot press J");
        });
    }
}
