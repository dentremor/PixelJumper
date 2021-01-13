package de.se2projekt.level.tiles;

import javafx.scene.image.Image;

public class TileFactory {

    public Tile makeTile(final int id, final int x, final int y, final Image image) {

        Tile t = new Tile(id, x, y, image);

        switch (TileType.getTyleType(image)){
            case BASIC_SOLID_TYPE:
                t.setSolid(true);
                break;
            case BASIC_CLIMABLE_TILE:
                t.setClimbable(true);
                break;
        }

        return t;
    }
}
