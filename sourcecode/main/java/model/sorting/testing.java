package main.java.model.sorting;

import java.util.Arrays;

import main.java.model.utils.arrayUtils;

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

        BubbleSort bubble = new BubbleSort(testArray);
        bubble.sort();

    }
}