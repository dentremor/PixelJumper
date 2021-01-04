package de.se2projekt.level.tiles;

public class BasicSolidTile extends BasicTile {

    /**
     * [Konstruktor] Initialisiert den Block mit den übergebenen Werten
     */
    public BasicSolidTile(int tileId, int x, int y, String imagePath) {
        super(tileId, x, y, imagePath);
        setSolid(true);
    }

}
