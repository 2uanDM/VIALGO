package main.java.view.home;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class HomeController implements Initializable {

    @FXML
    private TextField taskEntry;

    @FXML
    private ListView<String> taskList;

    // Name of the file path
    private String filePath = "output.txt";

    // Create a new file
    private File data = new File(filePath);

    public void AddTask(ActionEvent e) {
        String textEntry = taskEntry.getText();

        if (!textEntry.equals("")) {
            taskList.getItems().add(textEntry);
            taskEntry.setText("");
        } else {
            System.out.println("No input");
        }

    }

    public void DeleteTask(ActionEvent e) {
        String selected = taskList.getSelectionModel().getSelectedItem();

        if (selected != null) {
            taskList.getItems().remove(selected);
        } else {
            System.out.println("No selected");
        }
    }

    public void Exit(ActionEvent e) throws IOException {

        List<String> savedList = taskList.getItems();

        try {
            // The writer of java
            FileWriter writer = new FileWriter(filePath);

            for (String text : savedList) {
                text += "\n";
                writer.write(text);
            }

            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        System.exit(0);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {
            Scanner scanner = new Scanner(data);

            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                taskList.getItems().add(text);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}