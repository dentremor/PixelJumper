package de.se2projekt.level.map;

import de.se2projekt.controller.Config;
import de.se2projekt.util.ImageHolder;
import javafx.scene.image.Image;

public class EditorTileMap {

    public Image[] getEditorMap() {
        final Image[] imageArray = new Image[Config.Map.INDEX_SIZE];

        int index = 0;

        // First loop is for the columns, second one for the rows
        for (int i = 0; i < Config.Map.ROW_SIZE; i++) {
            for (int c = 0; c < Config.Map.COLUMN_SIZE; c++) {
                imageArray[index] = ImageHolder.INSTANCE.DUMMY_IMAGE;
                index++;
            }
        }
        return imageArray;
    }
}