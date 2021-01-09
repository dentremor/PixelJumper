package de.se2projekt.level.tiles;

import javafx.scene.image.Image;

public class BasicTile extends Tile {

    public BasicTile(int tileId, int x, int y, Image image) {
        super(false, false, false, false, false, false, tileId, x, y, image);
    }

    @Override
    public void collide() {
    }

}
