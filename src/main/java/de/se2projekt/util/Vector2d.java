package de.se2projekt.util;

public class Vector2d {

    /* x Koordinate*/
    public double x;
    /* y Koordinate*/
    public double y;

    /**
     * [Konstruktor] Initialisiert den Vektro
     */
    public Vector2d(double x, double y){
        this.x = x;
        this.y = y;
    }

    /*
     * Addiert einen Vektor
     */
    public void add(Vector2d vec){
        x = x + vec.x;
        y = y + vec.y;
    }

    /*
     * subtrahiert einen Vektor
     */
    public void sub(Vector2d vec) {
        x = x - vec.x;
        y = y - vec.y;
    }

}
