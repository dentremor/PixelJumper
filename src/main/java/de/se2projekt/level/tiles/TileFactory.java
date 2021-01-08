package de.se2projekt.level.tiles;

import javafx.scene.image.Image;

public class TileFactory {

    public Tile makeTile(final int id, final int x, final int y, final Image image) {

        if (TileType.getTyleType(image) == TileType.BASIC_TYPE) {
            return new BasicTile(id, x, y, image);
        } else if (TileType.getTyleType(image) == TileType.BASIC_SOLID_TYPE) {
            return new BasicSolidTile(id, x, y, image);
        } else if (TileType.getTyleType(image) == TileType.BASIC_CLIMABLE_TILE) {
            return new BasicClimbableTile(id, x, y, image);
        } else {
            return null;
        }
    }
}
