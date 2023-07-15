package main.java.model.sorting_algo;

import java.util.Arrays;

import main.java.model.vialgo_utils.ArrayUtils;

public class BubbleSort extends SortingAlgorithm {
    public BubbleSort(int[] inputArray) {
        super(inputArray);
        System.out.println(Arrays.toString(this.arrayLogs[0]));
    }

    public void sort() {
        for (int i = 0; i < inputArray.length; i++) {
            boolean swapped = false;
            // the last i element has been sorted, then we swap from 0 to inputArray.length
            // - i - 1
            for (int j = 0; j < inputArray.length - i - 1; j++) {
                if (inputArray[j] > inputArray[j + 1]) {
                    // create a copy version to store Log
                    int[] newArray = ArrayUtils.copyArray(inputArray);

                    // swaped two adjacent elements
                    int temp;
                    temp = newArray[j];
                    newArray[j] = newArray[j + 1];
                    newArray[j + 1] = temp;
                    swapped = true;

                    // after changing, we assign inputArray to newArray
                    inputArray = newArray;

                    // add Logs
                    int[] arrayLog = newArray;
                    int[] pointerLog = { j + 1, j };
                    int[] tempLog = {};
                    String messageLog = String.format("Since %d > %d, swap element %d with the element %d",
                            inputArray[j + 1],
                            inputArray[j],
                            inputArray[j + 1],
                            inputArray[j]);

                    this.addLogs(arrayLog, tempLog, pointerLog, messageLog);

                    System.out.println(messageLog);
                    System.out.println(Arrays.toString(newArray));
                } else {

                    int[] arrayLog = inputArray;
                    int[] pointerLog = { j + 1, j };
                    int[] tempLog = {};
                    String messageLog = String.format("Since %d <= %d, we do nothing.",
                            inputArray[j],
                            inputArray[j + 1],
                            inputArray[j],
                            inputArray[j + 1]);

                    this.addLogs(arrayLog, tempLog, pointerLog, messageLog);

                    System.out.println(messageLog);
                    System.out.println(Arrays.toString(inputArray));
                }
            }
            System.out.println("------------------------------------");
            if (swapped == false) {
                System.out.println(inputArray[i]);
                break;
            }
        }
    }
}
