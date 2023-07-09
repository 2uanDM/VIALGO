package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import main.java.Main;

public abstract class SortController implements Initializable {

    private boolean arrowPointRight;

    @FXML
    private Button menuActionButton;

    @FXML
    private ImageView menuActionArrow;

    @FXML
    private Button createArrayButton;

    @FXML
    private Button sortButton;

    @FXML
    private VBox parentController;

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

        // Intially, the arrowPosition is true ~ point to the right
        arrowPointRight = true;
    }

    public void backToHomePage() {
        /*
         * This action method is used for back to home page button.
         * 
         * The .notStartApp() method tells the program that it backs
         * to the HomePage from a sorting view, so the startup sound will not be played.
         */
        try {
            HomeController.notStartApp();
            Main.setRoot("./view/HomeView.fxml");
        } catch (Exception error) {
            System.out.println("Error when back to home page: " + error.getMessage());
        }
    }

    public void clickedShowMenuActionOptions() {
        /*
         * This action method is used for showing or closing the left side bar menu
         * options
         */

        // Set visible for the first level MenuItems
        boolean isVisible = createArrayButton.isVisible();
        createArrayButton.setVisible(!isVisible);
        sortButton.setVisible(!isVisible);

        // Rotating the arrow 180 degrees when being clicked
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.3), menuActionArrow);
        if (arrowPointRight) {
            rotateTransition.setByAngle(180);
            arrowPointRight = false;
        } else {
            rotateTransition.setByAngle(-180);
            arrowPointRight = true;
        }
        rotateTransition.play();
    }


}
