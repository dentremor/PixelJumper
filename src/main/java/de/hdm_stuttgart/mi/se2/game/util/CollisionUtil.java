package de.hdm_stuttgart.mi.se2.game.util;

import de.hdm_stuttgart.mi.se2.game.entities.Player;
import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import de.hdm_stuttgart.mi.se2.game.main.GameManager;
import javafx.scene.shape.Rectangle;

/**
 * Class that handles the Collisions
 * @author Cazim Ukela
 */
public class CollisionUtil {

    // Instance
    private static CollisionUtil instance;

    /**
     * Calls Methods to detect the Collisions
     *
     * @param player | Player
     * @param gameManager | GameManager instance
     */
    public void detectCollisions(Player player, GameManager gameManager) {
        betweenPlayerAndTiles(player, gameManager);
        defineJumpingOrFalling(player,gameManager);
    }

    /**
     * Controls weather the Player is falling or jumping
     *
     * @param player | Player
     * @param gameManager | GameManager
     */
    public void defineJumpingOrFalling(final Player player, final GameManager gameManager) {

        Tile tile = getTileUnderPlayer(player,gameManager);

        if (player.getState() == Player.State.FALLING || player.getState() == Player.State.JUMPING) {
            player.setState((player.getVel().y > 0) ? Player.State.FALLING : Player.State.JUMPING);
        }else if(tile == null) {
            player.getVel().y = 0;
            player.setState(Player.State.FALLING);
        }else if(player.getState() == Player.State.GROUND){
            if(tile.isDeadly()) {
                gameManager.respawnPlayer();
            }else if(!tile.isSolid()){
                player.setState(Player.State.FALLING);
            }
        }

    }

    /**
     * Gives the Tile under the Player
     *
     * @param player | Player
     * @param gameManager | GameManager instance
     * @return | The Tile under the Player
     */
    private Tile getTileUnderPlayer(Player player, GameManager gameManager) {
        Rectangle defaultRectangle = player.getBounds();
        for(Tile tile : gameManager.getLevel().getTiles()) {
            if(intersects(new Rectangle(defaultRectangle.getX(),defaultRectangle.getY(),defaultRectangle.getWidth(),defaultRectangle.getHeight()+1),tile.getBounds())) {
                return tile;
            }
        }
        return null;
    }

    /**
     * Handles the Collision between Player and Tiles
     *
     * @param player | Player
     * @param gameManager | GameManager instance
     */
    private void betweenPlayerAndTiles(Player player, GameManager gameManager) {
        Tile scoreTile = null;
        for (Tile t : gameManager.getLevel().getTiles()) {
            if (intersects(player.getBounds(),t.getBounds())) {
                if (t.isSolid() && !t.isDeadly()) {
                    int collisionSide = getCollisionSide(player.getBounds(), t.getBounds());
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

    /**
     * Checks if the Player has collided with the End
     *
     * @param player | Player
     * @param gameManager | GameManager instance
     * @return | If the Player has collided with the End
     */
    public boolean playerCollidedWithEnd(Player player, GameManager gameManager) {
        return intersects(player.getBounds(),gameManager.getLevel().getFinishTile().getBounds());
    }

    /**
     * Gives the collisionside of the two Rectangles
     *
     * @param r1 | Rectangle
     * @param r2 | Rectangle
     * @return | The collisionside of the two Rectangles
     */
    public int getCollisionSide(Rectangle r1, Rectangle r2) {
        int right = Math.abs(Math.abs((int) r1.getX() + (int) r1.getWidth()) - Math.abs( (int) r2.getX()));
        int top = Math.abs((int) r1.getY() - (int) r2.getY() - (int) r2.getHeight());
        int bottom = Math.abs(Math.abs((int) r1.getY() + (int) r1.getHeight()) - Math.abs((int) r2.getY()));
        int left = Math.abs((int) r1.getX() - (int) r2.getX() - (int) r2.getWidth());

        int min = Math.min(Math.min(Math.min(right, top), bottom),left);
        return (min == bottom) ? 0 : (min == top) ? 1 : (min == right) ? 2 : (min == left) ? 3 : -1;
    }

    /**
     * Checks if two Rectangles intersects
     *
     * @param r1 | Rectangle
     * @param r2 | Rectangle
     * @return | If the tow Rectangles intersects
     */
    public boolean intersects(Rectangle r1, Rectangle r2){
        return r1.getX() < r2.getX() + r2.getWidth() && r1.getX() + r1.getWidth() > r2.getX() && r1.getY() < r2.getY() + r2.getHeight() && r1.getY() + r1.getHeight() > r2.getY();
    }

    //Getter
    public static CollisionUtil getInstance() {
        return (instance == null) ? instance = new CollisionUtil() : instance;
    }





}
