package de.se2projekt.gfx;

import de.se2projekt.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SpriteSheet {

    /*SpriteSheet instanz */
    private static SpriteSheet instance;

    /*Pfad von dem SpriteSheet*/
    private static final String PATH = "res/gfx/spriteSheet.png";

    /*HashMap die alle Sprites speichert*/
    private HashMap<Integer, BufferedImage> sprites = new HashMap<>();


    /* Ids aller Sprites*/

    public static final int GROUND = 0;
    public static final int TREE = 1;



    /**
     * [Konstruktor] Initialisiert die Sprites und fügt sie zut HashMap hinzu
     */
    private SpriteSheet() {
        BufferedImage spriteSheet = ImageUtil.loadImage(PATH);

        sprites.put(GROUND, spriteSheet.getSubimage(32, 64, 16, 16));
        sprites.put(TREE, spriteSheet.getSubimage(64, 0, 32, 48));
    }

    /**
     * Gibt die Instanz zurück
     */
    public static SpriteSheet getInstance() {
        return (instance == null) ? instance = new SpriteSheet() : instance;
    }

    /*
     * Gibt das Sprite mit der Id "index" zurueck
     */
    public BufferedImage getSprite(int index) {
        return sprites.get(index);
    }

    public int[] getBuildableBlocks() {
        return new int[]{0,1};
    }
}
