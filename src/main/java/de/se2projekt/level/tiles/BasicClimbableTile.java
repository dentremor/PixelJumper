package de.se2projekt.level.tiles;

public class BasicClimbableTile extends BasicTile{

    public BasicClimbableTile(int tileId, int x, int y, String imagePath) {
        super(tileId, x, y, imagePath);
        setClimbable(true);
    }
}
