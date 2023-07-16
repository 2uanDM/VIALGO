package main.java.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.Main;

public class HomeController implements Initializable {
    private static boolean startApp = true;

    @FXML
    private Button aboutButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button bubbleSortHover;

    @FXML
    private Button quickSortHover;

    @FXML
    private Button insertionSortHover;

    public void switchToBubbleSortView() {
        try {
            Main.setRoot("./view/BubbleSortView.fxml");
        } catch (Exception error) {
            System.out.println("ERROR: " + error.getMessage());
        }
    }

    public void switchToQuickSortView() {
        try {
            Main.setRoot("./view/QuickSortView.fxml");
        } catch (Exception error) {
            System.out.println("ERROR: " + error.getMessage());
        }
    }

    public void switchToInsertionSortView() {
        try {
            Main.setRoot("./view/InsertionSortView.fxml");
        } catch (Exception error) {
            System.out.println("ERROR: " + error.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rBundle) {
        // Welcome sound
        if (startApp) {
            AudioClip welcomeSound = new AudioClip(
                    this.getClass().getResource("../../resources/sound/chime.wav").toString());
            welcomeSound.setVolume(0.5);
            welcomeSound.play();
        }

    }

    public static void notStartApp() {
        HomeController.startApp = false;
    }

    public void showAbout() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("About");

        BorderPane borderPane = new BorderPane();
        TextArea textArea = new TextArea(
                "VIALGO - Sorting Visualizer\n\nThis program is our mini project product of class OOP.20222 - 131678\n\nTeam 17 including 4 members:\n\n- Đường Minh Quân - 20210710\n- Phạm Ngọc Quân – 20210704\n- Nguyễn Văn Quốc – 20214926\n- Bùi Minh Quang – 20214925\n\n© Copyright by VIALGO 2023");
        textArea.setEditable(false);
        textArea.setStyle("-fx-font-size: 14;");
        borderPane.setCenter(textArea);

        dialog.setScene(new Scene(borderPane, 400, 300));
        dialog.show();
    }

    public void showHelp() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Help");

        BorderPane borderPane = new BorderPane();
        TextArea textArea = new TextArea(
                "There are 3 types of sorting algorithms you can use:\n\n- Bubble sort\n- Insertion sort\n- Quick sort (First element as pivot)\n\nYou can create a random, sorted (non-decreasing), or custom array from input.\n\nNote that: as long as you provide valid integers in the range [1, 50], separated by commas, an array will be created.\n\nYou can change the sorting speed up to 7 times and recreate the original array to perform sorting again.\n\nWish you have fun with our product!");
        textArea.setEditable(false);
        textArea.setStyle("-fx-font-size: 14;");
        borderPane.setCenter(textArea);

        dialog.setScene(new Scene(borderPane, 400, 300));
        dialog.show();
    }

}