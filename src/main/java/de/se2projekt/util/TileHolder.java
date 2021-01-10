package de.se2projekt.util;

import de.se2projekt.level.tiles.BasicClimbableTile;
import de.se2projekt.level.tiles.BasicSolidTile;
import de.se2projekt.level.tiles.BasicTile;
import de.se2projekt.level.tiles.Tile;

public class TileHolder {

    /* Eine ArrayMap die alle Tiles beinhaltet*/

    // Variables
    public static TileHolder INSTANCE = new TileHolder();

    /* Alle Tiles */
    public static BasicSolidTile TILE_1 = new BasicSolidTile(1,-1,-1,ImageHolder.INSTANCE.IMAGE_4);
    public static BasicSolidTile TILE_2 = new BasicSolidTile(2, -1,-1,ImageHolder.INSTANCE.IMAGE_5);
    public static BasicSolidTile TILE_3 = new BasicSolidTile(3,-1,-1,ImageHolder.INSTANCE.IMAGE_6);
    public static BasicSolidTile TILE_4 = new BasicSolidTile(4,-1,-1,ImageHolder.INSTANCE.IMAGE_16);
    public static BasicClimbableTile TILE_5 = new BasicClimbableTile(5,-1,-1,ImageHolder.INSTANCE.IMAGE_17);
    public static BasicTile DUMMY_TILE = new BasicTile(-1, -1, -1, ImageHolder.INSTANCE.DUMMY_IMAGE);

    //
    private Tile[] getTilesAsArray(){
        final Tile [] tileHolderArray = new Tile[]{
                TILE_1, TILE_2, TILE_3, TILE_4, TILE_5, DUMMY_TILE
        };
        return tileHolderArray;
    }
}
