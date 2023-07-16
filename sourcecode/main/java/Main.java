package main.java;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {
        try {
            scene = new Scene(loadFXML("./view/HomeView.fxml"), 1280, 720);
            // Set the title of the stage
            stage.setTitle("VIALGO");

            // Set the scene of the stage
            stage.setScene(scene);

            // Prevent the stage from being resized
            stage.setResizable(false);

            // Show the stage
            stage.show();
        }

        catch (IOException e) {
            System.out.println("Error loading HomeView.fxml: " + e.getMessage());
        }
    }

    // This method is used for setting root Node for the main scene
    public static void setRoot(String fxml) {
        try {
            scene.setRoot(loadFXML(fxml));
        }

        catch (IOException e) {
            System.err.println("Error loading " + fxml + ": " + e.getMessage());
        }
    }

    // This is a support method returning the root Node from an fxml file
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}