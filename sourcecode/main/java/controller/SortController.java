package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import main.java.Main;
import main.java.model.object.ColumnBar;
import main.java.model.object.TextValue;
import main.java.model.vialgo_utils.SetVisibleUtils;
import main.java.model.vialgo_utils.InputParserUtils;

public abstract class SortController implements Initializable {

    protected boolean menuActionArrowPointRight;
    protected boolean sortExplainArrowPointLeft;
    protected boolean pseudoCodeArrowPointLeft;
    protected boolean isAnimating = false;
    protected int sortingSpeed;
    protected double animationTime;
    protected int intervalTime;
    public static int logStep = 1;

    @FXML
    protected AnchorPane anchorPane;

    @FXML
    protected ImageView aEqualsImageView;

    @FXML
    protected ImageView menuActionArrow;

    @FXML
    protected Label exceptionLabel;

    @FXML
    protected TextField enterArrayTextField;

    @FXML
    protected TextField sortExplainationTextField;

    @FXML
    protected TextArea pseudoCodeTextArea;

    @FXML
    protected ImageView pseudoCodeArrow;

    @FXML
    protected ImageView sortExplainArrow;

    @FXML
    protected HBox columnsHBox;

    @FXML
    protected Button createArrayButton;

    @FXML
    protected Button goButton;

    @FXML
    protected Button menuActionButton;

    @FXML
    protected Button randomArrayButton;

    @FXML
    protected Button sortButton;

    @FXML
    protected Button sortedArrayButton;

    @FXML
    protected Button sortExplainButton;

    @FXML
    protected Button pseudoCodeButton;

    @FXML
    protected Label speedLabel;

    @FXML
    protected Slider speedSlider;

    ArrayList<Node> menuActionOptionsChilds = new ArrayList<Node>();

    ArrayList<Node> createArrayButtonChilds = new ArrayList<Node>();

    ArrayList<Node> sortExplainButtonChilds = new ArrayList<Node>();

    ArrayList<Node> pseudoCodeButtonChilds = new ArrayList<Node>();

    SetVisibleUtils worker; // Executor for performing show or hide menu options animation

    ArrayList<ColumnBar> columns; // ArrayList store all columns created

    ArrayList<TextValue> textValues; // ArrayList store all Text values created

    public static Group textGroup = new Group(); // Group in Scene containing all Text values

    Random random = new Random();

    Thread sortingThread; // Thread for performing sorting algorithm
    Thread nextThread; // Thread for running next Button

    Thread createArray; // Thread for performing creating Array

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

        // Add textValues Group to scene
        anchorPane.getChildren().add(textGroup);

        // Set the CSS style for sortExplainationTextField
        sortExplainationTextField.setStyle("-fx-text-fill: white;");

        // Create new sorting thread
        sortingThread = new Thread();
        nextThread = new Thread();

        // Create new create Array thread
        createArray = new Thread();

        // Initial speed
        sortingSpeed = 1;
        speedLabel.setText(Integer.toString(sortingSpeed) + "X");
        System.out.println("Sorting speed" + " " + sortingSpeed);
        animationTime = 0.3; // In second
        intervalTime = (int) animationTime * 1000 + 1260 / sortingSpeed; // In milisecond

        // Add event listener for slider to change in real time
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int intNewValue = newValue.intValue();
            if (newValue.doubleValue() == intNewValue) {
                // Get new value
                sortingSpeed = (int) speedSlider.getValue();

                // Show on the label
                speedLabel.setText(Integer.toString(sortingSpeed) + "X");

                // Set the animation time
                animationTime = 0.3; // In second
                intervalTime = (int) animationTime * 1000 + 1260 / sortingSpeed; // In milisecond

                System.out.println("Sorting speed" + " " + sortingSpeed);
            }
        });

        // Create a random array and show MenuActionOptions
        Platform.runLater(() -> {
            generateRandomArray();
            showMenuActionOptions();
        });

    }

    /*----------------------------------------Action Handler---------------------------------------- */
    public void backToHomePage() {
        /*
         * This action method is used for back to home page button.
         * 
         * The .notStartApp() method tells the program that it backs
         * to the HomePage from a sorting view, so the startup sound will not be played.
         */

        // Interrupt the current sorting thread and wait for it to terminate
        if (sortingThread != null && sortingThread.isAlive()) {
            sortingThread.interrupt();
            try {
                sortingThread.join();
            } catch (InterruptedException e) {
                // Handle the exception if necessary
            }
        }

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

    public void generateRandomArray() {
        // Interrupt the current sorting thread and wait for it to terminate
        if (sortingThread != null && sortingThread.isAlive()) {
            sortingThread.interrupt();
            try {
                sortingThread.join();
            } catch (InterruptedException e) {
                // Handle the exception if necessary
            }
        }

        // Set sortExplainationTextField to blank
        sortExplainationTextField.setText("");

        // initialize logStep = 1 again
        logStep = 1;

        // Random number of elements
        int numberElements = random.nextInt(5, 20);

        // Generate a random array of integers
        ArrayList<Integer> arrayValue = new ArrayList<Integer>();
        for (int i = 1; i <= numberElements; ++i) {
            int randomValue = random.nextInt(1, 50);
            arrayValue.add(randomValue);
        }

        // Add ColumnBars to HBox with respect to these values
        addColumnBarToHBox(arrayValue);

        // Show array so that we can copy
        int[] intArray = new int[columns.size()];
        int i = 0;
        for (ColumnBar col : columns) {
            intArray[i] = col.getValue();
            i++;
        }
        String stringArray = Arrays.toString(intArray);
        stringArray = stringArray.replace('[', ' ');
        stringArray = stringArray.replace(']', ' ');
        enterArrayTextField.setText(stringArray);

    }

    public void generateSortedArray() {
        // Interrupt the current sorting thread and wait for it to terminate
        if (sortingThread != null && sortingThread.isAlive()) {
            sortingThread.interrupt();
            try {
                sortingThread.join();
            } catch (InterruptedException e) {
                // Handle the exception if necessary
            }
        }

        // Set sortExplainationTextField to blank
        sortExplainationTextField.setText("");

        // initialize logStep = 1 again
        logStep = 1;

        boolean isNonDecreasing = true;
        Random t = new Random();
        int numberElements = t.nextInt(5, 20);
        ArrayList<Integer> arrayVal = new ArrayList<Integer>();

        // Generate a random array of integers
        for (int i = 1; i <= numberElements; ++i) {
            int randomValue = t.nextInt(1, 50);
            arrayVal.add(randomValue);
        }

        // Sort
        if (isNonDecreasing) {
            Collections.sort(arrayVal);
        } else {
            Collections.sort(arrayVal, Comparator.reverseOrder());
        }

        // Add to HBox
        addColumnBarToHBox(arrayVal);

        // Show array so that we can copy
        int[] intArray = new int[columns.size()];
        int i = 0;
        for (ColumnBar col : columns) {
            intArray[i] = col.getValue();
            i++;
        }
        String stringArray = Arrays.toString(intArray);
        stringArray = stringArray.replace('[', ' ');
        stringArray = stringArray.replace(']', ' ');
        enterArrayTextField.setText(stringArray);
    }

    public void generateCustomArray() {
        // Interrupt the current sorting thread and wait for it to terminate
        if (sortingThread != null && sortingThread.isAlive()) {
            sortingThread.interrupt();
            try {
                sortingThread.join();
            } catch (InterruptedException e) {
                // Handle the exception if necessary
            }
        }

        // Set sortExplainationTextField to blank
        sortExplainationTextField.setText("");

        // initialize logStep = 1 again
        logStep = 1;

        String content = enterArrayTextField.getText();
        exceptionLabel.setText("");

        // Parse the string into ArrayList<Integer> with predefined types of exception
        InputParserUtils parser = new InputParserUtils(exceptionLabel, content);
        ArrayList<Integer> arrayVal = new ArrayList<Integer>();
        arrayVal = parser.getArrayValue();

        // Add ColumnBars to HBox with respect to these values
        addColumnBarToHBox(arrayVal);
    }

    private void addColumnBarToHBox(ArrayList<Integer> arrayValue) {
        columns = new ArrayList<ColumnBar>();
        textValues = new ArrayList<TextValue>();

        // Created list of ColumnBar object from list of integers
        for (int val : arrayValue) {
            ColumnBar newColumn = new ColumnBar(val);
            columns.add(newColumn);
        }

        // Add list of Columnbar to HBox
        columnsHBox.getChildren().setAll(columns);
        columnsHBox.layout();

        // Add xCoordinate and Text value with respect to AnchorPane for each ColumnBar
        for (ColumnBar col : columns) {
            double xCoordinate = col.localToScene(col.getBoundsInLocal()).getMinX();
            double yCoordinate = col.localToScene(col.getBoundsInLocal()).getMinY();
            col.setXCoordinate(xCoordinate);
            col.setYCoordinate(yCoordinate);

            // Add Text value to array
            col.setTextValue(new TextValue(col.getValue(), col));
            textValues.add(col.getTextValue());

        }

        // Set Children for textGroup
        textGroup.getChildren().setAll(textValues);
    }

    protected void sidePanelActionBeforeSorting() {
        // Close left panel
        worker = new SetVisibleUtils(this.menuActionOptionsChilds);
        worker.changeVisibleStatus(true, false, "translate");

        worker = new SetVisibleUtils(this.createArrayButtonChilds);
        worker.changeVisibleStatus(true, false, "fade");

        // Show all right panel
        worker = new SetVisibleUtils(this.sortExplainButtonChilds);
        worker.changeVisibleStatus(false, true, "translate");

        worker = new SetVisibleUtils(this.pseudoCodeButtonChilds);
        worker.changeVisibleStatus(false, true, "translate");

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

    public abstract void sortButtonHandler();
}