package de.se2projekt.level;

import de.se2projekt.gfx.Screen;
import de.se2projekt.level.tiles.Tile;

import java.util.ArrayList;
import java.util.List;


public class Level {

    /* Eine Liste mit allen Tiles des Levels*/
    private List<Tile> tiles;

    public Level() {
        generateLevel();
    }

    /*
     * Rendert alle Tiles die sich im Fester befinden
     */
    public void render(Screen screen) {

    }

    private List<Tile> generateLevel() {
        List<Tile> tiles = new ArrayList<>();

        return tiles;
    }

    /*
     * Getter & Setter
     */
    public List<Tile> getTiles() {
        return tiles;
    }


}
