package de.se2projekt.util;

import de.se2projekt.gfx.SpriteSheet;
import de.se2projekt.level.tiles.BasicSolidTile;
import de.se2projekt.level.tiles.Tile;

import java.util.HashMap;

public class TileMap {

    /* Eine HashMap die alle Tiles beinhaltet*/
    private static final HashMap<Integer, Tile> TILE_MAP = new HashMap<>();

    /* Zuweisung aller Tiles*/
    private static final BasicSolidTile GROUND = new BasicSolidTile(SpriteSheet.GROUND, 0, 0,  "@../images/tiles/tree.png");
    private static final BasicSolidTile TREE = new BasicSolidTile(SpriteSheet.GROUND, 0, 0,  "@../images/tiles/tree.png");

    /*
     * Gibt das Tile mit der id "index" zurueck
     */
    public static Tile getTile(int index) {
        if (TILE_MAP.isEmpty())
            initTileMap();
        return TILE_MAP.get(index);
    }

    private static void initTileMap() {
        int[] tiles = SpriteSheet.getInstance().getBuildableBlocks();
        TILE_MAP.put(tiles[0], GROUND);
        TILE_MAP.put(tiles[1], TREE);
    }

}
