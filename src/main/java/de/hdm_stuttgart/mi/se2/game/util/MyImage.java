package de.hdm_stuttgart.mi.se2.game.util;

import javafx.scene.image.Image;

/**
 * A childClass from the JavaFX Image class for tagging the Images.
 * @author Daniel Hiller
 * @version 0.1.0
 */

public class MyImage extends Image {

    Config.TileType tileType;
    Image image;
    String subUrl;

    public MyImage(final String url, final Config.TileType tileType) {
        super(url);
        subUrl = url;
        this.tileType = tileType;
    }

    public Config.TileType getTileType() {
        return tileType;
    }

    public String getSubUrl() {
        return subUrl;
    }

    public Image getImage() {
        return this.image;
    }
}
