package mazerunner.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Jan on 18.07.2017.
 */
public class MazeCell implements IRender {
    public static final int TOP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;

    private int x;
    private int y;
    private float dimension;
    private boolean[] edges;

    public MazeCell(int x, int y, float dimension) {
        this.x = x;
        this.y = y;
        this.dimension = dimension;
        edges = new boolean[4];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = true;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean[] getEdges() {
        return edges;
    }

    public float getDimension() {
        return dimension;
    }

    public void removeEdge(int position) {
        edges[position] = false;
    }

    public void render(GraphicsContext gc) {
        gc.setStroke(Color.WHITE);
        // TOP
        if (edges[0]) {
            gc.strokeLine(x*dimension,y*dimension, x*dimension + dimension, y*dimension);
        }
        // LEFT
        if (edges[1]) {
            gc.strokeLine(x*dimension,y*dimension, x*dimension, y*dimension + dimension);
        }
        // RIGHT
        if (edges[2]) {
            gc.strokeLine(x * dimension + dimension, y * dimension, x * dimension + dimension, y * dimension + dimension);
        }
        // BOTTOM
        if (edges[3]) {
            gc.strokeLine(x * dimension, y * dimension + dimension, x * dimension + dimension, y * dimension + dimension);
        }
    }
}
