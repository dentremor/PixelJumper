package de.se2projekt.main;

import de.se2projekt.controller.GameController;
import de.se2projekt.gfx.Screen;
import de.se2projekt.level.tiles.Tile;
import de.se2projekt.util.TileMap;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;


public class GameManager {

    private Screen screen;
    private List<Tile> tiles = new ArrayList<>();


    public GameManager() {
        this.screen = new Screen();

        createLevel();

    }

    private void createLevel() {
            try {
                for(int i = 0; i < 16; i ++) {
                    Tile t = (Tile) TileMap.getInstance().getArray().get(0).clone();
                    t.setX(i*100);
                    t.setY(800);
                    tiles.add(t);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
    }

    public void update() {

    }

    public void render(GraphicsContext gc){
        gc.clearRect(0,0,1600,900);

        for(Tile t : tiles){
            screen.render(gc,t,0);
        }
    }
}
