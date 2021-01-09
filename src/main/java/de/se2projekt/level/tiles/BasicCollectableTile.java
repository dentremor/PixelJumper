package de.se2projekt.level.tiles;

import javafx.scene.image.Image;

public class BasicCollectableTile extends BasicTile {

    public BasicCollectableTile(int tileId, int x, int y, Image image) {
        super(tileId, x, y, image);
        setCollectable(true);
    }
}
