package main.java.model.object;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

    // Constructor
    public ColumnBar(int value) {
        this.setWidth(COL_WIDTH);
        this.setHeight(getHeight(value));
        this.setFill(DEFAULT_COLOR);
        this.value = value;

        // Create text value at the bottom center of the column bar
        valueText = new Text(String.valueOf(value));
        valueText.setFont(Font.font(14));
        valueText.setX(this.getX() + (COL_WIDTH / 2) - (valueText.getLayoutBounds().getWidth() / 2));
        valueText.setY(this.getY());
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

    private double getHeight(int value) {
        // log scale for accepting very large number
        double log_scale;
        log_scale = 36 * Math.log(value) + 10;
        return log_scale;
    }

    // Setter
    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void swap(ColumnBar otherColumn, double duration, ArrayList<ColumnBar> columns) {
        double translateDistance1 = otherColumn.getXCoordinate() - this.getXCoordinate();
        double translateDistance2 = -translateDistance1;

        // this.setFill(Color.GREEN);
        // otherColumn.setFill(Color.GREEN);

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
        thisTransition.setByX(translateDistance1);
        thisTransition.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition otherTransition = new TranslateTransition(Duration.seconds(duration), otherColumn);
        otherTransition.setByX(translateDistance2);
        otherTransition.setInterpolator(Interpolator.EASE_BOTH);

        // Create the color change back transition
        Duration colorChangeBackDelay = Duration.seconds(0.55);

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
            delayTransition.play();

            // Swap xCoordinate properties
            this.swapXCoordinate(otherColumn);

            // Swap inside array
            Collections.swap(columns, columns.indexOf(this), columns.indexOf(otherColumn));
        });

        otherColorTimeline.play();
        thisColorTimeline.play();

    }

    private void swapXCoordinate(ColumnBar otherColumnBar) {
        double tmp = this.getXCoordinate();
        this.setXCoordinate(otherColumnBar.getXCoordinate());
        otherColumnBar.setXCoordinate(tmp);
    }
}
