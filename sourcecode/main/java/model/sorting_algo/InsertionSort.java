package main.java.model.sorting_algo;

import main.java.model.vialgo_utils.ArrayUtils;

public class InsertionSort extends SortingAlgorithm {
    public InsertionSort(int[] inputArray) {
        super(inputArray);
    }

    public void sort() {
        int sortedPart = 1;
        while (sortedPart < inputArray.length) {
            int[] newArray = ArrayUtils.copyArray(inputArray);
            int element = inputArray[sortedPart];
            int elementIndex = sortedPart;

            for (int i = 0; i < sortedPart; i++) {
                if (element < inputArray[i]) {
                    newArray[i] = element;
                    elementIndex = i;
                    break;
                }
            }

            if (elementIndex != sortedPart) {
                int[] arrayLog = newArray;
                String messageLog = String.format("We insert the value %d to the index %d", element, elementIndex);
                int[] pointerLog = { element, sortedPart, elementIndex, 1 };
                int[] tempLog = ArrayUtils.copyArray(inputArray, elementIndex, sortedPart + 1);
                this.addLogs(arrayLog, tempLog, pointerLog, messageLog);

                for (int i = elementIndex + 1; i < sortedPart + 1; i++) {
                    newArray[i] = inputArray[i - 1];
                }
                inputArray = newArray;
            } else {
                int[] arrayLog = newArray;
                String messageLog = String.format("Since %d has been sorted, we do nothing", element);
                int[] pointerLog = { element, sortedPart, elementIndex, 0 };
                int[] tempLog = ArrayUtils.copyArray(inputArray, elementIndex, sortedPart + 1);
                this.addLogs(arrayLog, tempLog, pointerLog, messageLog);
            }
            sortedPart++;
        }
    }

}
