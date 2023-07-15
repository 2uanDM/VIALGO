package main.java.model.vialgo_utils;

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.model.object.ColumnBar;

public class AnimationUtils {
    public static void fadeColor(ColumnBar changeColumn, Color newColor, double duration) {
        /*
         * This method is used for change the color of a list of ColumnBar to a
         * particular color with fade animation
         */
        Timeline changeColorTimeline = new Timeline();
        KeyValue colorKeyValue = new KeyValue(changeColumn.fillProperty(), newColor);
        KeyFrame colorKeyFrame = new KeyFrame(Duration.seconds(duration), colorKeyValue);
        changeColorTimeline.getKeyFrames().add(colorKeyFrame);

        changeColorTimeline.play();
    }

    public static void fadeColor(ArrayList<ColumnBar> changeColumns, Color newColor, double duration) {
        /*
         * This method is used for change the color of a list of ColumnBar to a
         * particular color with fade animation
         */
        Timeline changeColorTimeline = new Timeline();
        for (ColumnBar column : changeColumns) {
            KeyValue colorKeyValue = new KeyValue(column.fillProperty(), newColor);
            KeyFrame colorKeyFrame = new KeyFrame(Duration.seconds(duration), colorKeyValue);
            changeColorTimeline.getKeyFrames().add(colorKeyFrame);
        }
        changeColorTimeline.play();
    }

    public static void fadeColor(ArrayList<ColumnBar> changeColumns, Color newColor, double duration,
            Runnable callback) {
        /*
         * This method is used for change the color of a list of ColumnBar to a
         * particular color with fade animation. Callback function is an optional
         */
        Timeline changeColorTimeline = new Timeline();
        for (ColumnBar column : changeColumns) {
            KeyValue colorKeyValue = new KeyValue(column.fillProperty(), newColor);
            KeyFrame colorKeyFrame = new KeyFrame(Duration.seconds(duration), colorKeyValue);
            changeColorTimeline.getKeyFrames().add(colorKeyFrame);
        }

        changeColorTimeline.setOnFinished((event) -> {
            callback.run();
        });

        changeColorTimeline.play();
    }

    public static void moveVertical(ColumnBar columnBar, String direction, double duration, Runnable callback) {
        /*
         * This method is used for Insertion Sort, move down the ColumnBar to find the
         * right place to insert into
         */
        double distanceMove;
        if (direction == "down") {
            distanceMove = 100;
        } else {
            distanceMove = -100;
        }

        TranslateTransition moveTranslateTransition = new TranslateTransition(Duration.seconds(duration), columnBar);
        moveTranslateTransition.setByY(distanceMove);
        moveTranslateTransition.setInterpolator(Interpolator.EASE_BOTH);

        // Set the new postion for xCoordinate
        columnBar.setYCoordinate(columnBar.getYCoordinate() + distanceMove);

        moveTranslateTransition.setOnFinished((event) -> {
            callback.run();
        });

        moveTranslateTransition.play();
    }

}
