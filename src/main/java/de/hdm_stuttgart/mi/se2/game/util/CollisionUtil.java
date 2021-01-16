package de.hdm_stuttgart.mi.se2.game.util;

import de.hdm_stuttgart.mi.se2.game.entities.Player;
import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import de.hdm_stuttgart.mi.se2.game.main.GameManager;
import javafx.scene.shape.Rectangle;

public class CollisionUtil {

    private static CollisionUtil instance = null;

    public static CollisionUtil getInstance() {
        return (instance == null) ? instance = new CollisionUtil() : instance;
    }

    public void detectCollisions(Player player, GameManager gameManager) {
        betweenPlayerAndTiles(player, gameManager);
        player.defineJumpingOrFalling(getTileUnderPlayer(player,gameManager));
    }

    private Tile getTileUnderPlayer(Player player, GameManager gameManager) {
        Rectangle defaultRectangle = player.getBounds();
        for(Tile tile : gameManager.getLevel().getTiles()) {
            if(intersects(new Rectangle(defaultRectangle.getX(),defaultRectangle.getY(),defaultRectangle.getWidth(),defaultRectangle.getHeight()+1),tile.getBounds())) {
                return tile;
            }
        }
        return null;
    }

    private void betweenPlayerAndTiles(Player player, GameManager gameManager) {
        Tile scoreTile = null;
        for (Tile t : gameManager.getLevel().getTiles()) {
            if (intersects(player.getBounds(),t.getBounds())) {
                if (t.isSolid() && !t.isDeadly()) {
                    int collisionSide = getCollsionSide(player.getBounds(), t.getBounds());
                    player.collide(collisionSide,t);
                } else if (t.isDeadly()) {
                    gameManager.respawnPlayer();
                    break;
                }else if(t.isCollectable()){
                    gameManager.getLevel().addScore();
                    scoreTile = t;
                    break;
                }
            }

        }

        if(scoreTile != null){
            gameManager.getLevel().getTiles().remove(scoreTile);
            scoreTile = null;
        }

        player.collide(-1,null);
    }

    public boolean playerCollidedWithEnd(Player player, GameManager gameManager) {
        for (Tile t : gameManager.getLevel().getTiles()) {
            if(t.getImage().getTileType().equals(Config.TileType.FINISH_TYPE)){
                if(intersects(player.getBounds(),t.getBounds())){
                    return true;
                }
            }
        }
        return false;
    }

    private int getCollsionSide(Rectangle r1, Rectangle r2) {
        int rigth = Math.abs(Math.abs((int) r1.getX() + (int) r1.getWidth()) - Math.abs( (int) r2.getX()));
        int top = Math.abs((int) r1.getY() - (int) r2.getY() - (int) r2.getHeight());
        int bottom = Math.abs(Math.abs((int) r1.getY() + (int) r1.getHeight()) - Math.abs((int) r2.getY()));
        int left = Math.abs((int) r1.getX() - (int) r2.getX() - (int) r2.getWidth());

        int min = Math.min(Math.min(Math.min(rigth, top), bottom),left);
        return (min == bottom) ? 0 : (min == top) ? 1 : (min == rigth) ? 2 : (min == left) ? 3 : -1;
    }

    private boolean intersects(Rectangle r1, Rectangle r2){
        return r1.getX() < r2.getX() + r2.getWidth() && r1.getX() + r1.getWidth() > r2.getX() && r1.getY() < r2.getY() + r2.getHeight() && r1.getY() + r1.getHeight() > r2.getY();
    }


}
