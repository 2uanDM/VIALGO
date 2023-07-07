package main.java.controller;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import main.java.Main;

public abstract class SortController {

    @FXML
    private Button backButton;

    public void backToHomePage(ActionEvent e) {
        try {
            HomeController.notStartApp();
            Main.setRoot("./view/HomeView.fxml");
        } catch (Exception error) {
            System.out.println("Error when back to home page: " + error.getMessage());
        }
    }
}
