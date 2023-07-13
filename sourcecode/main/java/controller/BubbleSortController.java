package main.java.controller;

import java.util.Random;

import main.java.model.object.ColumnBar;
import main.java.model.vialgo_utils.InputParserUtils;
import main.java.model.sorting_algo.BubbleSort;
public class BubbleSortController extends SortController {

    private static int[] arrayVal = {-1}; // avoid null error in SortingAlgorithm.java

    private static BubbleSort obj = new BubbleSort(arrayVal);
    private static int stepCount = 1;
    public void swapping() {
        System.out.println("clicked");
        int firstColumnIndex;
        int secondColumnIndex;
        int[][] pointerLog;
        // arrayVal stores user input in type of int array
        obj.sort();
        pointerLog = obj.getPointerLog();
        firstColumnIndex = pointerLog[stepCount][0];
        secondColumnIndex = pointerLog[stepCount][1];
        System.out.println("first column: " + firstColumnIndex);
        System.out.println("second column: " + secondColumnIndex);
        stepCount ++;


        // Random random = new Random();
        // int col1Index = random.nextInt(0, 5);
        // int col2Index = random.nextInt(6, 10);

        ColumnBar col1 = (ColumnBar) columnsHBox.getChildren().get(firstColumnIndex);
        ColumnBar col2 = (ColumnBar) columnsHBox.getChildren().get(secondColumnIndex);

        col1.swap(col2, 0.3);
    }

    public void generateCustomArray() {
        String content = enterArrayTextField.getText();

        columnsHBox.getChildren().clear();
        InputParserUtils parser = new InputParserUtils();
        parser.setInput(content);

        // check for the constraint
        parser.parse();
        if (parser.getErrorState()) {
            // When the error occur, the user must type the input again
            System.out.println("Please type again");
        } else {
            arrayVal = parser.getArrayInput();
            obj = new BubbleSort(arrayVal);
            for (int value : arrayVal) {
                ColumnBar newColumn = new ColumnBar(value);
                columnsHBox.getChildren().add(newColumn);
            }

            // Update HBox Layout
            columnsHBox.layout();

        }


    }


    
}
