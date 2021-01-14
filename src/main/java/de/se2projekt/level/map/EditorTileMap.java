package de.se2projekt.level.map;

import de.se2projekt.util.Config;
import de.se2projekt.util.ImageHolder;
import de.se2projekt.util.MyImage;
import javafx.scene.image.Image;

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