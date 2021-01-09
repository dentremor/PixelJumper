package de.se2projekt.main;

import de.se2projekt.controller.EditorController;
import de.se2projekt.entities.Player;
import de.se2projekt.gfx.Screen;
import de.se2projekt.gui.MainMenuController;
import de.se2projekt.level.tiles.Tile;
import de.se2projekt.util.TileHolder;
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



    public GameManager() {
        this.screen = new Screen();
        this.background = new Image(GameManager.class.getResource("/images/background900x900_loopable.png").toString());
        this.player = new Player(0,0);
        createLevel();

    }
    private void createLevel() {
            log.log(Level.INFO,"Loading Level");
            try {
                for(int i = 0; i < 16; i ++) {
                    Tile t = (Tile) TileHolder.TILE_4.clone();
                    t.setPos(i*100,800);
                    tiles.add(t);
                }
                Tile t = (Tile) TileHolder.TILE_4.clone();
                t.setPos(300,700);

                tiles.add(t);

                t = (Tile) TileHolder.TILE_4.clone();
                t.setPos(300,500);


                tiles.add(t);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            player.setPos(new Vector2d(10,750));

            log.log(Level.INFO,"Level completed - " + tiles.size() + " Tiles loaded");
    }


    public void update() {
        player.update();
    }

    public void render(final GraphicsContext gc){
        gc.clearRect(0,0,1600,900);
        for(int i = 0; i < 10; i ++){
            gc.drawImage(background,900*i,0);
        }

        for(final Tile t : tiles){
            screen.render(gc,t,0);
        }

        player.render(gc,screen);
    }
}
