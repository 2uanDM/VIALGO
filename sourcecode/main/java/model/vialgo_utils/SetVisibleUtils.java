package main.java.model.vialgo_utils;

import java.util.ArrayList;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class SetVisibleUtils {
    private ArrayList<Node> childComponents;

    public SetVisibleUtils(ArrayList<Node> childComponents) {
        this.childComponents = childComponents;
    }

    public void changeVisibleStatus(boolean leftSideBar, boolean visibleStatus, String animationType) {
        ParallelTransition parallelTransition = new ParallelTransition();

        if (visibleStatus == true) // show all the child components
        {
            for (Node node : childComponents) {
                setVisible(node, visibleStatus);
                if (animationType.equals("fade")) {
                    FadeTransition fadeTransition = createFadeTransition(node, visibleStatus);
                    parallelTransition.getChildren().add(fadeTransition);
                } else if (animationType.equals("translate")) {
                    TranslateTransition translateTransition = createTranslateTransition(node,
                            leftSideBar ? true : false, leftSideBar);
                    parallelTransition.getChildren().add(translateTransition);
                }
            }
        } else {
            for (Node node : childComponents) {
                if (animationType.equals("fade")) {
                    FadeTransition fadeTransition = createFadeTransition(node, visibleStatus);
                    parallelTransition.getChildren().add(fadeTransition);
                } else if (animationType.equals("translate")) {
                    TranslateTransition translateTransition = createTranslateTransition(node,
                            leftSideBar ? false : true, leftSideBar);
                    parallelTransition.getChildren().add(translateTransition);
                }
            }
        }

        // Ensure that the hiding animation finished after components disappear
        if (visibleStatus == false) {
            parallelTransition.setOnFinished(event -> {
                for (Node node : childComponents) {
                    setVisible(node, visibleStatus);
                }
            });
        }

        parallelTransition.play();
    }

    private FadeTransition createFadeTransition(Node node, boolean visible) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.2), node);
        fadeTransition.setFromValue(visible ? 0 : 1);
        fadeTransition.setToValue(visible ? 1 : 0);
        return fadeTransition;
    }

    private TranslateTransition createTranslateTransition(Node node, boolean leftToRight, boolean leftSideBar) {
        double nodeWidth = getWidth(node);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.3), node);

        double fromX, toX;
        if (leftSideBar) {
            fromX = leftToRight ? -nodeWidth : 0;
            toX = leftToRight ? 0 : -nodeWidth - 50;
        } else {
            fromX = leftToRight ? nodeWidth : 0;
            toX = leftToRight ? 0 : nodeWidth + 50;
        }

        translateTransition.setFromX(fromX);
        translateTransition.setToX(toX);
        translateTransition.setInterpolator(Interpolator.EASE_OUT);

        return translateTransition;
    }

    private void setVisible(Node component, boolean status) {
        if (component instanceof Button) {
            Button button = (Button) component;
            button.setVisible(status);
        } else if (component instanceof TextField) {
            TextField textField = (TextField) component;
            textField.setVisible(status);
        } else if (component instanceof TextArea) {
            TextArea textArea = (TextArea) component;
            textArea.setVisible(status);
        } else if (component instanceof ImageView) {
            ImageView imageView = (ImageView) component;
            imageView.setVisible(status);
        }
    }

    private double getWidth(Node component) {
        if (component instanceof Button) {
            Button button = (Button) component;
            return button.getWidth();
        } else if (component instanceof TextField) {
            TextField textField = (TextField) component;
            return textField.getWidth();
        } else if (component instanceof TextArea) {
            TextArea textArea = (TextArea) component;
            return textArea.getWidth();
        } else {
            // Handle other component types if needed
            return 0.0;
        }
    }
}
