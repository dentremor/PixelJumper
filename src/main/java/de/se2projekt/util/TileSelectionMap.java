package de.se2projekt.util;

import de.se2projekt.level.tiles.BasicClimbableTile;
import de.se2projekt.level.tiles.BasicSolidTile;
import de.se2projekt.level.tiles.Tile;

/* Eine ArrayMap die alle Tiles beinhaltet*/
public class TileSelectionMap {

    // Variables
    public static final TileSelectionMap INSTANCE = new TileSelectionMap();
    private final Tile[] tileArray;

    // Constructor
    private TileSelectionMap() {
        /* Zuweisung aller Tiles*/
        this.tileArray = new Tile[]{
                new BasicSolidTile(1, -1, -1, "/images/tiles/image16.png"),
                new BasicSolidTile(2, -1, -1, "/images/tiles/image17.png"),
                new BasicSolidTile(3, -1, -1, "/images/tiles/image4.png"),
                new BasicClimbableTile(4, -1, -1, "/images/tiles/image5.png"),
                new BasicClimbableTile(5, -1, -1, "/images/tiles/image6.png")
        };
    }

    public int size() {
        return this.tileArray.length;
    }

    public Tile get(final int idx) {
        return this.tileArray[idx];
    }
}
