package main.java.model.vialgo_utils;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.model.object.ColumnBar;

public class AnimationUtils {
    public static void fadeColor(ArrayList<ColumnBar> changeColumns, Color newColor, double duration) {
        Timeline changeColorTimeline = new Timeline();
        for (ColumnBar column : changeColumns) {
            KeyValue colorKeyValue = new KeyValue(column.fillProperty(), newColor);
            KeyFrame colorKeyFrame = new KeyFrame(Duration.seconds(duration), colorKeyValue);
            changeColorTimeline.getKeyFrames().add(colorKeyFrame);
        }
        changeColorTimeline.play();
    }

    public static void fadeColor(ColumnBar column, Color newColor, double duration) {
        Timeline changeColorTimeline = new Timeline();
        KeyValue colorKeyValue = new KeyValue(column.fillProperty(), newColor);
        KeyFrame colorKeyFrame = new KeyFrame(Duration.seconds(duration), colorKeyValue);
        changeColorTimeline.getKeyFrames().add(colorKeyFrame);

        changeColorTimeline.play();
    }
}
