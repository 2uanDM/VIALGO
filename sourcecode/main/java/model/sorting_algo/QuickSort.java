package main.java.model.sorting_algo;
import main.java.model.vialgo_utils.ArrayUtils;
public class QuickSort extends SortingAlgorithm {
    private int pivotChoice;

    public QuickSort(int[] inputArray, int pivotChoice) {
        super(inputArray);
        this.pivotChoice = pivotChoice;
    }

    public void sort() {
        quicksort(0, inputArray.length - 1);
        System.out.println("Start");
        for (int[] array : arrayLogs) {
            System.out.println(ArrayUtils.toString(array));
        }
    }

    private void quicksort(int low, int high) {
        if (low < high) {
            int pivotIndex;
            if (pivotChoice == 0) {
                pivotIndex = low;
            } else if (pivotChoice == 1) {
                pivotIndex = high;
            } else {
                pivotIndex = getRandomPivotIndex(low, high);
            }
            int partitionIndex = partition(low, high, pivotIndex);

            quicksort(low, partitionIndex - 1);
            quicksort(partitionIndex + 1, high);
        }
    }

    private int getRandomPivotIndex(int low, int high) {
        return (int) (Math.random() * (high - low + 1)) + low;
    }

    private int partition(int low, int high, int pivotIndex) {
        int pivot = inputArray[pivotIndex];
        System.out.println("Pivot Selected: " + pivot);
        swap(pivotIndex, low);

        int i = low + 1;

        for (int j = low + 1; j <= high; j++) {
            if (inputArray[j] < pivot) {
                swap(i, j);
                i++;
            }
        }
        System.out.println("Done Partition");
        swap(low, i - 1);
        return i - 1;
    }

    private void swap(int i, int j) {
        int temp = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = temp;

        // Update logs
        int[] arrayLog = ArrayUtils.copyArray(inputArray);
        String messageLog = String.format("Swapped elements: %d and %d", inputArray[i], inputArray[j]);
        int[] pointerLog = { inputArray[i], inputArray[j] };
        int[] tempLog = ArrayUtils.copyArray(inputArray, i, j + 1);
        this.addLogs(arrayLog, tempLog, pointerLog, messageLog);
    }
}