package de.se2projekt.util;

import de.se2projekt.gfx.SpriteSheet;
import de.se2projekt.level.tiles.BasicClimbableTile;
import de.se2projekt.level.tiles.BasicSolidTile;
import de.se2projekt.level.tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TileMap {

    /* Eine ArrayMap die alle Tiles beinhaltet*/

    // Variables
    private static TileMap instance;
    private ArrayList<Tile> tileMap;

    /* Zuweisung aller Tiles*/

    private static final BasicSolidTile tileOne = new BasicSolidTile(1,-1,-1,"/images/tiles/image16.png");
    private static final BasicSolidTile tileTwo = new BasicSolidTile(2, -1,-1,"/images/tiles/image17.png");
    private static final BasicSolidTile tileThree = new BasicSolidTile(3,-1,-1,"/images/tiles/image4.png");
    private static final BasicClimbableTile lianaOne = new BasicClimbableTile(4,-1,-1,"/images/tiles/image5.png");
    private static final BasicClimbableTile lianaTwo = new BasicClimbableTile(5,-1,-1,"/images/tiles/image6.png");

    // Constructor
    private TileMap() {
        tileMap = new ArrayList<>();
        tileMap.add(tileOne);
        tileMap.add(tileTwo);
        tileMap.add(tileThree);
        tileMap.add(lianaOne);
        tileMap.add(lianaTwo);
    }

    //
    public static TileMap getInstance(){
        if (instance == null) {
            instance = new TileMap();
        }
        return instance;
    }

    public ArrayList<Tile> getArray() {
        return this.tileMap;
    }
}
