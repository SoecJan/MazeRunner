package mazerunner.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mazerunner.game.MazeRunnerHandler;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jan on 18.07.2017.
 */
public class MazeRunnerController extends AbstractController {

    public static double minWindowDimension = 900;
    public static int size = 100;
    public static long runnerSpeed = 20;


    @FXML
    private BorderPane borderpane;

    private Canvas canvas;
    private MazeRunnerHandler mazeRunner;

    public MazeRunnerController() {

    }

    @Override
    public void transfer(Stage stage) {
        stage.setMinWidth(minWindowDimension+25);
        stage.setMinHeight(minWindowDimension+50);
    }

    public static void setMinWindowDimension(double minWindowDimension) {
        MazeRunnerController.minWindowDimension = minWindowDimension;
    }

    public static void setSize(int size) {
        MazeRunnerController.size = size;
    }

    public static void setRunnerSpeed(long runnerSpeed) {
        MazeRunnerController.runnerSpeed = runnerSpeed;
    }

    public static double getMinWindowDimension() {
        return minWindowDimension;
    }

    public static int getSize() {
        return size;
    }

    public static long getRunnerSpeed() {
        return runnerSpeed;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public MazeRunnerHandler getMazeRunner() {
        return mazeRunner;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.canvas = new Canvas(minWindowDimension, minWindowDimension);
        this.mazeRunner = new MazeRunnerHandler(canvas, size, runnerSpeed);

        this.borderpane.setCenter(canvas);
    }
}
