package de.se2projekt.util;

import javafx.scene.image.Image;

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
