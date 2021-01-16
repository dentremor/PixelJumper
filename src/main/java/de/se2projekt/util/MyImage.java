package de.se2projekt.util;

import javafx.scene.image.Image;

/**
 * A childClass from the JavaFX Image class for tagging the Images.
 * @author Daniel Hiller
 * @version 0.1.0
 */

public class MyImage extends Image {

    Config.TileType tileType;
    Image image;

    public MyImage(String url, Config.TileType tileType) {
        super(url);
        this.tileType = tileType;
    }

    public Config.TileType getTileType() {
        return tileType;
    }

    public Image getImage() {
        return this.image;
    }
}
