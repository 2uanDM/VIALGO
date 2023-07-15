package main.java.model.vialgo_utils;

import java.util.ArrayList;

import javafx.scene.control.Label;

import main.java.model.exception.DataTypeException;
import main.java.model.exception.MinMaxValueException;
import main.java.model.exception.NullException;
import main.java.model.exception.NumberOfValueException;

public class InputParserUtils {

    private ArrayList<Integer> arrayValue = new ArrayList<Integer>();
    private String input;
    private Label exceptionLabel;

    // Constructor
    public InputParserUtils(Label exceptionLabel, String input) {
        this.exceptionLabel = exceptionLabel;
        this.input = input;
    }

    // Getter
    public ArrayList<Integer> getArrayValue() {
        this.parse();
        return this.arrayValue;
    }

    // Setter
    public void setInput(String input) {
        this.input = input;
    }

    public void parse() {
        // First, split the input
        String content = this.input;
        String[] splitedValues = content.split(",", -1);

        /*
         * Flow of catching errors from input:
         * Check empty input -> Check correct data type -> Check valid range -> Check
         * number of elements
         */

        try {
            // Check empty input ?
            if (content.isEmpty()) {
                throw new NullException("The array should not be empty.");
            }
    
    // first, remove all the space in the string
    int index = 0;
    for (String strPart: splitArray) {

        strPart = strPart.replaceAll("\\s", "");
        splitArray[index] = strPart;
        index ++;
    }

            // Check correct data type
            for (String value : splitedValues) {
                try {
                    Integer.parseInt(value.trim());
                } catch (NumberFormatException e) {
                    if (value == "") {
                        throw new DataTypeException(
                                "There seems to be a missing element (a duplicate comma somewhere perhaps?)");
                    } else {
                        throw new DataTypeException(
                                "There seems to be an invalid element (not an integer): " + value);
                    }
                }
            }

            // Check valid range
            for (String value : splitedValues) {
                int number = Integer.parseInt(value);
                if (number < 1 || number > 50) {
                    throw new MinMaxValueException("The values should be between 1 and 50: " + number);
                }
            }

            // Check number of elements
            if (splitedValues.length > 20) {
                throw new NumberOfValueException("The number of values should not exceed 20.");
            }

            arrayValue = getArrayInput();
        } catch (NumberOfValueException e) {
            // Display the exception message on the label
            exceptionLabel.setText(e.getMessage());
        } catch (NullException e) {
            exceptionLabel.setText(e.getMessage());
        } catch (MinMaxValueException e) {
            exceptionLabel.setText(e.getMessage());
        } catch (DataTypeException e) {
            exceptionLabel.setText(e.getMessage());
        }

    }

    private ArrayList<Integer> getArrayInput() {
        /*
         * Only call this function when ensure that there is no syntax error from input
         */
        String[] splitedArray = this.input.split(",");
        ArrayList<Integer> validNumberArray = new ArrayList<Integer>();

        for (String value : splitedArray) {
            // Convert to int datatype, then add to numberArray
            int number = Integer.parseInt(value.trim());
            validNumberArray.add(number);
        }

        return validNumberArray;
    }
}
