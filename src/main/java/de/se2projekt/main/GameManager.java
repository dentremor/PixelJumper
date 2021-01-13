package de.se2projekt.main;

import de.se2projekt.entities.Player;
import de.se2projekt.gfx.Screen;
import de.se2projekt.level.tiles.Tile;
import de.se2projekt.level.tiles.TileFactory;
import de.se2projekt.util.CollisionUtil;
import de.se2projekt.util.ImageHolder;
import de.se2projekt.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class GameManager {

    private final static Logger log = LogManager.getLogger(GameManager.class);

    private final Screen screen;
    private final List<Tile> tiles = new ArrayList<>();
    private Image background;
    private Player player;
    private int xOffset;
    private long startTime;
    private int score;



    public GameManager() {
        this.screen = new Screen();
        this.background = new Image(GameManager.class.getResource("/images/background900x900_loopable.png").toString());
        this.player = new Player(this,0,0);
        createLevel();
        this.startTime = System.currentTimeMillis();

    }
    private void createLevel() {
            log.log(Level.INFO,"Loading Level");
            for(int i = 0; i < 64; i ++) {
                Tile t = new TileFactory().makeTile(1,i*50,800, ImageHolder.INSTANCE.IMAGE_16);
                t.setPos(i*50,800);
                tiles.add(t);
            }
            Tile t = new TileFactory().makeTile(1,300,750, ImageHolder.INSTANCE.IMAGE_16);
            tiles.add(t);

            t = new TileFactory().makeTile(1,300,650, ImageHolder.INSTANCE.IMAGE_16);
            tiles.add(t);


            player.setPos(new Vector2d(10,750));

            log.log(Level.INFO,"Level completed - " + tiles.size() + " Tiles loaded");
    }


    public void update() {
        player.update();

        if (player.getPos().y > 950) {
            respawnPlayer();
        } else if (CollisionUtil.getInstance().playerCollidedWithEnd(player,this)) {
            //TODO IMPLEMENT WIN
        }

        CollisionUtil.getInstance().detectCollisions(player, this);

    }


    public void render(final GraphicsContext gc){
        gc.clearRect(0,0,1600,900);
        xOffset = screen.moveCamera(player, xOffset);
        renderBackground(gc);
        for(Tile tile : tiles) {
            if(tile.getX() + xOffset > -100 && tile.getX() + xOffset < 1600) {
                screen.render(gc, tile,xOffset);
            }
        }
        player.render(gc,screen);
        gc.fillText("Zeit: " + ((System.currentTimeMillis()-startTime)/1000) + " sec",10,50);
        gc.fillText("Punkte: " + score + " Münzen",10,70);
    }

    public void respawnPlayer(){
        xOffset = 0;
        player.respawn(10,750);
    }

    private void renderBackground(GraphicsContext gc) {
        int index = (int)player.getPos().x / 900;
        screen.render(gc, (index-1)*900, 0, background, xOffset);
        screen.render(gc, index*900, 0, background, xOffset);
        screen.render(gc, (index+1)*900, 0, background, xOffset);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public int getxOffset() {
        return xOffset;
    }
}
