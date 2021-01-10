package de.se2projekt.entities;

import de.se2projekt.gfx.Screen;
import de.se2projekt.input.Keyboard;
import de.se2projekt.level.tiles.Tile;
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
    private static final double GRAVITY = 1000;
    private GameManager gameManager;

    public Player(GameManager gameManager, int xPos, int yPos) {
        super(xPos, yPos);
        this.gameManager = gameManager;
        init();
    }

    private void init() {
        setVel(new Vector2d(0, 0));
        setAcc(new Vector2d(0, 0));
        this.state = State.GROUND;
        this.lastState = State.GROUND;
    }

    public enum State {
        FALLING, JUMPING, GROUND
    }

    @Override
    public void render(GraphicsContext gc, Screen screen) {
        screen.render(gc,(int) getPos().x,(int) getPos().y,ImageHolder.INSTANCE.PLAYER_IMAGE,gameManager.getxOffset());
    }

    @Override
    public void update() {
        checkInput();
        move();
    }

    private void move() {
        getPos().x += getVel().x;
        getVel().x = 0;

        if(getPos().x <= 0) {
            getPos().x = 0;
        }

        if ((lastState == State.GROUND && state == State.FALLING)) {
            getVel().y = 0;
        }else if (state == State.FALLING || state == State.JUMPING) {
            getAcc().y = GRAVITY;
            getPos().y = getPos().y + getVel().y * (1 / 60D) + (0.5 * getAcc().y * (1 / 60D) * (1 / 60D));
            if (getVel().y < 500) {
                getVel().y += getAcc().y * (1 / 60D);
            }
        }
    }

    public void defineJumpingOrFalling(Tile tile) {
        if (state != State.GROUND) {
            setState((getVel().y > 0) ? State.FALLING : State.JUMPING);
        }else if(tile == null) {
            getVel().y = 0;
            setState(State.FALLING);
        }

    }



    private void checkInput(){

            if (Keyboard.getInstance().isKeyDown(KeyEvent.VK_W) && state == State.GROUND) {
                setState(State.JUMPING);
                getVel().y = -400;
            }

            if(Keyboard.getInstance().isKeyDown(KeyEvent.VK_D)) {
                getVel().x = 5;
            }else if(Keyboard.getInstance().isKeyDown(KeyEvent.VK_A)) {
                getVel().x = -5;
            }
    }

    public void collide(int side, Tile tile) {
        if(side == 0 && state != State.GROUND && state != State.JUMPING) {
            getVel().y = 0;
            getAcc().y = 0;
            getPos().y = tile.getY() - getHeight();
            setState(State.GROUND);
        }else if(side == 1 && state != State.FALLING) {
            getVel().y = 0;
            getAcc().y = 0;
            getPos().y = tile.getY() + tile.getHeight();
        }else if(side == 2) {
            if(getVel().x >= 0) {
                getVel().x = 0;
                getAcc().x = 0;
                getPos().x = tile.getX() - getWidth();
            }
        }else if(side == 3) {
            if(getVel().x <= 0) {
                getVel().x = 0;
                getAcc().x = 0;
                getPos().x = tile.getX() + tile.getWidth();
            }
        }
    }

    public void setState(State state) {
        this.lastState = this.state;
        this.state = state;
    }
}
