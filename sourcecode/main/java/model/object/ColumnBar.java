package main.java.model.object;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ColumnBar extends Rectangle {
    public static final double MAX_HEIGHT = 250.0;
    public static final double MIN_HEIGHT = 30.0;
    public static final double COL_WIDTH = 35.0;
    public static final double MAX_VALUE = 50.0;
    public static final double MIN_VALUE = 0.0;

    private Text valueText;

    public ColumnBar(double value) {
        this.setWidth(COL_WIDTH);
        this.setHeight(getHeight(value));
        this.setFill(Color.rgb(173, 216, 230));

        // Create text value at the bottom center of the column bar
        valueText = new Text(String.valueOf(value));
        valueText.setFont(Font.font(14));
        valueText.setX(this.getX() + (COL_WIDTH / 2) - (valueText.getLayoutBounds().getWidth() / 2));
        valueText.setY(this.getY());
    }

    public ColumnBar(double value, Color color) {
        this(value);
        this.setFill(color);
    }

    // public swap(ColumnBar column)

    private double getHeight(double value) {
        return value / MAX_VALUE * (MAX_HEIGHT - MIN_HEIGHT) + MIN_HEIGHT;
    }

    public Text getValueText() {
        return this.valueText;
    }
}
