package main.java.model.sorting_algo;

import java.util.Arrays;

import main.java.model.vialgo_utils.ArrayUtils;

public class QuickSort extends SortingAlgorithm {
    private int[] tempLog = { 0 }; // tempLog store the pivot during sorting process. We choosing the first index
                                   // is pivot, so initialze = 0
    private int[] arrayLog = inputArray;
    private int[] pointerLog = { 0, 0, 0 };
    String messageLog = "";
    private int swapped = 0;

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

                // check for actual swapping
                this.swapped = 1;
                swap(i, storeIndex);
                this.pointerLog = new int[] { i, storeIndex, this.swapped };
                storeIndex++;
            } else {
                this.swapped = 0;
                this.pointerLog = new int[] { i, storeIndex, this.swapped };
            }
            this.tempLog = new int[] { pivotIndex, 0 };
            this.arrayLog = inputArray;

            // add log each time check for swap or not
            this.addLogs(arrayLog, tempLog, pointerLog, messageLog);
            System.out.println(
                    "[" + inputArray[pointerLog[0]] + " " + inputArray[pointerLog[1]] + " " + pointerLog[2] + "]"
                            + "pointer");
            System.out.println(Arrays.toString(tempLog) + "temp");
            System.out.println(Arrays.toString(arrayLog));
        }

        swap(pivotIndex, storeIndex - 1);
        // store log when swap pivotIndex with storeIndex
        this.arrayLog = inputArray;
        this.tempLog = new int[] { storeIndex - 1, 1 };

        this.swapped = 1;
        this.pointerLog = new int[] { pivotIndex, storeIndex - 1, this.swapped };
        this.addLogs(arrayLog, tempLog, pointerLog, messageLog);
        System.out.println("CHANGE PIVOT");
        System.out.println(
                "[" + inputArray[pointerLog[0]] + " " + inputArray[pointerLog[1]] + " " + pointerLog[2] + "]"
                        + "pointer");
        System.out.println(Arrays.toString(tempLog) + "temp");
        System.out.println(Arrays.toString(arrayLog));
        return storeIndex - 1;
    }

    private void swap(int i, int j) {
        int temp = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = temp;

    }

}