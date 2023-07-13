package main.java.controller;

import java.util.Random;

import main.java.model.object.ColumnBar;

public class BubbleSortController extends SortController {

    public void swapping() {
        System.out.println("clicked");

        for (int i: arrayVal) {
            System.out.println(i);
        }
        Random random = new Random();
        int col1Index = random.nextInt(0, 5);
        int col2Index = random.nextInt(6, 10);

        ColumnBar col1 = (ColumnBar) columnsHBox.getChildren().get(col1Index);
        ColumnBar col2 = (ColumnBar) columnsHBox.getChildren().get(col2Index);

        col1.swap(col2, 0.3);
    }


    
}
