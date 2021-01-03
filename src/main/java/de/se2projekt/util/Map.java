package de.se2projekt.util;

import de.se2projekt.level.tiles.BasicSolidTile;
import de.se2projekt.level.tiles.Tile;

import java.util.HashMap;

public class Map {

    private HashMap<Integer, Tile> map = new HashMap<>();
    private static final BasicSolidTile dummyTile = new BasicSolidTile(-1,-1,-1,"/images/tiles/dummy_tile.png");

    public HashMap<Integer, Tile> getMap() {

        // Index for all tiles in map
        int index = 0;

        // First loop ist for the columns, second one for the rows
        for (int i = 1; i <= 18; i++) {
            dummyTile.setX(i);
            for (int c = 1; c <= 32; c++) {
                dummyTile.setY(c);
                map.put(index, dummyTile);
                System.out.println("Tile: " + index +" was added to the list in x " + dummyTile.getX() + " and y " + dummyTile.getY());
                index++;
            }
        }
        return map;
    }

    public Tile get(int index) {
    return map.get(index);
    }

    public void replace(int index, Tile tile) {
        map.replace(index, tile);
    }

    public int size() {
        return map.size();
    }

    public void extend(int row) {
        for (int i = 1; i <= 18 * row; i++) {
            map.put(i, dummyTile);
        }
    }
}
