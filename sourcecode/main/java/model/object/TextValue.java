package main.java.model.object;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TextValue extends Text {
    private double xCoordinate;
    private double yCoordinate;

    public TextValue(int value, ColumnBar column) {
        // Create text value at the bottom center of the column bar
        super(String.valueOf(value));
        this.setFont(Font.font("Roboto", FontWeight.MEDIUM, 18));
        this.setFill(Color.BLACK);

        // Position
        this.setLayoutX(column.getXCoordinate() + column.getWidth() / 2 - this.getLayoutBounds().getWidth() / 2);
        this.setLayoutY(column.getYCoordinate() + column.getHeight() - 3);

        this.setXCoordinate(this.getLayoutX());
        this.setYCoordinate(this.getLayoutY());
    }

    // Setter
    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    // Getter
    public double getXCoordinate() {
        return this.xCoordinate;
    }

    public double getYCoordinate() {
        return this.yCoordinate;
    }

    public void swap(TextValue otherText, double duration, ArrayList<TextValue> textValues, Runnable callback) {
        double thisTextX = this.getXCoordinate();
        double otherTextX = otherText.getXCoordinate();

        double textDistance1 = (otherTextX + (otherText.getLayoutBounds().getWidth() / 2))
                - (thisTextX + (this.getLayoutBounds().getWidth() / 2));
        double textDistance2 = -textDistance1;

        TranslateTransition thisTextTransition = new TranslateTransition(Duration.seconds(duration), this);
        thisTextTransition.setByX(textDistance1);
        thisTextTransition.setInterpolator(Interpolator.EASE_BOTH);

        TranslateTransition otherTextTransition = new TranslateTransition(Duration.seconds(duration), otherText);
        otherTextTransition.setByX(textDistance2);
        otherTextTransition.setInterpolator(Interpolator.EASE_BOTH);

        thisTextTransition.setOnFinished(e -> {
            callback.run();
        });

        // Swap xCoordinate properties
        this.swapTextXCoordinate(otherText, textDistance1, textDistance2);

        // Swap inside array
        Collections.swap(textValues, textValues.indexOf(this), textValues.indexOf(otherText));

        thisTextTransition.play();
        otherTextTransition.play();
    }

    private void swapTextXCoordinate(TextValue otherText, double textDistance1, double textDistance2) {
        double newPos1 = this.getXCoordinate() + textDistance1;
        double newPos2 = otherText.getXCoordinate() + textDistance2;
        this.setXCoordinate(newPos1);
        otherText.setXCoordinate(newPos2);
    }
}
