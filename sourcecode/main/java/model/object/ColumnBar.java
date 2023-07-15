package main.java.model.object;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ColumnBar extends Rectangle {

    public static final Color DEFAULT_COLOR = Color.rgb(173, 216, 230);
    public static final double MAX_HEIGHT = 250.0;
    public static final double MIN_HEIGHT = 30.0;
    public static final double COL_WIDTH = 40.0;
    public static final int MAX_VALUE = 50;
    public static final int MIN_VALUE = 0;

    private TextValue textValue;
    private int value;
    private double xCoordinate;
    private double yCoordinate;

    // Constructor
    public ColumnBar(int value) {
        this.value = value;
        this.setWidth(COL_WIDTH);
        this.setHeight(getHeight(value));
        this.setFill(DEFAULT_COLOR);
    }

    public ColumnBar(int value, Color color) {
        this(value);
        this.setFill(color);
    }

    // Getter
    public int getValue() {
        return this.value;
    }

    public double getXCoordinate() {
        return this.xCoordinate;
    }

    public double getYCoordinate() {
        return this.yCoordinate;
    }

    private double getHeight(int value) {
        return ((double) value) / MAX_VALUE * (MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT;
    }

    public TextValue getTextValue() {
        return this.textValue;
    }

    // Setter
    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setTextValue(TextValue textValue) {
        this.textValue = textValue;
    }

    public void swap(ColumnBar otherColumn, double duration, ArrayList<ColumnBar> columns,
            ArrayList<TextValue> textValues, Runnable callback) {
        double columnDistance1 = otherColumn.getXCoordinate() - this.getXCoordinate();
        double columnDistance2 = -columnDistance1;

        // create the translation animation
        TranslateTransition thisTransition = new TranslateTransition(Duration.seconds(duration), this);
        thisTransition.setByX(columnDistance1);
        thisTransition.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition otherTransition = new TranslateTransition(Duration.seconds(duration), otherColumn);
        otherTransition.setByX(columnDistance2);
        otherTransition.setInterpolator(Interpolator.EASE_BOTH);

        // set up a callback function to be executed when the animation is finished
        thisTransition.setOnFinished(event -> {
            callback.run();
        });

        // Perfrom xCoordinate properties
        this.getTextValue().swap(otherColumn.getTextValue(), duration, textValues, callback);

        // Swap xCoordinate properties
        this.swapColumnXCoordinate(otherColumn);

        // Swap inside array
        Collections.swap(columns, columns.indexOf(this), columns.indexOf(otherColumn));

        thisTransition.play();
        otherTransition.play();
    }

    private void swapColumnXCoordinate(ColumnBar otherColumnBar) {
        double tmp = this.getXCoordinate();
        this.setXCoordinate(otherColumnBar.getXCoordinate());
        otherColumnBar.setXCoordinate(tmp);
    }
}
