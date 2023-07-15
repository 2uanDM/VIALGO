package test;

import main.java.model.sorting_algo.BubbleSort;

public class testing {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j < 4; j++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(i + " " + j);
            }
        }
    }
}