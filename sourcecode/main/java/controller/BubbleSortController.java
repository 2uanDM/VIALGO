package main.java.controller;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;

import javafx.scene.paint.Color;
import main.java.model.object.ColumnBar;
import main.java.model.vialgo_utils.AnimationUtils;
import main.java.model.sorting_algo.BubbleSort;

public class BubbleSortController extends SortController {

    public void sortButtonHandler() {
        // Prevent many sort tasks run concurrently
        if (sortingThread.isAlive()) {
            return;
        }

        // Close all left panel and show all right panel
        sidePanelActionBeforeSorting();

        Task<Void> newTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Sort the array first
                int arrLength = columns.size();
                int[] intArray = new int[arrLength];
                int index = 0;
                for (ColumnBar column : columns) {
                    intArray[index] = column.getValue();
                    index++;
                }
                BubbleSort obj = new BubbleSort(intArray);
                obj.sort();

                // Get the pointer log to perform animation step
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

                    if (pointerLog[stepCount][2] == 1) {
                        AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, animationTime / sortingSpeed);

                        Platform.runLater(() -> sortExplainationTextField.setText(
                                String.format("Since %d > %d, swap their position", col2.getValue(), col1.getValue())));

                        Thread.sleep(intervalTime);

                        if (!isAnimating) {
                            isAnimating = true;
                            col1.swap(col2, animationTime, columns, textValues, () -> {
                                isAnimating = false;
                            });
                        }

                    } else {
                        AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, animationTime / sortingSpeed);

                        Platform.runLater(() -> sortExplainationTextField
                                .setText(
                                        String.format("Since %d <= %d, do nothing", col2.getValue(), col1.getValue())));

                        Thread.sleep(intervalTime);
                    }
                    Thread.sleep(intervalTime);
                    for (ColumnBar col : changeColorColumns) {
                        col.setFill(ColumnBar.DEFAULT_COLOR);
                    }
                }

                Platform.runLater(() -> sortExplainationTextField.setText("Finish Sorting"));

                return null;
            }
        };

        sortingThread = new Thread(newTask);
        sortingThread.start();

    }
}
