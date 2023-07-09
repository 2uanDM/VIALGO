package SortingAlgorithms;

import utils.arrayUtils;

public class testing {
    public static void main(String[] args) {
        int[] testArray = {1, 2, 3, 4, 5, 6, 7};
        int minEle = arrayUtils.min(testArray);
        int maxEle = arrayUtils.max(testArray);
        testArray = arrayUtils.Resize(testArray);
        System.out.println(minEle);
        System.out.println(maxEle);
        System.out.println(testArray.length);

    }
}