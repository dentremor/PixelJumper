package de.se2projekt.util;

import de.se2projekt.entities.Player;
import de.se2projekt.level.tiles.Tile;
import de.se2projekt.main.GameManager;
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
        for(Tile tile : gameManager.getTiles()) {
            if(intersects(new Rectangle(defaultRectangle.getX(),defaultRectangle.getY(),defaultRectangle.getWidth(),defaultRectangle.getHeight()+1),tile.getBounds())) {
                return tile;
            }
        }
        return null;
    }

    private void betweenPlayerAndTiles(Player player, GameManager gameManager) {
        System.out.println(1);
        for (Tile t : gameManager.getTiles()) {
            System.out.println(2);
            if (intersects(player.getBounds(),t.getBounds())) {
                System.out.println(3);
                if (t.isSolid() && !t.isDeadly()) {
                    System.out.println(4);
                    int collisionSide = getCollsionSide(player.getBounds(), t.getBounds());
                    player.collide(collisionSide,t);
                } else if (t.isDeadly()) {
                    //TODO IMPLEMENT DEATH
                    break;
                }
            }

        }
        player.collide(-1,null);
    }

    public boolean playerCollidedWithEnd(Player player, GameManager gameManager) {
        for (Tile t : gameManager.getTiles()) {
            //TODO IMPLEMENT END
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
