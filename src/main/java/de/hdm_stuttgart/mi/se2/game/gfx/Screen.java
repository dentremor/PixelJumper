package de.hdm_stuttgart.mi.se2.game.gfx;

import de.hdm_stuttgart.mi.se2.game.entities.Player;
import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import de.hdm_stuttgart.mi.se2.game.util.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

/**
 * Helperclass to draw every Tile and Entity
 * @author Cazim Ukela
 */
public class Screen {

    /**
     * Calculates the new XOffset depending on Player position
     *
     * @param player | Playerobjects
     * @param xOffset | Current xOffset
     * @return | The new xOffset
     */
    public int moveCamera(Player player, int xOffset) {
        if(player.getPos().x + xOffset > (Config.Global.GAME_WIDTH /2)) {
            xOffset -= player.getPos().x + xOffset - (Config.Global.GAME_WIDTH/2);
        }

        if(player.getPos().x + xOffset < 0) {
            player.getPos().x -=(player.getPos().x + xOffset);
        }

        return xOffset;
    }

    /**
     * Draws a Tile with xOffset
     *
     * @param gc | Needed to draw Image
     * @param tile | Tile to draw
     * @param xOffset | xOffset
     */
    public void render(final GraphicsContext gc, final Tile tile, final int xOffset) {
        gc.drawImage(tile.getImage(),tile.getX() + xOffset,tile.getY());
    }

    /**
     * Draws an Image with xOffset
     *
     * @param gc | Needed to draw Image
     * @param x | X - Position
     * @param y | Y - Position
     * @param image | Image to draw
     * @param xOffset | xOffset
     */
    public void render(final GraphicsContext gc, final int x, final int y, final Image image, final int xOffset) {
        gc.drawImage(image,x + xOffset,y);
    }


}
