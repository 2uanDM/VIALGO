package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.image.*;
import main.java.Main;

public abstract class SortController implements Initializable {

    @FXML
    private Button menuActionButton;

    @FXML
    private ImageView menuActionArrow;

    @FXML
    private Button createArrayButton;

    @FXML
    private Button sortButton;

    @Override
    public void initialize(URL url, ResourceBundle rBundle) {
        /*
         * By wrapping the visibility setting code in a Platform.runLater() block,
         * you ensure that it is executed on the JavaFX Application Thread after
         * the FXML components have been fully initialized.
         */
        Platform.runLater(() -> {
            createArrayButton.setVisible(false);
            sortButton.setVisible(false);
        });
    }

    public void backToHomePage() {
        /*
         * This action method is used for back to home page button.
         * 
         * The .notStartApp() method tells the program that it backs
         * to the HomePage from a sorting view, so the startup sound will not play.
         */
        try {
            HomeController.notStartApp();
            Main.setRoot("./view/HomeView.fxml");
        } catch (Exception error) {
            System.out.println("Error when back to home page: " + error.getMessage());
        }
    }

    public void showMenuActionOptions() {
        /*
         * This action method is used for showing or closing the left side bar menu
         * options
         */
        boolean isVisible = createArrayButton.isVisible();
        // System.out.println(isVisible);
        createArrayButton.setVisible(!isVisible);
        sortButton.setVisible(!isVisible);
    }

}
