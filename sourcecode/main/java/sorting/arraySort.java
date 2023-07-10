package main.java.sorting;

import main.java.utils.arrayUtils;

public abstract class arraySort {
/**
 * Store Log variable for tracking
 * int[][] arrayLog: arrayLog[i] store the array after changing i times
 * int[][] tempLog: tempLog[i] store the part of the array that using in i-th calculation steps.
 * int[][] pointerLog: save the poiters using in the i-th calculation steps.
 * String[] messageLog: save the message in the i-th calculator steps.
 */
    protected int[][] arrayLogs;
    protected int[][] tempLogs;
    protected int[][] pointerLogs;
    protected String[] messageLogs;

    protected int[] inputArray;
    protected int stepCount = 0; //number of changing steps.

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

    public int getStepCount() {
        return stepCount;
    }

    public arraySort(int[] inputArray) {
        // the first element when stepCount=0 store the original array
        this.inputArray = inputArray;
        this.arrayLogs = new int[1][inputArray.length];
        this.tempLogs = new int[1][inputArray.length];
        this.pointerLogs = new int[1][2];  // depend on the sorting algorithms, we use up to 2 pointerLog
        this.messageLogs = new String[1];

        this.arrayLogs[0] = inputArray;
        this.messageLogs[0] = "We did not change anything in the array";
    }

    public abstract void sort();

    public void addLogs(int[] arrayLog, int[] tempLog, int[] pointerLog, String messageLog) {
        //check for the space
        if (arrayLogs.length == this.stepCount ) {
            //when the Logs array is full, array.length = n store only n-1 stepCount due to empty space at index 0
            arrayLogs = arrayUtils.Resize(arrayLogs);
            tempLogs = arrayUtils.Resize(tempLogs);
            pointerLogs = arrayUtils.Resize(pointerLogs);
            messageLogs = arrayUtils.Resize(messageLogs);
        }
        // then add Log to the Logs array
        arrayLogs[stepCount] = arrayLog;
        tempLogs[stepCount] = tempLog;
        pointerLogs[stepCount] = pointerLog;
        messageLogs[stepCount] = messageLog;
        this.stepCount ++; 
    }



    
}
