package main.java.model.sorting_algo;

import java.util.Arrays;

import main.java.model.vialgo_utils.ArrayUtils;

public class QuickSort extends SortingAlgorithm {
    public QuickSort(int[] inputArray) {
        super(inputArray);
    }

    public void sort() {
        quicksort(0, inputArray.length - 1);
        for (int[] array : arrayLogs) {
            System.out.println(Arrays.toString(array));
        }
    }

    private void quicksort(int low, int high) {
        if (low < high) {
            int partitionIndex = partition(low, high);
            quicksort(low, partitionIndex - 1);
            quicksort(partitionIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = inputArray[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (inputArray[j] < pivot) {
                swap(i, j);
                i++;
            }
        }

        swap(i, high);

        return i;
    }

    private void swap(int i, int j) {
        int temp = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = temp;
        // add log

        int[] arrayLog = ArrayUtils.copyArray(inputArray);
        int[] pointerLog = { inputArray[j], inputArray[i] };
        int[] tempLog = {};
        String messageLog = String.format("Swap element %d with the element %d", inputArray[j], inputArray[i]);

        this.addLogs(arrayLog, tempLog, pointerLog, messageLog);
        System.out.println(Arrays.toString(inputArray));
        System.out.println(messageLog);

    }
}
