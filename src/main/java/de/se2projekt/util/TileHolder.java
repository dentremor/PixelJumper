package de.se2projekt.util;

import de.se2projekt.level.tiles.Tile;

public class TileHolder {

    /* Eine ArrayMap die alle Tiles beinhaltet*/

    // Variables
    public static TileHolder INSTANCE = new TileHolder();

    /* Alle Tiles */
    public static Tile TILE_1 = new Tile(-1,-1,ImageHolder.INSTANCE.IMAGE_4);
    public static Tile TILE_2 = new Tile( -1,-1,ImageHolder.INSTANCE.IMAGE_5);
    public static Tile TILE_3 = new Tile(-1,-1,ImageHolder.INSTANCE.IMAGE_6);
    public static Tile TILE_4 = new Tile(-1,-1,ImageHolder.INSTANCE.IMAGE_16);
    public static Tile TILE_5 = new Tile(-1,-1,ImageHolder.INSTANCE.IMAGE_17);
    public static Tile DUMMY_TILE = new Tile( -1, -1, ImageHolder.INSTANCE.DUMMY_IMAGE);

    //
    private Tile[] getTilesAsArray(){
        final Tile [] tileHolderArray = new Tile[]{
                TILE_1, TILE_2, TILE_3, TILE_4, TILE_5, DUMMY_TILE
        };
        return tileHolderArray;
    }
}
