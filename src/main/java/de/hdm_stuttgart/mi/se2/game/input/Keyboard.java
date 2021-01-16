package de.hdm_stuttgart.mi.se2.game.input;
public class Keyboard{

    private static Keyboard instance;
    private boolean[] keys;

    public Keyboard(){
        this.keys = new boolean[1000];
    }

    public void setKeyPressed(int keyCode){
        this.keys[keyCode] = true;

    }

    public void setKeyReleased(int keyCode){
        this.keys[keyCode] = false;

    }

    public boolean isKeyDown(int keyCode){
        return keys[keyCode];
    }

    public static Keyboard getInstance() {
        return (instance == null) ?  instance = new Keyboard() : instance;
    }

}
