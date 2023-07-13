package main.java.controller;

import javafx.fxml.FXML;
import java.util.Collections;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.java.model.object.ColumnBar;

public class BubbleSortController extends SortController {

    @FXML
    private TextField inputIndex1;

    @FXML
    private TextField inputIndex2;

    public void swapping() {

        int index1 = Integer.parseInt(inputIndex1.getText());
        int index2 = Integer.parseInt(inputIndex2.getText());

        System.out.println("col1: " + index1 + " col2: " + index2);

        // Perform animation

        ColumnBar col1 = columns.get(index1);
        ColumnBar col2 = columns.get(index2);

        col1.setFill(Color.GREEN);
        col2.setFill(Color.GREEN);

        col1.swap(col2, 0.3, columns);
    }

    public void swapInsideHBox() {
        int index1 = Integer.parseInt(inputIndex1.getText());
        int index2 = Integer.parseInt(inputIndex2.getText());

        Collections.swap(columns, index1, index2);

        // Update the position of the columns in the HBox
        for (int i = 0; i < columnsHBox.getChildren().size(); i++) {
            ColumnBar col = (ColumnBar) columnsHBox.getChildren().get(i);
            int colIndex = columns.indexOf(col);
            double newX = colIndex * (40 + SortController.HBOX_SPACING);
            col.setTranslateX(newX);
        }

        for (Node node : columnsHBox.getChildren()) {
            ColumnBar col = (ColumnBar) node;
            System.out.print(col.getValue() + " ");
        }

        System.out.println();
    }

}
