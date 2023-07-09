package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
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
    private Button testButton;

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

        // Intially, the arrowPointRight is true ~ point to the right
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

    public void createRectangle() {
        HBox centerHBox = new HBox();
        Rectangle r = new Rectangle();
        r.setX(50);
        r.setY(50);
        r.setWidth(200);
        r.setHeight(100);
        AnchorPane currentPane = (AnchorPane) testButton.getScene().getRoot();

        // Set alignment and position for centerHBox
        centerHBox.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(centerHBox, 0.0);
        AnchorPane.setBottomAnchor(centerHBox, 0.0);
        AnchorPane.setLeftAnchor(centerHBox, 0.0);
        AnchorPane.setRightAnchor(centerHBox, 0.0);

        // Add r to centerHBox
        centerHBox.getChildren().add(r);

        // Update the UI on the JavaFX application thread
        Platform.runLater(() -> {
            // Add centerHBox to currentPane
            currentPane.getChildren().add(centerHBox);
        });
    }

}
