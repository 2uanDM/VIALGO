package main.java.controller;

import main.java.model.object.ColumnBar;
import main.java.model.sorting_algo.BubbleSort;

public class BubbleSortController extends SortController {

    private static int[] arrayVal = { -1 }; // avoid null error in SortingAlgorithm.java

    private static BubbleSort obj = new BubbleSort(arrayVal);
    private static int stepCount = 1;

    public void swapping() {
        // System.out.println("clicked");
        // int firstColumnIndex;
        // int secondColumnIndex;
        // int[][] pointerLog;
        // // arrayVal stores user input in type of int array
        // obj.sort();
        // pointerLog = obj.getPointerLog();
        // firstColumnIndex = pointerLog[stepCount][0];
        // secondColumnIndex = pointerLog[stepCount][1];
        // System.out.println("first column: " + firstColumnIndex);
        // System.out.println("second column: " + secondColumnIndex);
        // stepCount++;

        int col1Index = random.nextInt(0, 5);
        int col2Index = random.nextInt(6, 10);

        ColumnBar col1 = columns.get(col1Index);
        ColumnBar col2 = columns.get(col2Index);

        col1.swap(col2, 0.3, columns);
    }

}
