package de.se2projekt.gfx;

import de.se2projekt.entities.Player;
import de.se2projekt.level.tiles.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public class Screen {

    private final List<Tile> renderedTiles = new LinkedList<>();

    public int moveCamera(Player player, int xOffset) {
        if(player.getPos().x + xOffset > (1600/2)) {
            xOffset -= player.getPos().x + xOffset - (1600/2);
        }

        if(player.getPos().x + xOffset < 0) {
            player.getPos().x -=(player.getPos().x + xOffset);
        }

        renderedTiles.clear();
        return xOffset;
    }
    public void render(final GraphicsContext gc, final Tile tile, final int xOffset) {
        gc.drawImage(tile.getImage(),tile.getX() + xOffset,tile.getY());
        renderedTiles.add(tile);
    }
    public void render(final GraphicsContext gc, final int x, final int y, final Image image, final int xOffset) {
        gc.drawImage(image,x + xOffset,y);
    }

    /*
     * Getter & Setter
     */
    public List<Tile> getRenderedTiles() {
        return renderedTiles;
    }

}
