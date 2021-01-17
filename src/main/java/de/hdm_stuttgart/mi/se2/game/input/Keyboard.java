package de.hdm_stuttgart.mi.se2.game.input;

/**
 * Class that Controls the Keyboardinputs
 * @author Cazim Ukela
 */
public class Keyboard{

    // Instance of this class
    private static Keyboard instance;

    // Array that holds all pressed keys
    private boolean[] keys;

    /**
     * Constructor
     */
    public Keyboard(){
        this.keys = new boolean[1000];
    }

    /**
     * Adds the Pressed KeyCode to the key array
     *
     * @param keyCode | KeyCode of the Key which is pressed
     */
    public void setKeyPressed(int keyCode){
        this.keys[keyCode] = true;

    }

    /**
     * Removes the Released KeyCode to the key array
     *
     * @param keyCode | KeyCode of the Key which is released
     */
    public void setKeyReleased(int keyCode){
        this.keys[keyCode] = false;

    }

    /**
     * Checks if the the Key is pressed
     *
     * @param keyCode
     * @return | If the Key with the keyCode is pressed
     */
    public boolean isKeyDown(int keyCode){
        return keys[keyCode];
    }

    /**
     *
     * @return Keyboard instance
     */
    public static Keyboard getInstance() {
        return (instance == null) ?  instance = new Keyboard() : instance;
    }

}
