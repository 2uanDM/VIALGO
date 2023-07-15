package main.java.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.java.model.object.ColumnBar;

public class BubbleSortController extends SortController {

    @FXML
    private TextField firstTextField;

    @FXML
    private TextField secondTextField;

    public void sortButtonHandler() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int arrayLength = columns.size();

                for (int i = 0; i < arrayLength; i++) {
                    boolean swapped = false;

                    for (int j = 0; j < arrayLength - i - 1; j++) {
                        // Set Green for columns.get(j) and columns.get(j+1)
                        columns.get(j).setFill(Color.GREEN);
                        columns.get(j + 1).setFill(Color.GREEN);

                        ColumnBar currentCol = columns.get(j);
                        ColumnBar nextCol = columns.get(j + 1);

                        if (columns.get(j).getValue() > columns.get(j + 1).getValue()) {
                            swapped = true;

                            // Swap columns in terms of animation and inside columns array
                            currentCol.swap(nextCol, 0.3, columns, textValues);

                            // Add log
                            String log = String.format("Since %d > %d, swap element %d with the element %d",
                                    columns.get(j).getValue(),
                                    columns.get(j + 1).getValue(),
                                    columns.get(j).getValue(),
                                    columns.get(j + 1).getValue());

                            Platform.runLater(() -> sortExplainationTextField.setText(log));

                            // Change the current column to DEFAULT COLOR
                            currentCol.setFill(ColumnBar.DEFAULT_COLOR);
                        } else {
                            // Set Green for columns.get(j) and columns.get(j+1)
                            columns.get(j).setFill(Color.GREEN);
                            columns.get(j + 1).setFill(Color.GREEN);

                            // Add log
                            String log = String.format("Since %d <= %d, no swapping required",
                                    columns.get(j).getValue(),
                                    columns.get(j + 1).getValue());

                            Platform.runLater(() -> sortExplainationTextField.setText(log));

                            // Change the current column to DEFAULT COLOR
                            currentCol.setFill(ColumnBar.DEFAULT_COLOR);
                        }

                        // Wait 500ms before continuing to the next iteration
                        Thread.sleep(1000);
                    }

                    if (!swapped) {
                        Platform.runLater(() -> sortExplainationTextField.setText("List is sorted"));
                        break;
                    }
                }
                return null;
            }
        };

        new Thread(task).start();
    }

    public void swapping() {
        int index1 = Integer.parseInt(firstTextField.getText());
        int index2 = Integer.parseInt(secondTextField.getText());

        ColumnBar col1 = columns.get(index1);
        ColumnBar col2 = columns.get(index2);

        col1.swap(col2, 0.3, columns, textValues);
    }

}
