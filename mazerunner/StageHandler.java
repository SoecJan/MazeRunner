package mazerunner;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mazerunner.controller.AbstractController;

import java.io.IOException;

/**
 * Created by Jan on 18.07.2017.
 */
public class StageHandler {

    public static final int MENUE = 1;
    public static final int MAZERUNNER = 2;

    private Stage primaryStage;
    private AbstractController currentController;

    public StageHandler(Stage primaryStage) {
        this.primaryStage = primaryStage;
        AbstractController.setStageHandler(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public AbstractController getCurrentController() {
        return currentController;
    }

    private void loadNewController(String fxmlPath, String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlPath));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace(); //
        }
        // configure current controller that is also going to be stored in previousControllers
        this.currentController = loader.getController();
        getCurrentController().setScene(new Scene(root));
        getCurrentController().setTitle(title);
        // LoadControllerRunnable class can be found at the bottom of this class.
        Platform.runLater(new LoadControllerRunnable(getPrimaryStage(), getCurrentController(),
                getCurrentController().getScene(), title));
    }

    public void loadView(int constant) {

        switch (constant) {
            case MENUE:
//                loadFXML("views/MenueView.fxml");
                loadNewController("views/MenueView.fxml","Menue");
                break;
            case MAZERUNNER:
                loadNewController("views/MazeRunnerView.fxml","Maze MazeCreator");
                break;
        }
    }

    public void exit() {
        primaryStage.close();
    }

    static class LoadControllerRunnable implements Runnable {
        private Stage stage;
        private AbstractController controller;
        private Scene scene;
        private String title;
        /**
         *
         * @param stage the primaryStage
         * @param controller the new Controller
         * @param scene the new scene loaded onto the stage
         * @param title the windows' title
         */
        public LoadControllerRunnable(Stage stage, AbstractController controller, Scene scene, String title) {
            this.stage = stage;
            this.controller = controller;
            this.scene = scene;
            this.title = title;
        }

        public void run() {
            controller.transfer(stage);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        }
    }
}

//    /**
//     * This method lets us load previously initialized instances
//     * @param loadedController the controller to be loaded
//     */
//    private void loadPreviousController(AbstractController loadedController) {
//        LoadControllerRunnable runnable = new LoadControllerRunnable(getPrimaryStage(), loadedController,
//                loadedController.getScene(), loadedController.getTitle());
//        Platform.runLater(runnable);
//    }
