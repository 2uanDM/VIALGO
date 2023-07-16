package main.java.controller;

import java.util.Collections;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import main.java.model.object.ColumnBar;
import main.java.model.sorting_algo.InsertionSort;
import main.java.model.vialgo_utils.AnimationUtils;

public class InsertionSortController extends SortController {

    @Override
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
                InsertionSort obj = new InsertionSort(intArray);
                obj.sort();

                // Get the pointer log to perform animation step
                int[][] pointerLog = obj.getPointerLog();

                Thread.sleep(1500);

                for (int stepCount = 1; stepCount < pointerLog.length; stepCount++) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }

                    if (testBlankArray(pointerLog[stepCount])) {
                        continue;
                    }

                    int value = pointerLog[stepCount][0];
                    int valueIndex = pointerLog[stepCount][1];
                    int postionToInsert = pointerLog[stepCount][2];

                    // Mark the values as sorted:
                    for (int i = 0; i < valueIndex; i++) {
                        ColumnBar sortedColumnBar = columns.get(i);
                        AnimationUtils.fadeColor(sortedColumnBar, Color.ORANGE, animationTime);
                    }
                    Platform.runLater(() -> sortExplainationTextField.setText("Mark the elements sorted"));
                    Thread.sleep(intervalTime);

                    // Move down the current column
                    ColumnBar currentColumnBar = columns.get(valueIndex);
                    AnimationUtils.fadeColor(currentColumnBar, Color.rgb(220, 20, 60), animationTime);
                    if (!isAnimating) {
                        isAnimating = true;
                        AnimationUtils.moveVertical(currentColumnBar, "down", animationTime, () -> {
                            isAnimating = false;
                        });
                    }
                    Platform.runLater(() -> sortExplainationTextField
                            .setText(String.format("Extract the first unsorted element (%d)", value)));
                    Thread.sleep(intervalTime);

                    if (pointerLog[stepCount][3] == 0) {
                        // Remain postion
                        AnimationUtils.fadeColor(columns.get(columns.indexOf(currentColumnBar) - 1), Color.GREEN,
                                animationTime);
                        Thread.sleep(intervalTime);
                        if (!isAnimating) {
                            isAnimating = true;
                            AnimationUtils.moveVertical(currentColumnBar, "up", animationTime, () -> {
                                isAnimating = false;
                                AnimationUtils.fadeColor(currentColumnBar, Color.ORANGE, animationTime);
                            });
                        }

                        Platform.runLater(() -> sortExplainationTextField
                                .setText(String.format("Since (%d) is at the right position, do nothing", value)));
                        Thread.sleep(intervalTime);
                    } else {
                        // Perform inserstion
                        Platform.runLater(() -> sortExplainationTextField
                                .setText(String.format("Insert (%d) to the correct position (%d)", value,
                                        postionToInsert)));

                        for (int i = valueIndex - 1; i >= postionToInsert; i--) {
                            ColumnBar moveRightColumnBar = columns.get(i);
                            AnimationUtils.fadeColor(moveRightColumnBar, Color.GREEN, animationTime);
                            Thread.sleep(intervalTime);

                            if (!isAnimating) {
                                isAnimating = true;
                                AnimationUtils.moveHorizontal(currentColumnBar, columns, "left", animationTime, () -> {
                                    isAnimating = false;
                                });
                                AnimationUtils.moveHorizontal(moveRightColumnBar, columns, "right", animationTime,
                                        () -> {
                                            isAnimating = false;
                                        });

                                Collections.swap(columns, columns.indexOf(currentColumnBar),
                                        columns.indexOf(currentColumnBar) - 1);
                            }

                            Thread.sleep(intervalTime);
                            AnimationUtils.fadeColor(moveRightColumnBar, Color.ORANGE, 0.2);
                            Thread.sleep(intervalTime);
                        }

                        if (!isAnimating) {
                            isAnimating = true;
                            AnimationUtils.moveVertical(currentColumnBar, "up", animationTime, () -> {
                                isAnimating = false;
                                AnimationUtils.fadeColor(currentColumnBar, Color.ORANGE, animationTime);
                            });
                        }

                        Thread.sleep(intervalTime);
                    }
                }

                Platform.runLater(() -> sortExplainationTextField
                        .setText("Finish Sorting"));

                return null;
            }

        };

        sortingThread = new Thread(newTask);
        sortingThread.start();
    }

    private boolean testBlankArray(int[] array) {
        boolean allZero = true;
        for (int x : array) {
            if (x != 0)
                allZero = false;
        }
        return allZero;
    }

}
