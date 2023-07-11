package test;

import main.java.model.sorting_algo.BubbleSort;
import main.java.model.vialgo_utils.ArrayUtils;

public class testing {
    public static void main(String[] args) {
        int[] testArray = { 6, 5, 4, 3, 2, 1 };
        int minEle = ArrayUtils.min(testArray);
        int maxEle = ArrayUtils.max(testArray);
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