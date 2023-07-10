package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
    private HBox columnsHBox;

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
        rotateTransition.setInterpolator(Interpolator.EASE_BOTH);
        rotateTransition.play();

    }

    public void createRectangle() {
        // Create a new rectangle
        Random t = new Random();
        Rectangle r = new Rectangle();
        r.setWidth(30);
        r.setHeight(t.nextInt(230));
        r.setFill(Color.rgb(t.nextInt(255), t.nextInt(255), t.nextInt(255)));

        // Set the initial position of the rectangle
        r.setTranslateX(-50); // Start the rectangle outside the visible area

        // Add the rectangle to the HBox
        columnsHBox.getChildren().add(r);
        columnsHBox.setSpacing(10);

        // Create a TranslateTransition for the rectangle
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), r);
        transition.setFromX(-50); // Start position
        transition.setToX(0); // End position

        // Play the animation
        transition.play();
    }

}
