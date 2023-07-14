package main.java.model.object;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.java.controller.SortController;

public class ColumnBar extends Rectangle {

    public static final Color DEFAULT_COLOR = Color.rgb(173, 216, 230);
    public static final double MAX_HEIGHT = 250.0;
    public static final double MIN_HEIGHT = 30.0;
    public static final double COL_WIDTH = 40.0;
    public static final int MAX_VALUE = 50;
    public static final int MIN_VALUE = 0;

    private Text valueText;
    private int value;
    private double xCoordinate;
    private double yCoordinate;

    // Constructor
    public ColumnBar(int value) {
        this.setWidth(COL_WIDTH);
        this.setHeight(getHeight(value));
        this.setFill(DEFAULT_COLOR);
        this.value = value;
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

    // Setter
    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Text getTextvalue() {
        // Create text value at the bottom center of the column bar
        valueText = new Text(String.valueOf(this.value));
        valueText.setFont(Font.font("Roboto", FontWeight.MEDIUM, 18));
        valueText.setFill(Color.BLACK);
        valueText.setLayoutX(this.xCoordinate + this.getWidth() / 2 - valueText.getLayoutBounds().getWidth() / 2);
        valueText.setLayoutY(this.yCoordinate + this.getHeight() - 3);

        System.out.println(valueText.getLayoutX() + " " + valueText.getLayoutY());
        return valueText;
    }

    public void swap(ColumnBar otherColumn, double duration, ArrayList<ColumnBar> columns,
            ArrayList<Text> textValues) {
        System.out.println("Before");
        for (Text text : textValues) {
            System.out.print(text.getText() + " ");
        }
        System.out.println();

        double columnDistance1 = otherColumn.getXCoordinate() - this.getXCoordinate();
        double columnDistance2 = -columnDistance1;

        Text thisText = textValues.get(columns.indexOf(this));
        Text otherText = textValues.get(columns.indexOf(otherColumn));

        double thisTextX = thisText.getLayoutX();
        double otherTextX = otherText.getLayoutX();

        double textDistance1 = (otherTextX + (otherText.getLayoutBounds().getWidth() / 2))
                - (thisTextX + (thisText.getLayoutBounds().getWidth() / 2));
        double textDistance2 = -textDistance1;

        // Create the color change animation
        Duration colorChangeDuration = Duration.seconds(0.3);
        Color newColor = Color.GREEN;

        KeyValue thisColorKeyValue = new KeyValue(this.fillProperty(), newColor);
        KeyFrame thisColorKeyFrame = new KeyFrame(colorChangeDuration, thisColorKeyValue);
        Timeline thisColorTimeline = new Timeline(thisColorKeyFrame);

        KeyValue otherColorKeyValue = new KeyValue(otherColumn.fillProperty(), newColor);
        KeyFrame otherColorKeyFrame = new KeyFrame(colorChangeDuration, otherColorKeyValue);
        Timeline otherColorTimeline = new Timeline(otherColorKeyFrame);

        // create the translation animation
        TranslateTransition thisTransition = new TranslateTransition(Duration.seconds(duration), this);
        thisTransition.setByX(columnDistance1);
        thisTransition.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition otherTransition = new TranslateTransition(Duration.seconds(duration), otherColumn);
        otherTransition.setByX(columnDistance2);
        otherTransition.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition thisTextTransition = new TranslateTransition(Duration.seconds(duration), thisText);
        thisTextTransition.setByX(textDistance1);
        thisTextTransition.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition otherTextTransition = new TranslateTransition(Duration.seconds(duration), otherText);
        otherTextTransition.setByX(textDistance2);
        otherTextTransition.setInterpolator(Interpolator.EASE_BOTH);

        // Create the color change back transition
        Duration colorChangeBackDelay = Duration.seconds(0.5);

        PauseTransition delayTransition = new PauseTransition(colorChangeBackDelay);

        delayTransition.setOnFinished(event -> {
            KeyValue thisColorBackKeyValue = new KeyValue(this.fillProperty(), DEFAULT_COLOR);
            KeyFrame thisColorBackKeyFrame = new KeyFrame(colorChangeDuration, thisColorBackKeyValue);
            Timeline thisColorBackTimeline = new Timeline(thisColorBackKeyFrame);

            KeyValue otherColorBackKeyValue = new KeyValue(otherColumn.fillProperty(), DEFAULT_COLOR);
            KeyFrame otherColorBackKeyFrame = new KeyFrame(colorChangeDuration, otherColorBackKeyValue);
            Timeline otherColorBackTimeline = new Timeline(otherColorBackKeyFrame);

            thisColorBackTimeline.play();
            otherColorBackTimeline.play();
        });

        // Play the color change animations
        thisColorTimeline.setOnFinished(event -> {
            thisTransition.play();
            otherTransition.play();
            thisTextTransition.play();
            otherTextTransition.play();
            delayTransition.play();

            // Swap xCoordinate properties
            this.swapColumnXCoordinate(otherColumn);
            // swapTextXCoordinate(thisText, otherText);

            // Swap inside array
            Collections.swap(columns, columns.indexOf(this), columns.indexOf(otherColumn));
            Collections.swap(textValues, textValues.indexOf(thisText), textValues.indexOf(otherText));

            System.out.println("After");
            for (Text text : textValues) {
                System.out.print(text.getText() + " ");
            }
            System.out.println();

        });

        otherColorTimeline.play();
        thisColorTimeline.play();

    }

    private void swapColumnXCoordinate(ColumnBar otherColumnBar) {
        double tmp = this.getXCoordinate();
        this.setXCoordinate(otherColumnBar.getXCoordinate());
        otherColumnBar.setXCoordinate(tmp);
    }

    private void swapTextXCoordinate(Text thisText, Text otherText) {
        double tmp = thisText.getLayoutX();
        thisText.setLayoutX(otherText.getLayoutX());
        otherText.setLayoutX(tmp);
    }
}
