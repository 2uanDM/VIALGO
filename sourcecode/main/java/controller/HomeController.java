package main.java.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
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

}