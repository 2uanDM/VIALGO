package test;

import main.java.model.sorting_algo.BubbleSort;
import main.java.model.vialgo_utils.ArrayUtils;

public class testing {
    public static void main(String[] args) {
        int[] a = { 33, 5, 3, 56, 2, 5, 46 };

        BubbleSort bubbleSort = new BubbleSort(a);
        bubbleSort.sort();
    }
}