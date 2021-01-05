package de.se2projekt.level.tiles;

import javafx.scene.image.Image;

public class BasicClimbableTile extends BasicTile{

    public BasicClimbableTile(final int tileId, final int x, final int y, final Image image) {
        super(tileId, x, y, image);
        setClimbable(true);
    }
}
