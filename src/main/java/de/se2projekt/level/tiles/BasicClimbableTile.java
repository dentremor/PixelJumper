package de.se2projekt.level.tiles;

public class BasicClimbableTile extends BasicTile{

    public BasicClimbableTile(int tileId, int x, int y, int width, int height, String imagePath) {
        super(tileId, x, y, width, height, imagePath);
        setClimbable(true);
    }
}
