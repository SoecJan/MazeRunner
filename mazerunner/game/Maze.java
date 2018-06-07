package mazerunner.game;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Jan on 18.07.2017.
 */
public class Maze implements IRender {
    private int size;
    private float dimension;
    private MazeCell[][] maze;

    public Maze(int size, float dimension) {
        this.size = size;
        this.dimension = dimension;
        maze = new MazeCell[size][size];
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                maze[x][y] = new MazeCell(x, y, dimension);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public float getDimension() {
        return dimension;
    }

    public MazeCell[][] getMaze() {
        return maze;
    }

    public void render(GraphicsContext gc) {
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                maze[x][y].render(gc);
            }
        }
    }
}
