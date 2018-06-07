package mazerunner.game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 18.07.2017.
 */
public class Renderer extends AnimationTimer {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Maze maze;
    private MazeCreator mazeCreator;
    private List<IRender> renderList;

    public Renderer(Canvas canvas, MazeCreator mazeCreator) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.mazeCreator = mazeCreator;
        this.maze = mazeCreator.getMaze();
        this.renderList = new ArrayList<>();
    }

    public void addEntity(IRender renderable) {
        this.renderList.add(renderable);
    }

    public Boolean removeEntity(IRender renderable) {
        return this.renderList.remove(renderable);
    }

    public void render() {
        this.graphicsContext.setFill(Color.BLACK);
        this.graphicsContext.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
        this.graphicsContext.setFill(Color.WHITE);
        for (int i = 0; i < renderList.size(); i++) {
            renderList.get(i).render(graphicsContext);
        }
    }

    @Override
    public void handle(long now) {
        this.render();
    }
}
