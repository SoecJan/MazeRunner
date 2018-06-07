package mazerunner;

/**
 * Created by Jan on 18.07.2017.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import mazerunner.controller.MazeRunnerController;

public class Main extends Application {

    private StageHandler stageHandler;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.stageHandler = new StageHandler(primaryStage);
        this.stageHandler.loadView(StageHandler.MENUE);

        primaryStage.setTitle("MazeRunnerController");
        primaryStage.show();
    }

    @Override
    public void stop(){
        if (this.stageHandler.getCurrentController() instanceof MazeRunnerController) {
            ((MazeRunnerController)this.stageHandler.getCurrentController()).getMazeRunner().getMazeCreator().getThread().interrupt();
        }
    }
}
