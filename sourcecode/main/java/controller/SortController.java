package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.java.Main;

import main.java.model.vialgo_utils.SetVisibleUtils;

public abstract class SortController implements Initializable {

    private boolean menuActionArrowPointRight;
    private boolean sortExplainArrowPointLeft;
    private boolean pseudoCodeArrowPointLeft;

    @FXML
    private ImageView aEqualsImageView;

    @FXML
    private ImageView menuActionArrow;

    @FXML
    private TextField enterArrayTextField;

    @FXML
    private TextField sortExplainationTextField;

    @FXML
    private TextArea pseudoCodeTextArea;

    @FXML
    private ImageView pseudoCodeArrow;

    @FXML
    private ImageView sortExplainArrow;

    @FXML
    private HBox columnsHBox;

    @FXML
    private Button createArrayButton;

    @FXML
    private Button goButton;

    @FXML
    private Button menuActionButton;

    @FXML
    private Button randomArrayButton;

    @FXML
    private Button sortButton;

    @FXML
    private Button sortedArrayButton;

    @FXML
    private Button sortExplainButton;

    @FXML
    private Button pseudoCodeButton;

    ArrayList<Node> menuActionOptionsChilds = new ArrayList<Node>();

    ArrayList<Node> createArrayButtonChilds = new ArrayList<Node>();

    ArrayList<Node> sortExplainButtonChilds = new ArrayList<Node>();

    ArrayList<Node> pseudoCodeButtonChilds = new ArrayList<Node>();

    SetVisibleUtils worker;

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
            sortExplainationTextField.setVisible(false);
            pseudoCodeTextArea.setVisible(false);
        });

        // Prepare Component Hierachy for menuActionButton;
        menuActionOptionsChilds.add(createArrayButton);
        menuActionOptionsChilds.add(sortButton);

        // Prepare Component Hierachy for createArrayButton
        createArrayButtonChilds.add(randomArrayButton);
        createArrayButtonChilds.add(sortedArrayButton);
        createArrayButtonChilds.add(aEqualsImageView);
        createArrayButtonChilds.add(enterArrayTextField);
        createArrayButtonChilds.add(goButton);

        // Prepare Component Hierachy for sortExplainButton
        sortExplainButtonChilds.add(sortExplainationTextField);

        // Prepare Component Hierachy for pseudoCodeButton
        pseudoCodeButtonChilds.add(pseudoCodeTextArea);

        // Intially, the menuActionArrowPointRight is true ~ point to the right
        menuActionArrowPointRight = true;
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

        worker = new SetVisibleUtils(this.menuActionOptionsChilds);
        boolean isVisible = createArrayButton.isVisible();
        worker.changeVisibleStatus(true, !isVisible, "translate");

        if (isVisible) {
            // Hide second level MenuItems if their parent (MenuItems) also be hided
            worker = new SetVisibleUtils(this.createArrayButtonChilds);
            worker.changeVisibleStatus(true, !isVisible, "fade");
        }

        // Rotating the arrow 180 degrees when being clicked
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.3), menuActionArrow);
        if (menuActionArrowPointRight) {
            rotateTransition.setByAngle(180);
            menuActionArrowPointRight = false;
        } else {
            rotateTransition.setByAngle(-180);
            menuActionArrowPointRight = true;
        }
        rotateTransition.play();
    }

    public void showCreateArrayOptions() {
        /*
         * This action method is used for showing create array options
         * 
         */
        worker = new SetVisibleUtils(this.createArrayButtonChilds);
        if (goButton.isVisible() == false)
            worker.changeVisibleStatus(true, true, "fade");
    }

    public void showSortExplaination() {
        worker = new SetVisibleUtils(sortExplainButtonChilds);
        boolean isVisible = sortExplainationTextField.isVisible();
        worker.changeVisibleStatus(false, !isVisible, "translate");

        // Rotating the arrow 180 degrees when being clicked
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), sortExplainArrow);
        if (sortExplainArrowPointLeft) {
            rotateTransition.setByAngle(180);
            sortExplainArrowPointLeft = false;
        } else {
            rotateTransition.setByAngle(-180);
            sortExplainArrowPointLeft = true;
        }
        rotateTransition.play();
    }

    public void showPseudoCode() {
        worker = new SetVisibleUtils(pseudoCodeButtonChilds);
        boolean isVisible = pseudoCodeTextArea.isVisible();
        worker.changeVisibleStatus(false, !isVisible, "translate");

        // Rotating the arrow 180 degrees when being clicked
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), pseudoCodeArrow);
        if (pseudoCodeArrowPointLeft) {
            rotateTransition.setByAngle(180);
            pseudoCodeArrowPointLeft = false;
        } else {
            rotateTransition.setByAngle(-180);
            pseudoCodeArrowPointLeft = true;
        }
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
