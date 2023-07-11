package main.java.model.vialgo_utils;

import java.util.ArrayList;

public class InputParserUtils {

    private ArrayList<Double> arrayValue = new ArrayList<Double>();
    private String input;

    public ArrayList<Double> getArrayValue() {
        this.parse();
        return this.arrayValue;
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    private void parse() {
        // Ngọc Quân code here
    }

    public static void main(String[] args) {
        InputParserUtils parser = new InputParserUtils();
        String test1 = "29, 40 ;1,48, 43;a, 0";
        // There seems to be an invalid element (not a number): 40 ;1
        String test2 = " 29, ,40 ;1,48, 43;a, 0   ";
        // There seems to be a missing element (a duplicate comma somewhere perhaps?)
        String test3 = " 1,    36, 8, 3, 5";
        // [1,36,8,3,5]
        String test4 = "1,5,7,4,23";
        // [1,5,7,4,23]
        String test5 = "-1,5,8,2";
        // Sorry, you're restricted to values between 1 and 50 inclusive.(Out of range
        // number: -1.)

        parser.setInput(test1);
        System.out.println(parser.getArrayValue());
    }
}
