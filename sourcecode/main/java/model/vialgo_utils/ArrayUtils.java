package main.java.model.vialgo_utils;

public class ArrayUtils {
    public static int max(int[] array) {
        int maxElement = array[0];
        for (int i : array) {
            if (i > maxElement) {
                maxElement = i;
            }
            // else: continue iterate in for loop
        }
        // here, we do not consider whether or not the maximun element is unique
        return maxElement;
    }

    public static int min(int[] array) {
        int minElement = array[0];
        for (int i : array) {
            if (i < minElement) {
                minElement = i;
            }
            // else: continue iterate in the for loop
        }
        return minElement;
    }

    public static int[] Resize(int[] array) {
        int oldLength = array.length;
        int newLength = oldLength * 2; // double the length
        int[] newArray = new int[newLength];
        // copy values to the new array
        for (int i = 0; i < oldLength; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static int[][] Resize(int[][] array) {
        int oldLength = array.length;
        int newLength = oldLength * 2; // double the length
        int[][] newArray = new int[newLength][array[0].length];
        // copy values to the new array
        for (int i = 0; i < oldLength; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static String[] Resize(String[] array) {
        int oldLength = array.length;
        int newLength = oldLength * 2; // double the length
        String[] newArray = new String[newLength];
        // copy values to the new array
        for (int i = 0; i < oldLength; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static int[] copyArray(int[] array, int fromIndex, int toIndex) {
        // copy the given array to a new array from to fromIndex index to toIndex index
        // not copy the element at the toIndex index
        int[] newArray = new int[array.length];
        for (int i = fromIndex; i < toIndex; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static int[] copyArray(int[] array) {
        // copy the entire array
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    public static String toString(int[] array) {
        String string = "[";
        for (int element : array) {
            string += element + ", ";
        }
        string = string.substring(0, string.length() - 2);
        string += "]";
        return string;
    }
}
