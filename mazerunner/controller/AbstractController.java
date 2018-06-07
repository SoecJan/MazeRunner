package mazerunner.controller;

import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mazerunner.StageHandler;

/**
 * Created by JanS on 18.07.2017.
 */
public abstract class AbstractController implements Initializable {
    /* Attributes*/
    protected static StageHandler stageHandler;
    protected String title;
    protected Scene scene;
    private static double width = 550;
    private static double height = 350;

    /*Constructor*/
    public AbstractController() {
    }

    /* Methods */

    /**
     * Transfermethods from parent->child
     *
     * @param stage
     */
    public void transfer(Stage stage) {
        stage.setMaximized(false);
        stage.setMinWidth(getWidth());
        stage.setMinHeight(getHeight());
        stage.setWidth(getWidth());
        stage.setHeight(getHeight());
        //this.setCentered(stage);
    }

    /**
     * Sets the GUI-Window centred on the Screen
     * http://stackoverflow.com/questions/29558449/javafx-center-stage-on-screen
     *
     * @param primaryStage
     */
    protected void setCentered(Stage primaryStage) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    /* Getter & Setter */

    /**
     * Getter
     *
     * @return this.scene
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Setter
     *
     * @param scene
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Getter
     *
     * @return this.title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter
     *
     * @return AbstractController.stageHandler
     */
    public static StageHandler getStageHandler() {
        return AbstractController.stageHandler;
    }

    /**
     * Setter
     *
     * @param stageHandler
     */
    public static void setStageHandler(StageHandler stageHandler) {
        AbstractController.stageHandler = stageHandler;
    }

    /**
     * Getter
     *
     * @return AbstractController.width
     */
    public static double getWidth() {
        return AbstractController.width;
    }

    /**
     * Setter
     *
     * @param width
     */
    public static void setWidth(double width) {
        AbstractController.width = width;
    }

    /**
     * Getter
     *
     * @return AbstractController.height
     */
    public static double getHeight() {
        return AbstractController.height;
    }

    /**
     * Setter
     *
     * @param height
     */
    public static void setHeight(double height) {
        AbstractController.height = height;
    }
}