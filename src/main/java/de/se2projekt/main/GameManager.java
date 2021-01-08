package de.se2projekt.main;

import de.se2projekt.gfx.Screen;
import de.se2projekt.level.tiles.Tile;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;


public class GameManager {

    private final Screen screen;
    private final List<Tile> tiles = new ArrayList<>();


    public GameManager() {
        this.screen = new Screen();
        //TODO Danny *important* unset comment after adding the missing functionality to TileMap
//        createLevel();

    }
    //TODO Danny *important* unset comment after adding the missing functionality to TileMap
//    private void createLevel() {
//            try {
//                for(int i = 0; i < 16; i ++) {
//                    Tile t = (Tile) TileMap.getInstance().getArray().get(0).clone();
//                    t.setX(i*100);
//                    t.setY(800);
//                    tiles.add(t);
//                }
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//    }

    public void update() {

    }

    public void render(final GraphicsContext gc){
        gc.clearRect(0,0,1600,900);

        for(final Tile t : tiles){
            screen.render(gc,t,0);
        }
    }
}
