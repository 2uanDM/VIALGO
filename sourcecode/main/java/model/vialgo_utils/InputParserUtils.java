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
        // first, split the input
        String[] splitArray = this.input.split(",");
        int index = 0;
        for (String strPart: splitArray) {

            // first, remove all the space in the string
            strPart = strPart.replaceAll("\\s", "");
            splitArray[index] = strPart;
            index ++;
        }
// Now we ensure that input is splited by ",", store them in an array, and not exists space in each element

        for (String strPart: splitArray) {
            try {
                int number = Integer.parseInt(strPart);
            } catch(Exception e) {
                System.out.println("Check your input, remember that only type integer value, each integer is splited by a ','. Check near the part contain: " + e.getMessage());
            } 

        }
// Here, we ensure that there is not any syntax error from user. But we still need more constraint about the value of integer.


        // for testing
        // for (String i: splitArray) {

        //     int number = Integer.parseInt(i);
        //     // System.out.println(number);  
        // }
    }

    public static void main(String[] args) {
        InputParserUtils parser = new InputParserUtils();
        String test = "0, 1, 2, 3, 4, 5";
        String test1 = "29, ,40 ;1,48, 43;a, 0";
        // There seems to be an invalid element (not a number): 40 ;1
        String test2 = " 29 ,40 ;1,48, 43;a, 0   ";
        // There seems to be a missing element (a duplicate comma somewhere perhaps?)
        String test3 = " 1,    36, 8, 3, 5";
        // [1,36,8,3,5]
        String test4 = "1,5,7,4,23";
        // [1,5,7,4,23]
        String test5 = "-1,5,8,2";
        // Sorry, you're restricted to values between 1 and 50 inclusive.(Out of range
        // number: -1.)

        parser.setInput(test2);
        System.out.println(parser.getArrayValue());
    }
}
