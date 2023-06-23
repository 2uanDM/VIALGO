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
    public void start(Stage homeStage) throws Exception {
        Scene homeScene = new Scene(loadFXML("../java/view/HomeView.fxml"), 1280, 720);
        homeStage.setTitle("VIALGO");
        homeStage.setScene(homeScene);
        homeStage.show();
    }

    // This method is used for setting root of the current Stage
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
