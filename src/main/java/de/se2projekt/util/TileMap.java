package de.se2projekt.util;

import de.se2projekt.level.tiles.BasicSolidTile;
import de.se2projekt.level.tiles.Tile;

import java.util.HashMap;

public class TileMap {

    private final HashMap<Integer, Tile> map = new HashMap<>();

    public HashMap<Integer, Tile> getMap() {

        // Index for all tiles in map
        int index = 0;
        int x = 0;
        int y = 0;

        // First loop ist for the columns, second one for the rows
        for (int i = 1; i <= 32; i++) {
            x++;
            for (int c = 1; c <= 18; c++) {
                y++;
                final BasicSolidTile dummyTile = new BasicSolidTile(index, x, y, "/images/tiles/dummy_tile.png");
                this.map.put(index, dummyTile);
                index++;
            }
            y = 0;
        }
        System.out.println("Tile 300 x " + this.map.get(300).getX() + " y " + this.map.get(300).getY());
        return this.map;
    }
}
