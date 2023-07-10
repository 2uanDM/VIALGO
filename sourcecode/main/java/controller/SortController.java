package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.java.Main;

public abstract class SortController implements Initializable {

    private boolean arrowPointRight;

    @FXML
    private ImageView aEqualsImageView;

    @FXML
    private HBox columnsHBox;

    @FXML
    private Button createArrayButton;

    @FXML
    private TextField enterArrayTextField;

    @FXML
    private Button goButton;

    @FXML
    private ImageView menuActionArrow;

    @FXML
    private Button menuActionButton;

    @FXML
    private Button randomArrayButton;

    @FXML
    private Button sortButton;

    @FXML
    private Button sortedArrayButton;

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
            randomArrayButton.setVisible(false);
            sortedArrayButton.setVisible(false);
            aEqualsImageView.setVisible(false);
            enterArrayTextField.setVisible(false);
            goButton.setVisible(false);
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

    public void showMenuActionOptions() {
        /*
         * This action method is used for showing or closing the left side bar menu
         * options
         */

        TranslateTransition createArrayButtonAnimation, sortButtonAnimation;

        // Set visible for the first level MenuItems
        boolean isVisible = createArrayButton.isVisible();
        if (isVisible == true) {

            // Create hide animation for first level MenuItems
            createArrayButtonAnimation = createTranslateTransition(createArrayButton, false);
            sortButtonAnimation = createTranslateTransition(sortButton, false);

            // Still hide second level MenuItems Until the createArrayButton is clicked
            randomArrayButton.setVisible(false);
            sortedArrayButton.setVisible(false);
            aEqualsImageView.setVisible(false);
            enterArrayTextField.setVisible(false);
            goButton.setVisible(false);
        } else {
            // Create show animation for first level MenuItems
            createArrayButton.setVisible(!isVisible);
            sortButton.setVisible(!isVisible);
            createArrayButtonAnimation = createTranslateTransition(createArrayButton, true);
            sortButtonAnimation = createTranslateTransition(sortButton, true);
        }

        // Create a ParallelTransition to play all wipe transitions simultaneously
        ParallelTransition parallelTransition = new ParallelTransition(
                createArrayButtonAnimation,
                sortButtonAnimation);

        // Rotating the arrow 180 degrees when being clicked
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.3), menuActionArrow);
        if (arrowPointRight) {
            rotateTransition.setByAngle(180);
            arrowPointRight = false;
        } else {
            rotateTransition.setByAngle(-180);
            arrowPointRight = true;
        }

        // Only when the hide animation finished, those button will be set not visible
        if (isVisible == true) {
            parallelTransition.setOnFinished(event -> {
                createArrayButton.setVisible(false);
                sortButton.setVisible(false);
            });
        }

        parallelTransition.play();
        rotateTransition.play();
    }

    public void showCreateArrayOptions() {
        /*
         * This action method is used for showing create array options
         * 
         */

        // Create a FadeTransition for each component
        FadeTransition randomArrayFade = createFadeTransition(randomArrayButton);
        FadeTransition sortedArrayFade = createFadeTransition(sortedArrayButton);
        FadeTransition aEqualsFade = createFadeTransition(aEqualsImageView);
        FadeTransition enterArrayFade = createFadeTransition(enterArrayTextField);
        FadeTransition goButtonFade = createFadeTransition(goButton);

        // Create a ParallelTransition to play all fade transitions simultaneously
        ParallelTransition parallelTransition = new ParallelTransition(
                randomArrayFade,
                sortedArrayFade,
                aEqualsFade,
                enterArrayFade,
                goButtonFade);

        // Show the components by making them visible
        randomArrayButton.setVisible(true);
        sortedArrayButton.setVisible(true);
        aEqualsImageView.setVisible(true);
        enterArrayTextField.setVisible(true);
        goButton.setVisible(true);

        // Play the fade-in animation
        parallelTransition.play();
    }

    private FadeTransition createFadeTransition(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.2), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        return fadeTransition;
    }

    private TranslateTransition createTranslateTransition(Node node, boolean leftToRight) {
        int createArrayButtonWidth = 110;
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.3), node);
        double fromX = leftToRight ? -createArrayButtonWidth : 0;
        double toX = leftToRight ? 0 : -createArrayButtonWidth - 50;
        translateTransition.setFromX(fromX);
        translateTransition.setToX(toX);
        translateTransition.setInterpolator(Interpolator.EASE_OUT);
        return translateTransition;
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
