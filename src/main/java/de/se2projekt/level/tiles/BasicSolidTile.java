package de.se2projekt.level.tiles;

import javafx.scene.image.Image;

public class BasicSolidTile extends BasicTile {

    /**
     * [Konstruktor] Initialisiert den Block mit den übergebenen Werten
     */
    public BasicSolidTile(final int tileId, final int x, final int y, final Image image) {
        super(tileId, x, y, image);
        setSolid(true);
    }

}
