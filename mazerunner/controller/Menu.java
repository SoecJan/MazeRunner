package mazerunner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import mazerunner.StageHandler;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jan on 18.07.2017.
 */
public class Menu extends AbstractController {

    @FXML
    private Slider mazeSizeSlider;
    @FXML
    private Slider speedSlider;
    @FXML
    private Slider canvasSizeSlider;
    @FXML
    private Label mazeSizeLabel;
    @FXML
    private Label speedLabel;
    @FXML
    private Label canvasSizeLabel;

    public Menu() {

    }

    @FXML
    public void onExit() {

    }

    @FXML
    public void onStart() {
        stageHandler.loadView(StageHandler.MAZERUNNER);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mazeSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mazeSizeLabel.setText(Double.toString(newValue.intValue()));
            MazeRunnerController.setSize(newValue.intValue());
        });
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            speedLabel.setText(Double.toString(newValue.intValue()));
            MazeRunnerController.setRunnerSpeed(newValue.longValue());
        });
        canvasSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            canvasSizeLabel.setText(Double.toString(newValue.intValue()));
            MazeRunnerController.setMinWindowDimension(newValue.doubleValue());
        });
    }
}
