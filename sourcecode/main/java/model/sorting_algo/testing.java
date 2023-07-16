package main.java.model.sorting_algo;

import java.util.Arrays;

public class testing {
    public static void main(String[] args) {
        int[] test_arr = { 30, 37, 7, 5, 6, 15, 33 };
        QuickSort obj = new QuickSort(test_arr);
        obj.sort();
        // System.out.println(Arrays.toString(obj.getInputArray()));
    }
}
