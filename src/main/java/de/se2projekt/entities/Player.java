package de.se2projekt.entities;

import de.se2projekt.gfx.Screen;
import de.se2projekt.input.Keyboard;
import de.se2projekt.main.GameManager;
import de.se2projekt.util.ImageHolder;
import de.se2projekt.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.event.KeyEvent;
import java.security.Key;


public class Player extends Entity{

    private State state;
    private State lastState;

    public Player(int xPos, int yPos) {
        super(xPos, yPos);
        init();
    }

    private void init() {
        setVel(new Vector2d(0, 0));
        setAcc(new Vector2d(0, 0));
    }

    public enum State {
        FALLING, JUMPING, GROUND
    }



    @Override
    public void update() {
        checkInput();

        getPos().x += getVel().x;
        getVel().x = 0;

        if(getPos().x <= 0) {
            getPos().x = 0;
        }


    }

    @Override
    public void render(GraphicsContext gc, Screen screen) {
        gc.drawImage(ImageHolder.INSTANCE.PLAYER_IMAGE,getPos().x,getPos().y);
    }


    private void checkInput(){
            if(Keyboard.getInstance().isKeyDown(KeyEvent.VK_D)) {
                getVel().x = 5;
            }else if(Keyboard.getInstance().isKeyDown(KeyEvent.VK_A)) {
                getVel().x = -5;
            }
    }
}
