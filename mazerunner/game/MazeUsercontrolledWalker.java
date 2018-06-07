package mazerunner.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JanS on 19.07.2017.
 */
public class MazeUsercontrolledWalker implements IRender {

    private MazeRunnerHandler mazeRunnerHandler;
    private Maze maze;
    private MazeCell currentPosition;
    private MazeCell target;
    private MazeCell start;
    private List<MazeCell> path;

    public MazeUsercontrolledWalker(MazeRunnerHandler mazeRunnerHandler, MazeCell start, MazeCell target) {
        this.mazeRunnerHandler = mazeRunnerHandler;
        this.maze = mazeRunnerHandler.getMaze();
        this.target = target;
        this.start = start;
        this.currentPosition = new MazeCell(start.getX(), start.getY(), start.getDimension());
        this.path = new ArrayList<>();
        this.path.add(start);

        this.mazeRunnerHandler.getCanvas().setOnMouseClicked(event -> {
            int clickedX = (int) (event.getX()/this.maze.getDimension());
            int clickedY = (int) (event.getY()/this.maze.getDimension());
            moveTo(this.maze.getMaze()[clickedX][clickedY]);
        });
    }

    private void moveTo(MazeCell cell) {
        if (cell.getX() == currentPosition.getX() && cell.getY() > currentPosition.getY()) {
            // Nach Unten
            for (int i = currentPosition.getY(); i <= cell.getY(); i++) {
                currentPosition.setY(i);
            }
        } else if (cell.getX() == currentPosition.getX() && cell.getY() < currentPosition.getY()) {
            // Nach Oben#
            for (int i = currentPosition.getY(); i >= cell.getY(); i--) {
                currentPosition.setY(i);
            }
        } else if (cell.getY() == currentPosition.getY() && cell.getX() > currentPosition.getX()) {
            // Nach Rechts
            for (int i = currentPosition.getX(); i <= cell.getX(); i++) {
                currentPosition.setX(i);
            }
        } else if (cell.getY() == currentPosition.getY() && cell.getX() < currentPosition.getX()) {
            // Nach Links
            for (int i = currentPosition.getX(); i >= cell.getX(); i--) {
                currentPosition.setX(i);
            }
        }
//        this.currentPosition = target;
        if (this.currentPosition.getX() == this.target.getX() && this.currentPosition.getY() == this.target.getY()) {
            this.mazeRunnerHandler.handleWalkerFinished();
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.ORANGERED);
        graphicsContext.fillRect(start.getX(),start.getY(), this.maze.getDimension(), this.maze.getDimension());
        graphicsContext.setFill(Color.LIGHTBLUE);
        graphicsContext.fillRect(target.getX() * this.maze.getDimension(), target.getY() * this.maze.getDimension(), this.maze.getDimension(), this.maze.getDimension());
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(this.currentPosition.getX() * this.maze.getDimension(), this.currentPosition.getY() * this.maze.getDimension(), this.maze.getDimension(), this.maze.getDimension());
    }
}
