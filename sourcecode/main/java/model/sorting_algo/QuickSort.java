package main.java.model.sorting_algo;

import java.util.Arrays;

import main.java.model.vialgo_utils.ArrayUtils;

public class QuickSort extends SortingAlgorithm {
    public QuickSort(int[] inputArray) {
        super(inputArray);
        System.out.println(Arrays.toString(this.arrayLogs[0]));
    }

    public void sort() {
        quicksort(0, inputArray.length - 1);
    }

    private void quicksort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quicksort(low, pivotIndex - 1);
            quicksort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = inputArray[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (inputArray[j] < pivot) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        int temp = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = temp;

        int[] arrayLog = ArrayUtils.copyArray(inputArray);
        int[] pointerLog = { i, j, 1 };
        int[] tempLog = {};
        String messageLog = String.format("Swapped elements %d and %d", inputArray[i], inputArray[j]);
        this.addLogs(arrayLog, tempLog, pointerLog, messageLog);
    }

    public int getPivotIndex() {
        return inputArray.length - 1; // Pivot index is always the last index in the current implementation
    }
}
