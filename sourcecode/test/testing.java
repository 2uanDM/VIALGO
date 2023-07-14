package test;

import main.java.model.sorting_algo.BubbleSort;
import main.java.model.vialgo_utils.ArrayUtils;

public class testing {
    public static void main(String[] args) {
        String content = "1,3,4,14,,";
        String[] values = content.split(",", -1);
        for (String x : values) {
            System.out.println(x);
        }
        System.out.println(values.length);
    }
}