package de.hdm_stuttgart.mi.se2.game.main;

import de.hdm_stuttgart.mi.se2.game.controller.GameController;
import de.hdm_stuttgart.mi.se2.game.entities.Player;
import de.hdm_stuttgart.mi.se2.game.level.Level;
import de.hdm_stuttgart.mi.se2.game.level.map.Map;
import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import de.hdm_stuttgart.mi.se2.game.level.tiles.TileFactory;
import de.hdm_stuttgart.mi.se2.game.util.Config;
import de.hdm_stuttgart.mi.se2.game.util.Vector2d;
import de.hdm_stuttgart.mi.se2.game.gfx.Screen;
import de.hdm_stuttgart.mi.se2.game.util.CollisionUtil;
import de.hdm_stuttgart.mi.se2.game.util.ImageHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GameManager {

    private final static Logger log = LogManager.getLogger(GameManager.class);

    private GameController gameController;
    private final Screen screen;
    private Player player;
    private long startTime;
    private Level level;



    public GameManager(GameController gameController) {
        this.gameController = gameController;
        this.screen = new Screen();
        this.player = new Player(this,0,0);
        this.level = new Level(this);
        this.level.createLevel();
        this.startTime = System.currentTimeMillis();
    }



    public void update() {
        player.update();

        if (player.getPos().y > 950) {
            respawnPlayer();
        } else if (CollisionUtil.getInstance().playerCollidedWithEnd(player,this)) {
            gameController.stopGameLoop();
        }

        CollisionUtil.getInstance().detectCollisions(player, this);

    }


    public void render(final GraphicsContext gc){
        gc.clearRect(0,0,1600,900);
        level.setxOffset(screen.moveCamera(player, level.getxOffset()));
        renderBackground(gc);
        for(Tile tile : level.getTiles()) {
            if(tile.getX() + level.getxOffset() > -100 && tile.getX() + level.getxOffset() < 1600) {
                screen.render(gc, tile,level.getxOffset());
            }
        }
        player.render(gc,screen);
        gc.fillText("Zeit: " + ((System.currentTimeMillis()-startTime)/1000) + " sec",10,50);
        gc.fillText("Punkte: " + level.getScore() + " Münzen",10,70);
    }

    public void respawnPlayer(){
        level.setxOffset(0);
        player.respawn(new Vector2d(level.getStartTile().getX(),level.getStartTile().getY()));
    }

    private void renderBackground(GraphicsContext gc) {
        int index = (int)player.getPos().x / 900;
        screen.render(gc, (index-1)*900, 0, level.getBackground(), level.getxOffset());
        screen.render(gc, index*900, 0, level.getBackground(), level.getxOffset());
        screen.render(gc, (index+1)*900, 0, level.getBackground(), level.getxOffset());
    }


    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }
}
