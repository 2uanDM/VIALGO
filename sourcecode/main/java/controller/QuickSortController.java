package main.java.controller;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.java.model.object.ColumnBar;

public class QuickSortController extends SortController {

    public void swapping() {
        System.out.println("clicked");

        Random random = new Random();
        int col1Index = random.nextInt(0, 5);
        int col2Index = random.nextInt(6, 10);

        ColumnBar col1 = (ColumnBar) columnsHBox.getChildren().get(col1Index);
        ColumnBar col2 = (ColumnBar) columnsHBox.getChildren().get(col2Index);

        col1.swap(col2, 0.3);
    }






    private double getComponentWidth(Node component) {
        if (component instanceof Button) {
            Button button = (Button) component;
            return button.getWidth();
        } else if (component instanceof TextField) {
            TextField textField = (TextField) component;
            return textField.getWidth();
        } else if (component instanceof TextArea) {
            TextArea textArea = (TextArea) component;
            return textArea.getWidth();
        } else {
            // Handle other component types if needed
            return 0.0;
        }
    }

    public void onAction(ActionEvent e) {
        if (e.getTarget() instanceof Node) {
            Node targetNode = (Node) e.getTarget();
            double width = getComponentWidth(targetNode);
            System.out.println("Width: " + width);
        }
    }



}
