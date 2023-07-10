package main.java.sorting_algo;

import utils.ArrayUtils;

public abstract class SortingAlgorithm {
    /**
     * Store Log variable for tracking
     * int[][] arrayLog: arrayLog[i][j] store the array after changing i times
     * int[][] tempLog: tempLog[i][j] store the part of the array that using in i-th
     * calculation steps.
     * int[][] pointerLog: save the poiters using in the i-th calculation steps.
     * String[] messageLog: save the message in the i-th calculator steps.
     */
    protected int[][] arrayLogs;
    protected int[][] tempLogs;
    protected int[][] pointerLogs;
    protected String[] messageLogs;

    protected int[] inputArray;
    protected int stepCount = 0; // number of changing steps.

    public int[][] getArrayLogs() {
        return arrayLogs;
    }

    public int[][] getTempLogs() {
        return tempLogs;
    }

    public int[][] getPointerLog() {
        return pointerLogs;
    }

    public String[] getMessageLog() {
        return messageLogs;
    }

    public SortingAlgorithm() {
        this.arrayLogs = new int[1][inputArray.length];
        this.tempLogs = new int[1][inputArray.length];
        this.pointerLogs = new int[1][2]; // depend on the sorting algorithms, we use up to 2 pointerLog
        this.messageLogs = new String[1];
    }

    public abstract void sort();

    public void addLogs(int[] arrayLog, int[] tempLog, int[] pointerLog, String messageLog) {
        // check for the space
        if (arrayLogs.length == this.stepCount) {
            // when the Logs array is full
            arrayLogs = ArrayUtils.Resize(arrayLogs);
            tempLogs = ArrayUtils.Resize(tempLogs);
            pointerLogs = ArrayUtils.Resize(pointerLogs);
            messageLogs = ArrayUtils.Resize(messageLogs);
        }
        // then add Log to the Logs array
        arrayLogs[stepCount] = arrayLog;
        tempLogs[stepCount] = tempLog;
        pointerLogs[stepCount] = pointerLog;
        messageLogs[stepCount] = messageLog;
        this.stepCount++;
    }

}
