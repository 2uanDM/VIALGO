package main.java.model.object;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.java.controller.SortController;

public class ColumnBar extends Rectangle {
    public static final Color DEFAULT_COLOR = Color.rgb(173, 216, 230);
    public static final double MAX_HEIGHT = 250.0;
    public static final double MIN_HEIGHT = 30.0;
    public static final double COL_WIDTH = 40.0;
    public static final double MAX_VALUE = 50.0;
    public static final double MIN_VALUE = 0.0;

    private Text valueText;
    private double value;
    private int indexInsideHBox;
    private double xCoordinate;

    public ColumnBar(double value) {
        this.setWidth(COL_WIDTH);
        this.setHeight(getHeight(value));
        this.setFill(DEFAULT_COLOR);
        this.value = value;

        // Create text value at the bottom center of the column bar and wrap it in a
        // group
        valueText = new Text(String.valueOf(value));
        valueText.setFont(Font.font(18));
        double textWidth = valueText.getLayoutBounds().getWidth();
        double textHeight = valueText.getLayoutBounds().getHeight();
        double textX = this.getX() + (COL_WIDTH / 2) - (textWidth / 2);
        double textY = this.getY() + getHeight(value) + textHeight;
        valueText.setX(textX);
        valueText.setY(textY);

        // Wrap the rectangle and text in a group
        Node[] nodes = new Node[] { this, valueText };
        Group group = new Group(nodes);
    }

    public ColumnBar(double value, Color color) {
        this(value);
        this.setFill(color);
    }

    public Text getValueText() {
        return this.valueText;
    }

    public double getValue() {
        return (int) Math.round(this.value);
    }

    public void setIndexInsideHBox(int index) {
        this.indexInsideHBox = index;
    }

    public int getIndexInsideHBox() {
        return this.indexInsideHBox;
    }

    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getXCoordinate() {
        return this.xCoordinate;
    }

    public void swap(ColumnBar otherColumn, double duration, ArrayList<ColumnBar> columns) {
        double translateDistance1 = otherColumn.getXCoordinate() - this.getXCoordinate();
        double translateDistance2 = -translateDistance1;

        TranslateTransition thisTransition = new TranslateTransition(Duration.seconds(duration), this);
        thisTransition.setByX(translateDistance1);
        thisTransition.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition otherTransition = new TranslateTransition(Duration.seconds(duration), otherColumn);
        otherTransition.setByX(translateDistance2);
        otherTransition.setInterpolator(Interpolator.EASE_BOTH);

        ParallelTransition both = new ParallelTransition(thisTransition, otherTransition);

        both.play();

        both.setOnFinished(event -> {

            // this.setFill(DEFAULT_COLOR);
            // otherColumn.setFill(DEFAULT_COLOR);

            // Swap xCoordinate properties
            this.swapXCoordinate(otherColumn);

            // Swap inside array
            Collections.swap(columns, columns.indexOf(this), columns.indexOf(otherColumn));

            for (ColumnBar col : columns) {
                System.out.print(col.getValue() + " ");
            }
            System.out.println();
        });

    }

    private double getHeight(double value) {
        return value / MAX_VALUE * (MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT;
    }

    private void swapXCoordinate(ColumnBar otherColumnBar) {
        double tmp = this.getXCoordinate();
        this.setXCoordinate(otherColumnBar.getXCoordinate());
        otherColumnBar.setXCoordinate(tmp);
    }

}
