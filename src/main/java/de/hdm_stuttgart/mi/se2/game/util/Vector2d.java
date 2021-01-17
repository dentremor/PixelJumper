package de.hdm_stuttgart.mi.se2.game.util;

/**
 * Vector class
 * @author Cazim Ukela
 */
public class Vector2d {

    // X
    public double x;

    // Y
    public double y;

    /**
     * Constructor
     *
     * @param x
     * @param y
     */
    public Vector2d(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Adds a Vector to the current
     *
     * @param vec | Other Vector
     */
    public void add(Vector2d vec){
        x = x + vec.x;
        y = y + vec.y;
    }

    /**
     * Removes a Vector from to current
     *
     * @param vec | Other Vector
     */
    public void sub(Vector2d vec) {
        x = x - vec.x;
        y = y - vec.y;
    }

}
