package test;

import main.java.model.sorting_algo.InsertionSort;

public class testing {
    public static void main(String[] args) {
        int[] arr = { 2, 10, 8, 45, 10, 3, 16 };
        InsertionSort insertionSort = new InsertionSort(arr);
        insertionSort.sort();
    }
}