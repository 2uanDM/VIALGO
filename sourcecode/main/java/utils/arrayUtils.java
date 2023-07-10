package utils;

// We provide 3 basic static methods for Array includes finding maximun, minimun element and resizing the array length.
public abstract class ArrayUtils {
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

}