package de.se2projekt.level.tiles;

public class BasicTile extends Tile {

    public BasicTile(int tileId, int x, int y, int width, int height) {
        super(false, false, tileId, x, y, width, height);
    }

    @Override
    public void collide() {}

}
