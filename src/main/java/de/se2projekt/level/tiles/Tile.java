package de.se2projekt.level.tiles;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import org.json.simple.JSONObject;


public abstract class Tile implements Cloneable {

    /* Legt fest, ob das Tile solide ist*/
    private boolean isSolid;
    /* Legt fest, ob das Tile toetlich ist*/
    private boolean isDeadly;
    /* Legt fest, ob das Tile erklimmbar ist*/
    private boolean isClimbable;
    /* Id des Tiles*/
    private final int tileId;
    /* x Koordinate des Tiles*/
    private int x;
    /* y Koordinate des Tiles*/
    private int y;
    /* Breite des Tiles*/
    private final int width;
    /* Hoehe des Tiles*/
    private final int height;
    /* Pfad der Imagedatei*/
    private final Image image;


    @Override
    public String toString() {
        return "Tile{" +
                "tileId=" + tileId +
                ", x=" + x +
                ", y=" + y +
                ", image=" + image +
                '}';
    }

    /**
     * [Konstruktor] Initialisiert das Tile mit den Werten
     */

    public Tile(final boolean isSolid, final boolean isDeadly, final boolean isClimbable, final int tileId, final int x, final int y, final Image image) {
        this.isSolid = isSolid;
        this.isDeadly = isDeadly;
        this.isClimbable = isClimbable;
        this.tileId = tileId;
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        this.image = image;
    }

    /*
     * Zu überschreibende Methode, falls der Spieler mit diesem Tile collediert
     */
    public abstract void collide();

    /*
     * Gibt die Kollisionbox zurueck
     */
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    /*
     * Clont das Tile
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    /*
     * Getter & Setter
     */
    public boolean isSolid() {
        return this.isSolid;
    }

    public void setSolid(final boolean isSolid) {
        this.isSolid = isSolid;
    }

    public boolean isDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(final boolean isDeadly) {
        this.isDeadly = isDeadly;
    }

    public boolean isClimbable() {
        return this.isClimbable;
    }

    public void setClimbable(final boolean climbable) {
        this.isClimbable = climbable;
    }

    public int getTileId() {
        return this.tileId;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Image getImage() {
        return image;
    }

    public void setPos(final int x, final int y){
        this.x = x;
        this.y = y;
    }

    public JSONObject getAsJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", tileId);
        jsonObject.put("x", x);
        jsonObject.put("y", y);
        // TODO hier muss man was ändern
        jsonObject.put("image", image.getUrl());

        return jsonObject;
    }

}
