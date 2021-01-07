package de.se2projekt.util;

import de.se2projekt.level.tiles.Tile;
import javafx.scene.image.Image;

public class TileMap {


    private final Tile[] mapArray = new Tile[575];

    public Image[] getEditorMap() {
        final Image[] imageArray = new Image[575];

        // First loop ist for the columns, second one for the rows
        for (int i = 1; i <= 32; i++) {
            for (int c = 1; c <= 18; c++) {
                imageArray[i] = ImageHolder.INSTANCE.DUMMY_IMAGE;
            }
        }
        return imageArray;
    }

    //TODO Danny should be completely reworked after adding a Factory for tiles
//    public Tile get(final int index) {
//    return map.get(index);
//    }
//
//    public void replace(final int index, final Tile tile) {
//        map.replace(index, tile);
//    }

    public int size() {
        return mapArray.length;
    }

//    public void extend(int row) {
//        for (int i = 1; i <= 18 * row; i++) {
//            map.put(i, dummyTile);
//        }
//    }
}
