package main.java.model.sorting_algo;

import java.util.Arrays;

public class testing {
    public static void main(String[] args) {
        int[] test_arr = { 5, 4, 3, 2, 1 };
        QuickSort obj = new QuickSort(test_arr);
        obj.sort();
        // System.out.println(Arrays.toString(obj.getInputArray()));
    }
}
