package main.java.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.java.model.object.ColumnBar;
import main.java.model.sorting_algo.QuickSort;
import main.java.model.vialgo_utils.AnimationUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSortController extends SortController {
    @FXML
    private TextField firstTextField;

    @FXML
    private TextField secondTextField;

    private boolean isAnimating = false;
    private ColumnBar pivotColumn; // Biến để theo dõi cột pivot

    public void sortButtonHandler() {
        if (sortingThread.isAlive()) {
            return;
        }

        sidePanelActionBeforeSorting();

        Task<Void> newTask = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                int arrLength = columns.size();
                int[] intArray = new int[arrLength];
                int index = 0;
                for (ColumnBar column : columns) {
                    intArray[index] = column.getValue();
                    index++;
                }
                QuickSort obj = new QuickSort(intArray);

                obj.sort();

                int[][] pointerLog = obj.getPointerLog();

                Thread.sleep(1500);

                for (int stepCount = 0; stepCount < pointerLog.length; stepCount++) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
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

                    // Lấy cột pivot
                    ColumnBar pivot = columns.get(obj.getPivotIndex());
                    AnimationUtils.fadeColor(pivot, Color.YELLOW, 0.3);

                    if (pointerLog[stepCount][2] == 1) {
                        AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, 0.3);

                        // if (col1 == pivotColumn || col2 == pivotColumn || col1 == pivot || col2 ==
                        // pivot) {
                        // Platform.runLater(() -> {
                        // // col1.setFill(Color.YELLOW);
                        // // col2.setFill(Color.YELLOW);
                        // // pivot.setFill(Color.YELLOW);
                        // // AnimationUtils.fadeColor(changeColorColumns, Color.YELLOW, 0.3);

                        // AnimationUtils.fadeColor(pivot, Color.YELLOW, 0.3);
                        // });
                        // }

                        Platform.runLater(() -> sortExplainationTextField.setText(
                                String.format("Since %d > %d, swap their position", col2.getValue(), col1.getValue())));

                        Thread.sleep(600);

                        if (!isAnimating) {
                            isAnimating = true;
                            col1.swap(col2, 0.3, columns, textValues, () -> {
                                isAnimating = false;
                            });
                        }

                    } else {
                        AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, 0.3);

                        // if (col1 == pivotColumn || col2 == pivotColumn || col1 == pivot || col2 ==
                        // pivot) {
                        // Platform.runLater(() -> {
                        // // col1.setFill(Color.YELLOW);
                        // // col2.setFill(Color.YELLOW);
                        // // pivot.setFill(Color.YELLOW);

                        // AnimationUtils.fadeColor(pivot, Color.YELLOW, 0.3);
                        // });
                        // }

                        Platform.runLater(() -> sortExplainationTextField
                                .setText(
                                        String.format("Since %d <= %d, do nothing", col2.getValue(), col1.getValue())));

                        Thread.sleep(500);
                    }
                    Thread.sleep(600);
                    for (ColumnBar col : changeColorColumns) {
                        col.setFill(ColumnBar.DEFAULT_COLOR);
                    }
                    pivot.setFill(ColumnBar.DEFAULT_COLOR);
                }

                Platform.runLater(() -> sortExplainationTextField.setText("Finish Sorting"));
                Thread.sleep(1000);

                return null;
            }
        };

        sortingThread = new Thread(newTask);
        sortingThread.start();
    }

    public void swapping() {
        sortingThread.interrupt();

        Task<Void> nextTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                int arrLength = columns.size();
                int[] intArray = new int[arrLength];
                int swap_index = 0;
                for (ColumnBar column : columns) {
                    intArray[swap_index] = column.getValue();
                    swap_index++;
                }
                QuickSort obj = new QuickSort(intArray);
                obj.sort();

                int[][] pointerLog = obj.getPointerLog();

                int index1 = pointerLog[SortController.logStep][0];
                int index2 = pointerLog[SortController.logStep][1];

                ColumnBar col1 = columns.get(index1);
                ColumnBar col2 = columns.get(index2);
                ArrayList<ColumnBar> changeColorColumns = new ArrayList<ColumnBar>();

                changeColorColumns.add(col1);
                changeColorColumns.add(col2);

                // Lấy cột pivot
                ColumnBar pivot = columns.get(obj.getPivotIndex());
                AnimationUtils.fadeColor(pivot, Color.YELLOW, 0.3);

                if (pointerLog[SortController.logStep][2] == 1) {
                    AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, 0.3);

                    // if (col1 == pivotColumn || col2 == pivotColumn || col1 == pivot || col2 ==
                    // pivot) {
                    // Platform.runLater(() -> {
                    // // col1.setFill(Color.YELLOW);
                    // // col2.setFill(Color.YELLOW);
                    // // pivot.setFill(Color.YELLOW);

                    // AnimationUtils.fadeColor(pivot, Color.YELLOW, 0.3);
                    // });
                    // }

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

                    // if (col1 == pivotColumn || col2 == pivotColumn || col1 == pivot || col2 ==
                    // pivot) {
                    // Platform.runLater(() -> {
                    // // col1.setFill(Color.YELLOW);
                    // // col2.setFill(Color.YELLOW);
                    // // pivot.setFill(Color.YELLOW);

                    // AnimationUtils.fadeColor(pivot, Color.YELLOW, 0.3);
                    // });
                    // }

                    Platform.runLater(() -> sortExplainationTextField.setText("Do not swap"));
                    Thread.sleep(200);
                }
                Thread.sleep(600);
                for (ColumnBar col : changeColorColumns) {
                    col.setFill(ColumnBar.DEFAULT_COLOR);
                }
                pivot.setFill(ColumnBar.DEFAULT_COLOR);
                if (index1 == 0 && index2 == 0) {
                    Platform.runLater(() -> sortExplainationTextField.setText("Finish Sorting"));
                } else {
                    SortController.logStep++;
                }
                return null;
            }
        };

        nextThread = new Thread(nextTask);
        nextThread.start();
    }

    public void stopSorting() {
        // Implement the logic to stop the sorting process if needed
    }

    public void backStep() {
        // Implement the logic to go back to the previous step if needed
    }

    public void setPivotColumn(ColumnBar pivotColumn) {
        this.pivotColumn = pivotColumn;
    }
}
