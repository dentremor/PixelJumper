package de.hdm_stuttgart.mi.se2.game.level;

import de.hdm_stuttgart.mi.se2.game.level.map.Map;
import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import de.hdm_stuttgart.mi.se2.game.main.GameManager;
import de.hdm_stuttgart.mi.se2.game.util.Config;
import de.hdm_stuttgart.mi.se2.game.util.Vector2d;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that handles a single Level
 * @author Cazim Ukela
 */
public class Level {

    // Logger
    private final static Logger log = LogManager.getLogger(GameManager.class);

    // GameManager instance
    private final GameManager gameManager;

    // All Tiles in the Level
    private ArrayList<Tile> tiles;

    // Starttile of the Level
    private Tile startTile;

    // Finishtile of the Level
    private Tile finishTile;

    // Score of the Player
    private int score;

    // Background image of the Level
    private final Image background;

    // X-Offset od the Level
    private int xOffset;

    // Name of the Map
    private String mapName;

    /**
     * Constructor
     *
     * @param gameManager | Gamemanger instance
     */
    public Level(final GameManager gameManager){
        this.gameManager = gameManager;
        this.score = 0;
        this.background = new Image(GameManager.class.getResource("/images/guiElements/background900x900_loopable.png").toString());
        this.xOffset = 0;
    }

    /**
     * Loads the Level
     *
     * @param mapName | Name of the Map
     */
    public void loadLevel(final String mapName) {

        this.mapName = mapName;

        log.info("Loading Level");

        try {
            tiles = Map.MapManager.getMap(mapName);
            for(final Tile t : tiles){
                t.setX(t.getX() * Config.Global.TILE_SIZE);
                t.setY(t.getY() * Config.Global.TILE_SIZE);
                if(t.getImage().getTileType().equals(Config.TileType.START_TYPE)){
                    startTile = t;
                    gameManager.getPlayer().setPos(new Vector2d(t.getX(),t.getY()));
                }else if(t.getImage().getTileType().equals(Config.TileType.FINISH_TYPE)){
                    finishTile = t;

                }
            }

            log.info(tiles.size() + " Tiles loaded");
        } catch (final IOException e) {
            log.error(e);
        }
    }

    // Getter & Setter
    public Image getBackground() {
        return background;
    }

    public void addScore(){
        score++;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(final int xOffset) {
        this.xOffset = xOffset;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public Tile getFinishTile() {
        return finishTile;
    }

    public String getMapName() {
        return mapName;
    }
}
