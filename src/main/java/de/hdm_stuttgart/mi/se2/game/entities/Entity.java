package de.hdm_stuttgart.mi.se2.game.entities;

import de.hdm_stuttgart.mi.se2.game.gfx.Screen;
import de.hdm_stuttgart.mi.se2.game.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

/**
 * Abstract Class which hold all Entity attributes
 * @author Cazim Ukela
 */
public abstract class Entity {

    // Position of the Entity
    private Vector2d pos;

    // Velocity of the Entity
    private Vector2d vel;

    // Acceleration of the Entity
    private Vector2d acc;

    // Width of the Entity
    private int width;

    // Height of the Entity
    private int height;

    /**
     * Constructor
     *
     * @param xPos | X - Position of the Entity
     * @param yPos | Y - Position of the Entitiy
     */
    public Entity(int xPos, int yPos) {
        pos = new Vector2d(xPos, yPos);
        this.width = 50;
        this.height = 50;
    }

    /**
     * Updates the Entity. Has to be implemented by every childclass
     *
     */
    public abstract void update();

    /**
     * Draws the Entity to the Screen. Has to be implemented by every childclass
     *
     * @param gc | Needed to draw the images
     * @param screen | Helper Class to draw everything right
     */
    public abstract void render(GraphicsContext gc, Screen screen);

    /**
     * Needed for Collisiondetection
     *
     * @return rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle((int) pos.x, (int) pos.y, width, height);
    }


    //Getter & Setter
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
