package main.java.model.object;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
    public static final double MAX_VALUE = 50.0;
    public static final double MIN_VALUE = 0.0;

    private Text valueText;

    public ColumnBar(double value) {
        this.setWidth(COL_WIDTH);
        this.setHeight(getHeight(value));
        this.setFill(DEFAULT_COLOR);


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

    public void swap(ColumnBar columnBar, double duration) {
        double currentX = this.getLayoutX();
        double otherX = columnBar.getLayoutX();

        this.setFill(Color.BEIGE);
        columnBar.setFill(Color.BEIGE);

        // Create the color change animation
        Duration colorChangeDuration = Duration.seconds(0.3);
        Color newColor = Color.BEIGE;

        KeyValue thisColorKeyValue = new KeyValue(this.fillProperty(), newColor);
        KeyFrame thisColorKeyFrame = new KeyFrame(colorChangeDuration, thisColorKeyValue);
        Timeline thisColorTimeline = new Timeline(thisColorKeyFrame);

        KeyValue otherColorKeyValue = new KeyValue(columnBar.fillProperty(), newColor);
        KeyFrame otherColorKeyFrame = new KeyFrame(colorChangeDuration, otherColorKeyValue);
        Timeline otherColorTimeline = new Timeline(otherColorKeyFrame);

        // create the translation animation
        TranslateTransition thisTransition = new TranslateTransition(Duration.seconds(duration), this);
        thisTransition.setToX(otherX - currentX);
        thisTransition.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition otherTransition = new TranslateTransition(Duration.seconds(duration), columnBar);
        otherTransition.setToX(currentX - otherX);
        otherTransition.setInterpolator(Interpolator.EASE_BOTH);

        // Create the color change back transition
        Duration colorChangeBackDelay = Duration.seconds(0.55);

        PauseTransition delayTransition = new PauseTransition(colorChangeBackDelay);
        delayTransition.setOnFinished(event -> {
            KeyValue thisColorBackKeyValue = new KeyValue(this.fillProperty(), DEFAULT_COLOR);
            KeyFrame thisColorBackKeyFrame = new KeyFrame(colorChangeDuration, thisColorBackKeyValue);
            Timeline thisColorBackTimeline = new Timeline(thisColorBackKeyFrame);

            KeyValue otherColorBackKeyValue = new KeyValue(columnBar.fillProperty(), DEFAULT_COLOR);
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
        });

        thisColorTimeline.play();

    }

    private double getHeight(double value) {
        // log scale for accepting very large number
        double log_scale;
        log_scale = 36 * Math.log(value) + 10;
        return log_scale;
    }

    public Text getValueText() {
        return this.valueText;
    }
}
