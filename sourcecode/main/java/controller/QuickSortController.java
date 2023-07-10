package main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class QuickSortController extends SortController {

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
