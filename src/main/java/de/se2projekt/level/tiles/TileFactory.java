package de.se2projekt.level.tiles;

import de.se2projekt.util.Config;
import de.se2projekt.util.MyImage;
import de.se2projekt.util.Config.TileType.*;

public class TileFactory {

    public Tile makeTile(final int x, final int y, final MyImage image) {

        Tile tile = new Tile(x, y, image);

        switch (tile.getImage().getTileType()){
            case BASIC_TYPE:
                break;
            case SOLID_TYPE:
                tile.setSolid(true);
                break;
            case CLIMBABLE_TYPE:
                tile.setClimbable(true);
                break;
            case START_TYPE:
                tile.setStart(true);
                break;
            case FINISH_TYPE:
                tile.setFinish(true);
                break;
            case COLLECTABLE_TYPE:
                tile.setCollectable(true);
                break;
            case DEADLY_TYPE:
                tile.setDeadly(true);
                break;
            default:
                return null;
        }
        return tile;
    }
}
