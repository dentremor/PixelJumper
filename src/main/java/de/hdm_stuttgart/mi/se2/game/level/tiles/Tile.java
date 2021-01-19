package de.hdm_stuttgart.mi.se2.game.level.tiles;

import de.hdm_stuttgart.mi.se2.game.util.MyImage;
import javafx.scene.shape.Rectangle;
import org.json.simple.JSONObject;


public class Tile implements ITile {

    /* Legt fest, ob das Tile solide ist*/
    private boolean isSolid;
    /* Legt fest, ob das Tile toetlich ist*/
    private boolean isDeadly;

    private boolean isCollectable;
    private boolean isStart;
    private boolean isFinish;

    /* Legt fest, ob das Tile erklimmbar ist*/
    private boolean isClimbable;
    /* x Koordinate des Tiles*/
    private int x;
    /* y Koordinate des Tiles*/
    private int y;
    /* Breite des Tiles*/
    private final int width;
    /* Hoehe des Tiles*/
    private final int height;
    /* Pfad der Imagedatei*/
    private final MyImage image;


    @Override
    public String toString() {
        return "Tile{" +
                ", x=" + x +
                ", y=" + y +
                ", image=" + image +
                '}';
    }

    /**
     * [Konstruktor] Initialisiert das Tile mit den Werten
     */

    public Tile(final int x, final int y, final MyImage image) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        this.image = image;
    }

    /*
     * Gibt die Kollisionbox zurueck
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public boolean isSolid() {
        return this.isSolid;
    }

    @Override
    public void setSolid(final boolean isSolid) {
        this.isSolid = isSolid;
    }

    @Override
    public boolean isDeadly() {
        return this.isDeadly;
    }

    @Override
    public void setDeadly(final boolean isDeadly) {
        this.isDeadly = isDeadly;
    }

    @Override
    public boolean isClimbable() {
        return this.isClimbable;
    }

    @Override
    public void setClimbable(final boolean climbable) {
        this.isClimbable = climbable;
    }

    @Override
    public boolean isCollectable() {
        return isCollectable;
    }

    @Override
    public void setCollectable(final boolean collectable) {
        isCollectable = collectable;
    }

    @Override
    public boolean isStart() {
        return isStart;
    }

    @Override
    public void setStart(final boolean start) {
        isStart = start;
    }

    @Override
    public boolean isFinish() {
        return isFinish;
    }

    @Override
    public void setFinish(final boolean finish) {
        isFinish = finish;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setX(final int x) {
        this.x = x;
    }

    @Override
    public void setY(final int y) {
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public MyImage getImage() {
        return image;
    }

    @Override
    public void setPos(final int x, final int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public JSONObject getAsJson() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", x);
        jsonObject.put("y", y);
        final String url = image.getUrl();
        final String[] urlArray = url.split("/images/");
        jsonObject.put("image", "/images/"+urlArray[1]);

        return jsonObject;
    }
}

