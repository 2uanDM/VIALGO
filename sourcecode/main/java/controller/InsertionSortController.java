package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.java.model.vialgo_utils.AnimationUtils;

public class InsertionSortController extends SortController {

    @FXML
    private TextField firstTextField;

    @FXML
    private TextField secondTextField;

    @Override
    public void sortButtonHandler() {
        // Prevent many sort tasks run concurrently
        if (sortingThread.isAlive()) {
            return;
        }

        // Close all left panel and show all right panel
        sidePanelActionBeforeSorting();
    }

    public void testDown() {
        int index1 = Integer.parseInt(firstTextField.getText());
        // int index2 = Integer.parseInt(secondTextField.getText());

        if (!isAnimating) {
            isAnimating = true;
            AnimationUtils.fadeColor(columns.get(index1), Color.rgb(220, 20, 60), 0.1);
            AnimationUtils.moveVertical(columns.get(index1), "down", 0.25, () -> {
                isAnimating = false;
            });
        }

    }

    public void testUp() {
        int index1 = Integer.parseInt(firstTextField.getText());
        // int index2 = Integer.parseInt(secondTextField.getText());

        if (!isAnimating) {
            isAnimating = true;
            AnimationUtils.moveVertical(columns.get(index1), "up", 0.25, () -> {
                isAnimating = false;
            });
        }

    }

    public void testLeft() {
        int index1 = Integer.parseInt(firstTextField.getText());
        // int index2 = Integer.parseInt(secondTextField.getText());

        if (!isAnimating) {
            isAnimating = true;
            AnimationUtils.moveHorizontal(columns.get(index1), "left", 0.3, () -> {
                isAnimating = false;
            });
        }

    }

    public void testRight() {
        int index1 = Integer.parseInt(firstTextField.getText());
        // int index2 = Integer.parseInt(secondTextField.getText());

        if (!isAnimating) {
            isAnimating = true;
            AnimationUtils.moveHorizontal(columns.get(index1), "right", 0.3, () -> {
                isAnimating = false;
            });
        }

    }

}
