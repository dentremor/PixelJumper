package de.hdm_stuttgart.mi.se2.game.util;

import de.hdm_stuttgart.mi.se2.game.controller.EditorController;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A singleton class for spacing the whole tileSet.
 * @author Daniel Hiller
 * @version 0.1.0
 */

public class ImageHolder {

    // Logger
    private static final Logger log = LogManager.getLogger(EditorController.class);

    // Variables
    public static final ImageHolder INSTANCE = new ImageHolder();

    // Images for the Game and Editor
    public final MyImage IMAGE_1 = new MyImage("/images/tile_1.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_2 = new MyImage("/images/tile_2.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_3 = new MyImage("/images/tile_3.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_4 = new MyImage("/images/tile_4.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_5 = new MyImage("/images/tile_5.png", Config.TileType.CLIMBABLE_TYPE);
    public final MyImage IMAGE_7 = new MyImage("/images/tile_7.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_8 = new MyImage("/images/tile_8.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_9 = new MyImage("/images/tile_9.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_10 = new MyImage("/images/tile_10.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_11 = new MyImage("/images/tile_11.png", Config.TileType.CLIMBABLE_TYPE);
    public final MyImage IMAGE_12 = new MyImage("/images/tile_12.png", Config.TileType.COLLECTABLE_TYPE);
    public final MyImage IMAGE_13 = new MyImage("/images/tile_13.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_14 = new MyImage("/images/tile_14.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_15 = new MyImage("/images/tile_15.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_16 = new MyImage("/images/tile_16.png", Config.TileType.BASIC_TYPE);
    public final MyImage START_IMAGE = new MyImage("/images/tile_17.png", Config.TileType.START_TYPE);
    public final MyImage FINISH_IMAGE = new MyImage("/images/tile_18.png", Config.TileType.FINISH_TYPE);
    public final MyImage IMAGE_19 = new MyImage("/images/tile_19.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_20 = new MyImage("/images/tile_20.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_21 = new MyImage("/images/tile_21.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_22 = new MyImage("/images/tile_22.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_23 = new MyImage("/images/tile_23.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_24 = new MyImage("/images/tile_24.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_25 = new MyImage("/images/tile_25.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_26 = new MyImage("/images/tile_26.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_27 = new MyImage("/images/tile_27.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_28 = new MyImage("/images/tile_28.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_29 = new MyImage("/images/tile_29.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_30 = new MyImage("/images/tile_30.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_31 = new MyImage("/images/tile_31.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_32 = new MyImage("/images/tile_32.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_33 = new MyImage("/images/tile_33.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_34 = new MyImage("/images/tile_34.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_35 = new MyImage("/images/tile_35.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_36 = new MyImage("/images/tile_36.png", Config.TileType.SOLID_TYPE);
    public final MyImage IMAGE_38 = new MyImage("/images/tile_38.png", Config.TileType.CLIMBABLE_TYPE);
    public final MyImage IMAGE_40 = new MyImage("/images/tile_40.png", Config.TileType.DEADLY_TYPE);
    public final MyImage IMAGE_41 = new MyImage("/images/tile_41.png", Config.TileType.DEADLY_TYPE);
    public final MyImage IMAGE_42 = new MyImage("/images/tile_42.png", Config.TileType.DEADLY_TYPE);
    public final MyImage IMAGE_44 = new MyImage("/images/tile_44.png", Config.TileType.CLIMBABLE_TYPE);
    public final MyImage IMAGE_46 = new MyImage("/images/tile_46.png", Config.TileType.DEADLY_TYPE);
    public final MyImage IMAGE_47 = new MyImage("/images/tile_47.png", Config.TileType.DEADLY_TYPE);
    public final MyImage IMAGE_48 = new MyImage("/images/tile_48.png", Config.TileType.DEADLY_TYPE);
    public final MyImage DUMMY_IMAGE = new MyImage("/images/dummy_tile.png", Config.TileType.DUMMY_TYPE);

    // Images for the Game
    public final MyImage PLAYER_LEFT_IMAGE = new MyImage("/images/guiElements/player_left.png", Config.TileType.BASIC_TYPE);
    public final MyImage PLAYER_RIGHT_IMAGE = new MyImage("/images/guiElements/player_right.png", Config.TileType.BASIC_TYPE);

    // Returns the TilesSet for the ItemBox in EditorController
    public MyImage[] getImagesAsArray() {
        log.info("TileSet was successfully load");
        return new MyImage[]{
                IMAGE_1, IMAGE_2, IMAGE_3, IMAGE_4, IMAGE_5, DUMMY_IMAGE, IMAGE_7, IMAGE_8, IMAGE_9, IMAGE_10,
                IMAGE_11, IMAGE_12, IMAGE_13, IMAGE_14, IMAGE_15, IMAGE_16, START_IMAGE, FINISH_IMAGE, IMAGE_19, IMAGE_20,
                IMAGE_21, IMAGE_22, IMAGE_23, IMAGE_24, IMAGE_25, IMAGE_26, IMAGE_27, IMAGE_28, IMAGE_29, IMAGE_30,
                IMAGE_31, IMAGE_32, IMAGE_33, IMAGE_34, IMAGE_35, IMAGE_36, DUMMY_IMAGE, IMAGE_38, DUMMY_IMAGE, IMAGE_40,
                IMAGE_41, IMAGE_42, DUMMY_IMAGE, IMAGE_44, DUMMY_IMAGE, IMAGE_46, IMAGE_47,IMAGE_48,
        };
    }

    public String toString(Image image) {
        return image.getUrl();
    }

    public MyImage getImage(String imageURL) {
        for (int i = 0; i < getImagesAsArray().length; i++) {
            if (imageURL.equals(getImagesAsArray()[i].getUrl())) {
                return getImagesAsArray()[i];
            }
        }
        return null;
    }
}
