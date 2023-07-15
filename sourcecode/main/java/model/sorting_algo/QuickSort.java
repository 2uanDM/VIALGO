package main.java.model.sorting_algo;

import java.util.Arrays;

import main.java.model.vialgo_utils.ArrayUtils;

public class QuickSort extends SortingAlgorithm {
    private int pivotChoice;

    public QuickSort(int[] inputArray) {
        super(inputArray);
    }

    public int[] getInputArray() {
        return this.inputArray;
    }

    public void sort() {
        quickSort(0, inputArray.length - 1);

    }

    public void quickSort(int low, int high) {
        if (low < high) {

            // pi is partitioning index, inputArray[pivot]
            // is now at right place
            int pivot = partition(low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(low, pivot - 1);
            quickSort(pivot + 1, high);
        }
    }

    private int partition(int low, int high) {
        // first element as the pivot
        int pivotIndex = low;
        int storeIndex = pivotIndex + 1;
        for (int i = pivotIndex + 1; i <= high; i++) {
            if (inputArray[i] <= inputArray[pivotIndex]) {
                swap(i, storeIndex);
                storeIndex++;
            }
        }
        swap(pivotIndex, storeIndex - 1);

        return storeIndex - 1;
    }

    private void swap(int i, int j) {
        int temp = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = temp;
        System.out.println(Arrays.toString(inputArray));

        // Update logs
        // int[] arrayLog = ArrayUtils.copyArray(inputArray);
        // String messageLog = String.format("Swapped elements: %d and %d",
        // inputArray[i], inputArray[j]);
        // int[] pointerLog = { inputArray[i], inputArray[j] };
        // int[] tempLog = ArrayUtils.copyArray(inputArray, i, j + 1);
        // this.addLogs(arrayLog, tempLog, pointerLog, messageLog);
    }
}