package de.se2projekt.gfx;

import de.se2projekt.level.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Screen {

    private List<Tile> renderedTiles = new LinkedList<>();

    public int moveCamera() {
        return 0;
    }

    public void render(Graphics g, int xPos, int yPos, int tileId, int xOffset) {
        //g.drawImage(SpriteSheet.getInstance().getSprite(tileId), (xPos + xOffset), yPos, null);
    }

    public void render(Graphics g, int xPos, int yPos, BufferedImage image, int xOffset) {
        g.drawImage(image, (xPos + xOffset), yPos, null);
    }

    public void render(Graphics g, Tile tile, int xOffset) {
        //g.drawImage(SpriteSheet.getInstance().getSprite(tile.getTileId()), (tile.getX() + xOffset), tile.getY(), null);
        //renderedTiles.add(tile);
    }

    /*
     * Getter & Setter
     */
    public List<Tile> getRenderedTiles() {
        return renderedTiles;
    }

}
