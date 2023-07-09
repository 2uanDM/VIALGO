package main.java.sorting;

import java.util.Arrays;

import main.java.utils.arrayUtils;

public class insertionSort extends arraySort {
    public insertionSort(int[] inputArray) {
        super(inputArray);
    }

    public void sort() {
        int sortedPart = 1; // the number of element in the sorted part, and also is the index of first element in unsorted part
        // When sortedPart = inputArray.length, which means entire array is in SortedPart
        int[] previousArray = this.inputArray;

        while ( sortedPart < inputArray.length) {
            boolean isChange = false;
            // start changing the array, then we need add 1 to this.stepCount
            int[] newArray = arrayUtils.copyArray(previousArray);
            int element = previousArray[sortedPart]; // element variable is the first element in unsorted part
            int elementIndex = -1; // tracking where to put element in new array
            for (int i=0; i < sortedPart; i++) {
                if (element > previousArray[i]) {
                    // nothing happen, we need to move to the right of the array
                }
                else {  //element < this.inputArray[i] and element > this.inputArray[i+1]
                    // We need to put element at the elementIndex of the Array, then break to for loop
                    newArray[i] = element;
                    elementIndex = i;
                    isChange = true; // the array is changed
                    break;
                }
            }
            
            if (isChange) {
                for (int i = elementIndex + 1; i < sortedPart + 1; i++) {
                    newArray[i] = previousArray[i-1];
                }
                previousArray = newArray;
                System.out.println(Arrays.toString(newArray));
            }

            // Here, we ensure that the sortedPart increases by 1
            sortedPart ++;
            

            

        } 



    }
    
}
