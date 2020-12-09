package ru.itis.aleynik.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button pooButton;
    @FXML
    private Circle player;
    @FXML
    private Label login;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pooButton.setOnAction(ev -> {
            login.setText(Integer.toString((int) Math.random()*100));
        });
    }
}
