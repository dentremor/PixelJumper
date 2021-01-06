package de.se2projekt.util;

import de.se2projekt.controller.Config;
import de.se2projekt.level.tiles.Tile;
import javafx.scene.image.Image;

public class TileMap {

    private final Tile[] mapArray = new Tile[Config.Map.INDEX_SIZE];

    public Image[] getEditorMap() {
        final Image[] imageArray = new Image[Config.Map.INDEX_SIZE];

        // First loop ist for the columns, second one for the rows
        for (int i = 1; i <= Config.Map.ROW_SIZE; i++) {
            for (int c = 1; c <= Config.Map.COLUMN_SIZE; c++) {
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
        return this.mapArray.length;
    }

//    public void extend(int row) {
//        for (int i = 1; i <= 18 * row; i++) {
//            map.put(i, dummyTile);
//        }
//    }
}
