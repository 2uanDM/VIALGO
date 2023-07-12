package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import main.java.Main;
import main.java.model.object.ColumnBar;
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
        sortExplainArrowPointLeft = true;
        pseudoCodeArrowPointLeft = true;

        // Spacing for columns in HBox
        columnsHBox.setSpacing(10);
    }

    /*----------------------------------------Action Handler---------------------------------------- */
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
            rotateTransition.setByAngle(-180);
            sortExplainArrowPointLeft = false;
        } else {
            rotateTransition.setByAngle(180);
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
            rotateTransition.setByAngle(-180);
            pseudoCodeArrowPointLeft = false;
        } else {
            rotateTransition.setByAngle(180);
            pseudoCodeArrowPointLeft = true;
        }
        rotateTransition.play();
    }

    public void swapping() {
        System.out.println("clicked");

        Random random = new Random();
        int col1Index = random.nextInt(0, 5);
        int col2Index = random.nextInt(6, 10);

        ColumnBar col1 = (ColumnBar) columnsHBox.getChildren().get(col1Index);
        ColumnBar col2 = (ColumnBar) columnsHBox.getChildren().get(col2Index);

        col1.swap(col2, 0.3);
    }

    public void generateRandomArray() {
        columnsHBox.getChildren().clear();
        Random t = new Random();
        int numberElements = t.nextInt(5, 20);
        ArrayList<Integer> arrayVal = new ArrayList<Integer>();

        // Generate a random array of integers
        for (int i = 1; i <= numberElements; ++i) {
            int randomValue = t.nextInt(1, 50);
            arrayVal.add(randomValue);
        }

        // Add ColumnBars to HBox with respect to these values
        for (int val : arrayVal) {
            ColumnBar newColumn = new ColumnBar(val);
            columnsHBox.getChildren().add(newColumn);
        }

        // Update HBox Layout
        columnsHBox.layout();
    }

    public void generateSortedArray() {
        // Quốc code phần này nhé
    }

    public void generateCustomArray() {
        String content = enterArrayTextField.getText();
        System.out.println(content);
    }
}
