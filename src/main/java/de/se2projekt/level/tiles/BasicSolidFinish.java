package de.se2projekt.level.tiles;

import javafx.scene.image.Image;

public class BasicSolidFinish extends BasicSolidTile {

    public BasicSolidFinish(int tileId, int x, int y, Image image) {
        super(tileId, x, y, image);
        setFinish(true);
    }
}
