package mazerunner.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 18.07.2017.
 */
public class MazeCreator implements IRender {
    private int x;
    private int y;
    private float dimension;
    private MazeRunnerHandler mazeRunnerHandler;
    private Maze maze;
    private RunnerThread thread;
    private List<MazeCell> visited;
    private long speed;

    public MazeCreator(int x, int y, MazeRunnerHandler mazeHandler) {
        this.x = x;
        this.y = y;
        this.mazeRunnerHandler = mazeHandler;
        this.maze = mazeHandler.getMaze();
        this.dimension = mazeHandler.getMaze().getDimension();
        this.visited = new ArrayList<>();
        this.thread = new RunnerThread(this);
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getDimension() {
        return dimension;
    }

    public Maze getMaze() {
        return maze;
    }

    public RunnerThread getThread() {
        return thread;
    }

    public void render(GraphicsContext graphicsContext) {

        graphicsContext.setFill(Color.LIGHTBLUE);
        graphicsContext.fillRect(this.x * this.dimension, this.y * this.dimension, this.dimension, this.dimension);

//            graphicsContext.setFill(Color.ORANGERED);
//            graphicsContext.fillRect(0, 0, this.dimension, this.dimension);
//            graphicsContext.setFill(Color.LIGHTBLUE);
//            graphicsContext.fillRect((this.maze.getSize()-1) * this.dimension, (this.maze.getSize()-1) * this.dimension, this.dimension, this.dimension);
    }

    public void move() {
        double random = Math.random();

        int oldX = this.x;
        int oldY = this.y;

        visited.add(this.maze.getMaze()[this.x][this.y]);

        if (random < 0.25 && this.x > 0 && !visited.contains(this.maze.getMaze()[this.x - 1][this.y])) {
            // Nach links
            this.x -= 1;
            this.maze.getMaze()[oldX][oldY].removeEdge(MazeCell.LEFT);
            this.maze.getMaze()[this.x][this.y].removeEdge(MazeCell.RIGHT);
        } else if (random < 0.5 && this.x < maze.getSize() - 1 && !visited.contains(this.maze.getMaze()[this.x + 1][this.y])) {
            // Nach rechts
            this.x += 1;
            this.maze.getMaze()[oldX][oldY].removeEdge(MazeCell.RIGHT);
            this.maze.getMaze()[this.x][this.y].removeEdge(MazeCell.LEFT);
        } else if (random < 0.75 && this.y > 0 && !visited.contains(this.maze.getMaze()[this.x][this.y - 1])) {
            // Nach oben
            this.y -= 1;
            this.maze.getMaze()[oldX][oldY].removeEdge(MazeCell.TOP);
            this.maze.getMaze()[this.x][this.y].removeEdge(MazeCell.BOTTOM);
        } else if (this.y < maze.getSize() - 1 && !visited.contains(this.maze.getMaze()[this.x][this.y + 1])) {
            // Nach unten
            this.y += 1;
            this.maze.getMaze()[oldX][oldY].removeEdge(MazeCell.BOTTOM);
            this.maze.getMaze()[this.x][this.y].removeEdge(MazeCell.TOP);
        }
        if (// Rechts
                (this.x == 0 && this.y == 0) ||
                        (this.x == this.maze.getSize() - 1 && this.y == 0) ||
                        (this.x == 0 && this.y == this.maze.getSize() - 1) ||
                        (this.x == this.maze.getSize() - 1 && this.y == this.maze.getSize() - 1) ||
                        (this.x == this.maze.getSize() - 1 && this.y > 0 && this.y < this.maze.getSize()
                                && this.visited.contains(maze.getMaze()[this.x - 1][this.y])
                                && this.visited.contains(maze.getMaze()[this.x][this.y - 1])
                                && this.visited.contains(maze.getMaze()[this.x][this.y + 1])) ||
                        // Links
                        (this.x == 0 && this.y > 0 && this.y < this.maze.getSize()
                                && this.visited.contains(maze.getMaze()[this.x + 1][this.y])
                                && this.visited.contains(maze.getMaze()[this.x][this.y - 1])
                                && this.visited.contains(maze.getMaze()[this.x][this.y + 1])) ||
                        // Unten
                        (this.x > 0 && this.x < this.maze.getSize() - 1 && this.y == this.maze.getSize() - 1
                                && this.visited.contains(maze.getMaze()[this.x - 1][this.y])
                                && this.visited.contains(maze.getMaze()[this.x + 1][this.y])
                                && this.visited.contains(maze.getMaze()[this.x][this.y - 1])) ||
                        // Oben
                        (this.x > 0 && this.x < this.maze.getSize() - 1 && this.y == 0
                                && this.visited.contains(maze.getMaze()[this.x - 1][this.y])
                                && this.visited.contains(maze.getMaze()[this.x + 1][this.y])
                                && this.visited.contains(maze.getMaze()[this.x][this.y + 1])) ||
                        // Mitte des Feldes
                        (this.x > 0 && this.x < this.maze.getSize() - 1 && this.y > 0 && this.y < this.maze.getSize() - 1
                                && this.visited.contains(maze.getMaze()[this.x - 1][this.y])
                                && this.visited.contains(maze.getMaze()[this.x + 1][this.y])
                                && this.visited.contains(maze.getMaze()[this.x][this.y - 1])
                                && this.visited.contains(maze.getMaze()[this.x][this.y + 1]))) {
            backtrack();
        }
    }

    private void backtrack() {
        for (int i = this.visited.size() - 1; i > 0; i--) {
            MazeCell cell = visited.get(i);
            if (cell.getX() < this.maze.getSize() - 1 && !this.visited.contains(this.maze.getMaze()[cell.getX() + 1][cell.getY()])) {
                System.out.println("Rechts");
                this.x = cell.getX() + 1;
                this.y = cell.getY();
                this.maze.getMaze()[cell.getX()][cell.getY()].removeEdge(MazeCell.RIGHT);
                this.maze.getMaze()[cell.getX() + 1][cell.getY()].removeEdge(MazeCell.LEFT);
                return;
            } else if (cell.getX() > 0 && !this.visited.contains(this.maze.getMaze()[cell.getX() - 1][cell.getY()])) {
                System.out.println("Links");
                this.x = cell.getX() - 1;
                this.y = cell.getY();
                this.maze.getMaze()[cell.getX()][cell.getY()].removeEdge(MazeCell.LEFT);
                this.maze.getMaze()[cell.getX() - 1][cell.getY()].removeEdge(MazeCell.RIGHT);
                return;
            } else if (cell.getY() < this.maze.getSize() - 1 && !this.visited.contains(this.maze.getMaze()[cell.getX()][cell.getY() + 1])) {
                System.out.println("Unten");
                this.x = cell.getX();
                this.y = cell.getY() + 1;
                this.maze.getMaze()[cell.getX()][cell.getY()].removeEdge(MazeCell.BOTTOM);
                this.maze.getMaze()[cell.getX()][cell.getY() + 1].removeEdge(MazeCell.TOP);
                return;
            } else if (cell.getY() > 0 && !this.visited.contains(this.maze.getMaze()[cell.getX()][cell.getY() - 1])) {
                System.out.println("Oben");
                this.x = cell.getX();
                this.y = cell.getY() - 1;
                this.maze.getMaze()[cell.getX()][cell.getY()].removeEdge(MazeCell.TOP);
                this.maze.getMaze()[cell.getX()][cell.getY() - 1].removeEdge(MazeCell.BOTTOM);
                return;
            }
        }
        System.out.println("Done!");
        this.getThread().interrupt();
        this.mazeRunnerHandler.handleMazeDone();
    }

    public static class RunnerThread extends Thread {
        private MazeCreator mazeCreator;

        public RunnerThread(MazeCreator mazeCreator) {
            this.mazeCreator = mazeCreator;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    sleep(mazeCreator.getSpeed());
                    mazeCreator.move();
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
