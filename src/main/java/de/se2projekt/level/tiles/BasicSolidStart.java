package de.se2projekt.level.tiles;

import javafx.scene.image.Image;

public class BasicSolidStart extends BasicSolidTile {

    public BasicSolidStart(int tileId, int x, int y, Image image) {
        super(tileId, x, y, image);
        setStart(true);
    }
}
