package mazerunner.game;

import javafx.scene.canvas.Canvas;
import mazerunner.controller.MazeRunnerController;

/**
 * Created by Jan on 18.07.2017.
 */
public class MazeRunnerHandler {

    private Canvas canvas;
    private Renderer renderer;
    private Maze maze;
    private MazeCreator mazeCreator;
    private MazeUsercontrolledWalker mazeRunner;

    public MazeRunnerHandler(Canvas canvas, int size, long runnerSpeed) {
        this.canvas = canvas;
        this.maze = new Maze(size, (float)canvas.getWidth()/size);
        this.mazeCreator = new MazeCreator(size/2,size/2, this);
        this.renderer = new Renderer(canvas, mazeCreator);
        fillRenderer();
        this.renderer.start();
        this.mazeCreator.setSpeed(runnerSpeed);
        this.mazeCreator.getThread().start();
    }

    private void fillRenderer() {
        this.renderer.addEntity(this.mazeCreator);
        this.renderer.addEntity(this.maze);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Maze getMaze() {
        return maze;
    }

    public MazeCreator getMazeCreator() {
        return mazeCreator;
    }

    public void handleMazeDone() {
        this.renderer.removeEntity(this.mazeCreator);
        this.mazeRunner = new MazeUsercontrolledWalker(this, this.maze.getMaze()[0][0], this.maze.getMaze()[MazeRunnerController.size-1][MazeRunnerController.size-1]);
        this.renderer.addEntity(this.mazeRunner);
    }

    public void handleWalkerFinished() {
        this.renderer.removeEntity(this.mazeRunner);
        this.renderer.removeEntity(this.maze);
        this.maze = new Maze(MazeRunnerController.size, (float)canvas.getWidth()/MazeRunnerController.size);
        this.mazeCreator = new MazeCreator(MazeRunnerController.size/2,MazeRunnerController.size/2, this);
        this.renderer.addEntity(this.maze);
        this.renderer.addEntity(this.mazeCreator);
        this.mazeCreator.setSpeed(MazeRunnerController.runnerSpeed);
        this.mazeCreator.getThread().start();
    }
}
