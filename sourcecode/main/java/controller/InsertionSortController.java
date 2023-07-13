package main.java.controller;

import java.util.Random;

import main.java.model.object.ColumnBar;
import main.java.model.vialgo_utils.InputParserUtils;

public class InsertionSortController extends SortController {

    public void swapping() {
        System.out.println("clicked");

        Random random = new Random();
        int col1Index = random.nextInt(0, 5);
        int col2Index = random.nextInt(6, 10);

        ColumnBar col1 = (ColumnBar) columnsHBox.getChildren().get(col1Index);
        ColumnBar col2 = (ColumnBar) columnsHBox.getChildren().get(col2Index);

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
            int[] arrayVal = parser.getArrayInput();
            for (int value : arrayVal) {
                ColumnBar newColumn = new ColumnBar(value);
                columnsHBox.getChildren().add(newColumn);
            }

            // Update HBox Layout
            columnsHBox.layout();

        }


    }
}
