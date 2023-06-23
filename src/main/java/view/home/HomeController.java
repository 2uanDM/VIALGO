package main.java.view.home;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.*;

public class HomeController implements Initializable {

    @FXML
    private Button aboutButton;

    @FXML
    private Button helpButton;

    @Override
    public void initialize(URL url, ResourceBundle rBundle) {
        // Welcome sound
        AudioClip welcomeSound = new AudioClip(
                this.getClass().getResource("../../../resources/sound/chime.wav").toString());
        welcomeSound.setVolume(0.5);
        welcomeSound.play();
    }

}