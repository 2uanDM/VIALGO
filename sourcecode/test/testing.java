package test;

import main.java.model.sorting_algo.BubbleSort;

public class testing {
    public static void main(String[] args) {
        int[] arr = { 3, 13, 42, 12, 4, 11, 22, 34 };

        BubbleSort bubbleSort = new BubbleSort(arr);
        bubbleSort.sort();
    }
}