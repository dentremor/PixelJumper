package de.hdm_stuttgart.mi.se2.game.main;

import de.hdm_stuttgart.mi.se2.game.controller.GameController;
import de.hdm_stuttgart.mi.se2.game.entities.Player;
import de.hdm_stuttgart.mi.se2.game.gfx.Screen;
import de.hdm_stuttgart.mi.se2.game.highscore.HighscoreManager;
import de.hdm_stuttgart.mi.se2.game.level.Level;
import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import de.hdm_stuttgart.mi.se2.game.util.CollisionUtil;
import de.hdm_stuttgart.mi.se2.game.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * Class that handles the whole Game
 * @author Cazim Ukela
 */
public class GameManager {

    // Logger
    private final static Logger log = LogManager.getLogger(GameManager.class);

    // GameController instance
    private final GameController gameController;

    // Screen instance
    private final Screen screen;

    // Player instance
    private final Player player;

    // Level instance
    private final Level level;

    // Starttime of playing
    private final long startTime;


    /**
     * Constructor
     *
     * @param gameController | GameController instance
     */
    public GameManager(final GameController gameController,String levelName, String playerName) {
        this.gameController = gameController;
        this.screen = new Screen();
        log.info("Create Player");
        this.player = new Player(this,0,0,playerName);
        log.info("Create Level");
        this.level = new Level(this);
        this.level.loadLevel(levelName);
        this.startTime = System.currentTimeMillis();
    }


    /**
     * Upadtes the Player and calls the detection of collisions
     */
    public void update() {
        player.update();

        if (player.getPos().y > 950) {
            respawnPlayer();
        } else if (CollisionUtil.getInstance().playerCollidedWithEnd(player,this)) {

            gameController.stopGameLoop();

            HighscoreManager highscoreManager = new HighscoreManager(level.getMapName());
            highscoreManager.addScore(player.getName(),level.getScore() + Math.max(0,(int)(20-((System.currentTimeMillis()-startTime)/1000))));

            gameController.goToStart();

        }

        CollisionUtil.getInstance().detectCollisions(player, this);

    }

    /**
     * Draws the Player, Level and Stats
     *
     * @param gc | Needed to draw Images
     */
    public void render(final GraphicsContext gc){
        gc.clearRect(0,0,1600,900);
        level.setxOffset(screen.moveCamera(player, level.getxOffset()));
        renderBackground(gc);
        for(final Tile tile : level.getTiles()) {
            if(tile.getX() + level.getxOffset() > -100 && tile.getX() + level.getxOffset() < 1600) {
                screen.render(gc, tile,level.getxOffset());
            }
        }
        player.render(gc,screen);
        gc.fillText("Zeit: " + ((System.currentTimeMillis()-startTime)/1000) + " sec",10,50);
        gc.fillText("Punkte: " + level.getScore() + " Münzen",10,70);
    }

    /**
     * Respaws the Player and set the xOffset to 0
     */
    public void respawnPlayer(){
        log.info("Respawn Player");
        level.setxOffset(0);
        player.respawn(new Vector2d(level.getStartTile().getX(),level.getStartTile().getY()));
    }

    /**
     * Draws the Background
     *
     * @param gc | Needed to draw Images
     */
    private void renderBackground(final GraphicsContext gc) {
        for(int i = 0; i < 4; i ++) {
            screen.render(gc, (i) * 900, 0, level.getBackground(), level.getxOffset());
        }
    }

    // Getter
    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }
}
