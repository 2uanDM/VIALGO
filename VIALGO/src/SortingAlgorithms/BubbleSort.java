package SortingAlgorithms;

import utils.arrayUtils;

public class BubbleSort extends arraySort {
    // New instance variable for pointer logs
    protected int[][] pointerLogs;

    public BubbleSort(int[] array) {
        super(array);
        this.pointerLogs = new int[1][2];
        this.arrayLogs[0] = array.clone();

    }

    @Override
    public void sort() {
        int n = inputArray.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (inputArray[j] > inputArray[j + 1]) {
                    // Swap elements
                    int temp = inputArray[j];
                    inputArray[j] = inputArray[j + 1];
                    inputArray[j + 1] = temp;
                }
                // Add logs for each comparison step
                int[] arrayLog = inputArray.clone();
                int[] tempLog = new int[0];
                int[] pointerLog = new int[] { j, j + 1 };
                addLogs(arrayLog, tempLog, pointerLog, "Comparing " + inputArray[j] + " and " + inputArray[j + 1]);
            }
        }
    }

    @Override
    public void addLogs(int[] arrayLog, int[] tempLog, int[] pointerLog, String messageLog) {
        // Check for space
        if (arrayLogs.length == this.stepCount) {
            arrayLogs = arrayUtils.Resize(arrayLogs);
            tempLogs = arrayUtils.Resize(tempLogs);
            pointerLogs = arrayUtils.Resize(pointerLogs);
            messageLogs = arrayUtils.Resize(messageLogs);
        }
        // Add logs to the log arrays
        arrayLogs[stepCount] = arrayLog;
        tempLogs[stepCount] = tempLog;
        pointerLogs[stepCount] = pointerLog;
        messageLogs[stepCount] = messageLog;
        this.stepCount++;
    }

    public int[] getSortedArray() {
        return inputArray;
    }

    public int[][] getArrayLogs() {
        return arrayLogs;
    }

    public int[][] getPointerLogs() {
        return pointerLogs;
    }

    public String[] getMessageLogs() {
        return messageLogs;
    }
}
