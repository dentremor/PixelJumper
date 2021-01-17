package de.hdm_stuttgart.mi.se2.game.level.map;

import de.hdm_stuttgart.mi.se2.game.util.Config;
import de.hdm_stuttgart.mi.se2.game.util.ImageHolder;
import de.hdm_stuttgart.mi.se2.game.util.MyImage;

/**
 * A class for generating an Array full of dummyImages for the EditorController.
 * @author Daniel Hiller
 * @version 0.1.0
 */


public class EditorTileMap {

    public MyImage[] getEditorMap() {
        final MyImage[] myImageArray = new MyImage[Config.Map.INDEX_SIZE];

        int index = 0;

        // First loop is for the columns, second one for the rows
        for (int i = 0; i < Config.Map.ROW_SIZE; i++) {
            for (int c = 0; c < Config.Map.COLUMN_SIZE; c++) {
                myImageArray[index] = ImageHolder.INSTANCE.DUMMY_IMAGE;
                index++;
            }
        }
        return myImageArray;
    }
}