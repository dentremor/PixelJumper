package de.se2projekt.entities;

import de.se2projekt.gfx.Screen;
import de.se2projekt.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;


public abstract class Entity {

    /* Position des Entity*/
    private Vector2d pos;
    /* Geschwindigkeit des Entity*/
    private Vector2d vel;
    /* Beschleunigung des Entity*/
    private Vector2d acc;
    /* Breite des Entity*/
    private int width;
    /* Hoehe des Entity*/
    private int height;

    /**
     * [Konstruktor] Initialisiert das Entity.
     */
    public Entity(int xPos, int yPos) {
        pos = new Vector2d(xPos, yPos);
        this.width = 50;
        this.height = 50;
    }

    /*
     * Abstrakte Methode update() für alle Entities
     */
    public abstract void update();
    /*
     * Abstrakte Methode render() für alle Entities
     */
    public abstract void render(GraphicsContext gc, Screen screen);

    /*
     * Gibt ein Rechteck für die Collisionsabfrage zurück
     */
    public Rectangle getBounds() {
        return new Rectangle((int) pos.x, (int) pos.y, width, height);
    }


    /*
     * Getter & Setter
     */
    public Vector2d getPos() {
        return pos;
    }

    public void setPos(Vector2d pos) {
        this.pos = pos;
    }

    public Vector2d getVel() {
        return vel;
    }

    public void setVel(Vector2d vel) {
        this.vel = vel;
    }

    public Vector2d getAcc() {
        return acc;
    }

    public void setAcc(Vector2d acc) {
        this.acc = acc;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
