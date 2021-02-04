package de.hdm_stuttgart.mi.se2.game.level.tiles;

import de.hdm_stuttgart.mi.se2.game.util.MyImage;

public class TileFactory {

    public Tile makeTile(final int x, final int y, final MyImage image) {

        ITile tile = new Tile(x, y, image);

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
        return (Tile) tile;
    }
}
