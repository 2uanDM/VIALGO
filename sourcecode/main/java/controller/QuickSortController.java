package main.java.controller;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import main.java.model.object.ColumnBar;
import main.java.model.vialgo_utils.AnimationUtils;
import main.java.model.sorting_algo.QuickSort;

public class QuickSortController extends SortController {

    private boolean isAnimating = false;

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
                // Block the speed slider during sorting
                Platform.runLater(() -> speedSlider.setDisable(true));

                // Sort the array first
                int arrLength = columns.size();
                int[] intArray = new int[arrLength];
                int index = 0;
                for (ColumnBar column : columns) {
                    intArray[index] = column.getValue();
                    index++;
                }
                QuickSort obj = new QuickSort(intArray);
                obj.sort();

                // Get the pointer log to perform animation step
                int[][] pointerLog = obj.getPointerLog();
                int[][] tempLog = obj.getTempLogs();

                Thread.sleep(1500);

                for (int stepCount = 1; stepCount < pointerLog.length; stepCount++) {
                    int index1 = pointerLog[stepCount][0];
                    int index2 = pointerLog[stepCount][1];

                    // fix bug: poiterLog store some invalid index due to for loop, just ignore it
                    if (index1 >= columns.size() || index2 >= columns.size()) {
                        continue;
                    }

                    ColumnBar col1 = columns.get(index1);
                    ColumnBar col2 = columns.get(index2);

                    if (tempLog[stepCount][1] == 1) {
                        // this is the step that we change Pivot column, after that the pivot is in the
                        // right place

                        // swap current pivot with the new pivot
                        if (!isAnimating) {
                            isAnimating = true;
                            if (index1 == index2) {
                                // Here, we do not swap any thing, just change pivot color to ORANGE
                            } else {
                                col1.swap(col2, animationTime / sortingSpeed, columns, textValues, () -> {
                                });
                            }
                            Thread.sleep(intervalTime);
                            int pivotIndex = tempLog[stepCount][0];
                            ColumnBar pivotColumn = columns.get(pivotIndex);
                            AnimationUtils.fadeColor(pivotColumn, Color.ORANGE, animationTime / sortingSpeed);
                            pivotColumn.setFill(Color.ORANGE);
                            Platform.runLater(() -> sortExplainationTextField
                                    .setText(String.format("The element at index %d move to the right place",
                                            pivotIndex)));

                            isAnimating = false;
                            Thread.sleep(intervalTime);
                        }
                    }

                    else { // Here, current pivot Column(yellow) not change
                        if (index1 == index2) {
                            // pivot not change, and do not swap, then
                            continue;
                        }
                        // FOR PIVOT ANIMATION, change pivot color to YELLOW
                        int pivotIndex = tempLog[stepCount][0];
                        ColumnBar pivotColumn = columns.get(pivotIndex);
                        AnimationUtils.fadeColor(pivotColumn, Color.YELLOW, animationTime / sortingSpeed);
                        Platform.runLater(() -> sortExplainationTextField
                                .setText(String.format("Set element at index %d as the pivot", pivotIndex)));
                        Thread.sleep(intervalTime);
                        // swap two column in PointerLog

                        ArrayList<ColumnBar> changeColorColumns = new ArrayList<ColumnBar>();

                        changeColorColumns.add(col1);
                        changeColorColumns.add(col2);

                        if (pointerLog[stepCount][2] == 1) {
                            AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, animationTime / sortingSpeed);

                            Platform.runLater(() -> sortExplainationTextField
                                    .setText(String.format("We swap column %d with the column %d.", index1,
                                            index2)));

                            Thread.sleep(intervalTime);
                            if (!isAnimating) {
                                isAnimating = true;
                                col1.swap(col2, animationTime / sortingSpeed, columns, textValues, () -> {
                                    isAnimating = false;

                                });
                            }

                        } else {
                            AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, animationTime / sortingSpeed);

                            Platform.runLater(() -> sortExplainationTextField
                                    .setText("Do not swap"));
                            Thread.sleep(intervalTime);
                        }
                        Thread.sleep(intervalTime);

                        AnimationUtils.fadeColor(changeColorColumns, ColumnBar.DEFAULT_COLOR,
                                animationTime / sortingSpeed);
                        System.out.println("END");

                    }

                }
                Platform.runLater(() -> sortExplainationTextField.setText("Finish Sorting"));
                AnimationUtils.fadeColor(columns, ColumnBar.DEFAULT_COLOR,
                        animationTime / sortingSpeed);

                // Re-enable the speed slider after sorting
                Platform.runLater(() -> speedSlider.setDisable(false));

                return null;
            }
        };

        sortingThread = new Thread(newTask);
        sortingThread.start();
    }

}