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
import main.java.model.sorting_algo.QuickSort;

public class QuickSortController extends SortController {

    private boolean isAnimating = false;
    @FXML
    private TextField firstTextField;

    @FXML
    private TextField secondTextField;

    public void sortButtonHandler() {
        System.out.println("HERE");

        nextThread.interrupt();
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
                int[][] tempLog = obj.getTempLogs();

                for (int stepCount = 1; stepCount < pointerLog.length; stepCount++) {
                    int index1 = pointerLog[stepCount][0];
                    int index2 = pointerLog[stepCount][1];

                    // fix bug: poiterLog store some invalid index due to for loop, just ignore it
                    if (index1 >= columns.size() || index2 >= columns.size()) {
                        continue;
                    }
                    ArrayList<ColumnBar> changeColorColumns = new ArrayList<ColumnBar>();

                    ColumnBar col1 = columns.get(index1);
                    ColumnBar col2 = columns.get(index2);

                    changeColorColumns.add(col1);
                    changeColorColumns.add(col2);
                    System.out.println(index1 + "!");
                    System.out.println(index2 + "!!");
                    System.out.println(pointerLog[stepCount][2] + "log");
                    if (pointerLog[stepCount][2] == 1) {
                        AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, 0.3);

                        Platform.runLater(() -> sortExplainationTextField
                                .setText(String.format("We swap column %d with the column %d.", index1,
                                        index2)));

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
                    AnimationUtils.fadeColor(changeColorColumns, ColumnBar.DEFAULT_COLOR,
                            0.3);
                    System.out.println("END");
                    // FOR PIVOT ANIMATION
                    int pivotIndex = tempLog[stepCount][0];
                    ColumnBar pivotColumn = columns.get(pivotIndex);
                    pivotColumn.setFill(Color.YELLOW);
                    System.out.println("END");
                    System.out.println(tempLog[stepCount][1] + "HEYYY");
                    if (tempLog[stepCount][1] == 1) {
                        System.out.println("INNER");
                        // pivotColumn.setFill(Color.BLACK);
                        System.out.println("INNER");
                        int oldPivot = tempLog[logStep - 1][0];

                        // ColumnBar prevPivotColumn = columns.get(oldPivot);
                        // System.out.println("INNER");
                        // prevPivotColumn.setFill(ColumnBar.DEFAULT_COLOR);

                    }
                    System.out.println("END");
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
        // sortingThread.interrupt();
        // Task<Void> nextTask = new Task<Void>() {
        // @Override
        // protected Void call() throws Exception {

        // SortController.logStep++;
        // int arrLength = columns.size();
        // int[] intArray = new int[arrLength];
        // int swap_index = 0;
        // for (ColumnBar column : columns) {
        // intArray[swap_index] = column.getValue();
        // swap_index++;
        // }
        // BubbleSort obj = new BubbleSort(intArray);
        // obj.sort();

        // int[][] pointerLog = obj.getPointerLog();

        // // stepCount is a static variable, each time user press Next button,
        // stepCount
        // // ++
        // int index1 = pointerLog[SortController.logStep][0];
        // int index2 = pointerLog[SortController.logStep][1];

        // ColumnBar col1 = columns.get(index1);
        // ColumnBar col2 = columns.get(index2);
        // ArrayList<ColumnBar> changeColorColumns = new ArrayList<ColumnBar>();

        // changeColorColumns.add(col1);
        // changeColorColumns.add(col2);

        // if (pointerLog[SortController.logStep][2] == 1) {
        // AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, 0.3);

        // Platform.runLater(() -> sortExplainationTextField
        // .setText(String.format("We swap column %d with the column %d.", index1,
        // index2)));

        // Thread.sleep(700);
        // if (!isAnimating) {
        // isAnimating = true;
        // col1.swap(col2, 0.3, columns, textValues, () -> {
        // isAnimating = false;

        // });
        // }

        // } else {
        // AnimationUtils.fadeColor(changeColorColumns, Color.GREEN, 0.3);

        // Platform.runLater(() -> sortExplainationTextField
        // .setText("Do not swap"));
        // Thread.sleep(200);
        // }
        // Thread.sleep(600);
        // for (ColumnBar col : changeColorColumns) {
        // col.setFill(ColumnBar.DEFAULT_COLOR);
        // }
        // // handing the case stepCount >= pointerLog.length, which mean the array is
        // // sorted.
        // if (index1 == 0 && index2 == 0) {
        // Platform.runLater(() -> sortExplainationTextField.setText("Finish Sorting"));
        // }
        // return null;
        // }
        // };

        // nextThread = new Thread(nextTask);
        // nextThread.start();

    }

    public void stopSorting() {
        // stop the sorting process
        // sortingThread.interrupt();

    }

    public void backStep() {

    }

}
