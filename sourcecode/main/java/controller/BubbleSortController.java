package main.java.controller;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.java.model.object.ColumnBar;
import main.java.model.vialgo_utils.AnimationUtils;

public class BubbleSortController extends SortController {
    @FXML
    private TextField firstTextField;

    @FXML
    private TextField secondTextField;

    private boolean isAnimating = false;

    public void sortButtonHandler() {
        Task<Void> sortingTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int arrayLength = columns.size();

                for (int i = 0; i < arrayLength; i++) {
                    boolean swapped = false;
                    for (int j = 0; j < arrayLength - i - 1; j++) {
                        ColumnBar currentCol = columns.get(j);
                        ColumnBar nextCol = columns.get(j + 1);

                        ArrayList<ColumnBar> changeColorColumns = new ArrayList<ColumnBar>();
                        changeColorColumns.add(currentCol);
                        changeColorColumns.add(nextCol);

                        // Set Green for currentCol and nextCol
                        AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, 0.3);

                        if (columns.get(j).getValue() > columns.get(j + 1).getValue()) {
                            swapped = true;

                            // Add log
                            String log = String.format("Since %d > %d, swap element %d with the element %d",
                                    columns.get(j).getValue(),
                                    columns.get(j + 1).getValue(),
                                    columns.get(j).getValue(),
                                    columns.get(j + 1).getValue());

                            Platform.runLater(() -> sortExplainationTextField.setText(log));
                            Thread.sleep(500);

                            // Swap columns in terms of animation and inside columns array
                            if (!isAnimating) {
                                isAnimating = true;
                                currentCol.swap(nextCol, 0.3, columns, textValues, () -> {
                                    isAnimating = false;
                                });
                            }

                            Thread.sleep(500);

                            // Change the current column to DEFAULT COLOR
                            AnimationUtils.fadeColor(changeColorColumns, ColumnBar.DEFAULT_COLOR, 0.3);
                        } else {
                            // Set Green for columns.get(j) and columns.get(j+1)
                            AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, 0.3);

                            // Add log
                            String log = String.format("Since %d <= %d, no swapping required",
                                    columns.get(j).getValue(),
                                    columns.get(j + 1).getValue());

                            Platform.runLater(() -> sortExplainationTextField.setText(log));

                            Thread.sleep(500);
                            // Change the current column to DEFAULT COLOR
                            AnimationUtils.fadeColor(changeColorColumns, ColumnBar.DEFAULT_COLOR, 0.3);
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

        sortingThread = new Thread(sortingTask);
        sortingThread.start();
    }

    public void swapping() {
        sortingThread.interrupt();
        int index1 = Integer.parseInt(firstTextField.getText());
        int index2 = Integer.parseInt(secondTextField.getText());

        ColumnBar col1 = columns.get(index1);
        ColumnBar col2 = columns.get(index2);

        if (!isAnimating) {
            isAnimating = true;
            col1.swap(col2, 0.3, columns, textValues, () -> {
                isAnimating = false;
            });
        }
    }

}
