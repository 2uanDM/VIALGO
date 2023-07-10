package main.java.sorting;

import java.util.Arrays;

import main.java.utils.arrayUtils;

public class testing {
    public static void main(String[] args) {
        int[] testArray = {6, 5, 4, 3, 2, 1};
        int minEle = arrayUtils.min(testArray);
        int maxEle = arrayUtils.max(testArray);
        // System.out.println(minEle);
        // System.out.println(maxEle);
        // System.out.println(testArray.length);
        
        // insertionSort obj = new insertionSort(testArray);
        // System.out.println(Arrays.toString(testArray));
        // obj.sort();

        bubbleSort bubble = new bubbleSort(testArray);
        bubble.sort();

    }
}