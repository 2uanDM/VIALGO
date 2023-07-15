package main.java.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.java.model.object.ColumnBar;
import main.java.model.vialgo_utils.AnimationUtils;
import main.java.model.sorting_algo.BubbleSort;

public class BubbleSortController extends SortController {
    @FXML
    private TextField firstTextField;

    @FXML
    private TextField secondTextField;

    private boolean isAnimating = false;

    private static int stepCount = 0;

    public void sortButtonHandler() {

        Task<Void> newTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Code de chay animation va sorting algorithm (Cho vong for trong nay va
                // .swap())
                // Neu muon them log vao thi se chay doan sau
                System.out.println("START");
                int arrLength = columns.size();
                int[] intArray = new int[arrLength];
                int index = 0;
                for (ColumnBar column : columns) {
                    intArray[index] = column.getValue();
                    index++;
                }
                BubbleSort obj = new BubbleSort(intArray);
                obj.sort();

                int[][] pointerLog = obj.getPointerLog();

                for (int stepCount = 0; stepCount < pointerLog.length; stepCount++) {
                    int index1 = pointerLog[stepCount][0];
                    int index2 = pointerLog[stepCount][1];
                    if (index1 == 0 && index2 == 0) {
                        continue;
                    }
                    ArrayList<ColumnBar> changeColorColumns = new ArrayList<ColumnBar>();

                    ColumnBar col1 = columns.get(index1);
                    ColumnBar col2 = columns.get(index2);

                    changeColorColumns.add(col1);
                    changeColorColumns.add(col2);

                    if (pointerLog[stepCount][2] == 1) {
                        AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, 0.3);

                        Platform.runLater(() -> sortExplainationTextField
                                .setText(String.format("We swap column %d with the column %d.", index1, index2)));

                        Thread.sleep(700);
                        if (!isAnimating) {
                            isAnimating = true;
                            col1.swap(col2, 0.3, columns, textValues, () -> {
                                isAnimating = false;

                            });
                        }

                    } else {
                        AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, 0.3);

                        Platform.runLater(() -> sortExplainationTextField
                                .setText("Do not swap"));
                        Thread.sleep(700);
                    }
                    Thread.sleep(700);
                    for (ColumnBar col : changeColorColumns) {
                        col.setFill(ColumnBar.DEFAULT_COLOR);
                    }
                    // AnimationUtils.fadeColor(changeColorColumns, ColumnBar.DEFAULT_COLOR, 0.3);
                }

                Platform.runLater(() -> sortExplainationTextField.setText("In ra log"));
                Thread.sleep(1000);

                return null;
            }
        };

        sortingThread = new Thread(newTask);
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
