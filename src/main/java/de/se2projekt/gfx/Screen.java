package de.se2projekt.gfx;

import de.se2projekt.level.tiles.Tile;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.List;

public class Screen {

    private final List<Tile> renderedTiles = new LinkedList<>();

    public int moveCamera() {
        return 0;
    }

    public void render(final GraphicsContext gc, final Tile tile, final int xOffset) {
        //TODO Danny *important* unset comment after adding the missing functionality to TileMap
//        gc.drawImage(new Image(GameController.class.getResource(tile.getImagePath()).toString()),tile.getX(),tile.getY());
    }

    /*
     * Getter & Setter
     */
    public List<Tile> getRenderedTiles() {
        return renderedTiles;
    }

}
