package de.se2projekt.level.tiles;

public class BasicTile extends Tile {

    public BasicTile(int tileId, int x, int y, int width, int height, String imagePath) {
        super(false, false, false, tileId, x, y, width, height, imagePath);
    }

    @Override
    public void collide() {}

}
