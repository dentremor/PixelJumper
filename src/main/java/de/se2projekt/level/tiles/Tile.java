package de.se2projekt.level.tiles;

import java.awt.*;

public abstract class Tile implements Cloneable{

    /* Legt fest, ob das Tile solide ist*/
    private boolean isSolid;
    /* Legt fest, ob das Tile toetlich ist*/
    private boolean isDeadly;
    /* Legt fest, ob das Tile erklimmbar ist*/
    private boolean isClimbable;
    /* Id des Tiles*/
    private int tileId;
    /* x Koordinate des Tiles*/
    private int x;
    /* y Koordinate des Tiles*/
    private int y;
    /* Breite des Tiles*/
    private int width;
    /* Hoehe des Tiles*/
    private int height;
    /* Pfad der Imagedatei*/
    private String imagePath;


    /**
     * [Konstruktor] Initialisiert das Tile mit den Werten
     */
    public Tile(boolean isSolid, boolean isDeadly, boolean isClimbable, int tileId, int x, int y, String imagePath) {
        this.isSolid = isSolid;
        this.isDeadly = isDeadly;
        this.isClimbable = isClimbable;
        this.tileId = tileId;
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        this.imagePath = imagePath;
    }

    /*
     * Zu überschreibende Methode, falls der Spieler mit diesem Tile collediert
     */
    public abstract void collide();

    /*
     * Gibt die Kollisionbox zurueck
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /*
     * Clont das Tile
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    /*
     * Getter & Setter
     */
    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    public boolean isDeadly() {
        return isDeadly;
    }

    public void setDeadly(boolean isDeadly) {
        this.isDeadly = isDeadly;
    }

    public boolean isClimbable() {
        return isClimbable;
    }

    public void setClimbable(boolean climbable) {
        this.isClimbable = climbable;
    }

    public int getTileId() {
        return tileId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImagePath() {
        return imagePath;
    }
}
