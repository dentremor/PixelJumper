package de.se2projekt.gfx;

import de.se2projekt.controller.GameController;
import de.se2projekt.level.tiles.Tile;
import de.se2projekt.util.TileMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public class Screen {

    private List<Tile> renderedTiles = new LinkedList<>();

    public int moveCamera() {
        return 0;
    }

    /*public void render(GraphicsContext gc, Tile tile, int xOffset) {
        gc.drawImage(new Image(GameController.class.getResource(tile.getImagePath()).toString()),tile.getX(),tile.getY());
    }*/

    /*
     * Getter & Setter
     */
    public List<Tile> getRenderedTiles() {
        return renderedTiles;
    }

}
