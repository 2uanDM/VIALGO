package main.java.model.sorting_algo;

import java.util.Arrays;

public class testing {
    public static void main(String[] args) {
        int[] test_arr = { 19, 35, 30, 1, 9, 43, 19 };
        QuickSort obj = new QuickSort(test_arr);
        obj.sort();
        // System.out.println(Arrays.toString(obj.getInputArray()));
    }
}
