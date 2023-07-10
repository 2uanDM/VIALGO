package main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class QuickSortController extends SortController {

    private void setVisible(Node component, boolean status) {
        if (component instanceof Button) {
            Button button = (Button) component;
            button.setVisible(status);
        } else if (component instanceof TextField) {
            TextField textField = (TextField) component;
            textField.setVisible(status);
        } else if (component instanceof TextArea) {
            TextArea textArea = (TextArea) component;
            textArea.setVisible(status);
        } else if (component instanceof ImageView) {
            ImageView imageView = (ImageView) component;
            imageView.setVisible(status);
        }
    }

    public void onAction(ActionEvent e) {
        if (e.getTarget() instanceof Node) {
            Node targetNode = (Node) e.getTarget();
            setVisible(targetNode, false);
            System.out.println("done");
        }
    }
}
