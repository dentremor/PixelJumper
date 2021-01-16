package de.hdm_stuttgart.mi.se2.game.entities;

import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import de.hdm_stuttgart.mi.se2.game.gfx.Screen;
import de.hdm_stuttgart.mi.se2.game.input.Keyboard;
import de.hdm_stuttgart.mi.se2.game.main.GameManager;
import de.hdm_stuttgart.mi.se2.game.util.ImageHolder;
import de.hdm_stuttgart.mi.se2.game.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;

import java.awt.event.KeyEvent;


public class Player extends Entity{

    private State state;
    private State lastState;
    private boolean rightDirection;
    private static final double GRAVITY = 1000;
    private GameManager gameManager;

    public Player(GameManager gameManager, int xPos, int yPos) {
        super(xPos, yPos);
        this.gameManager = gameManager;
        this.rightDirection = true;
        init();
    }

    private void init() {
        setVel(new Vector2d(0, 0));
        setAcc(new Vector2d(0, GRAVITY));
        this.state = State.GROUND;
        this.lastState = State.GROUND;
    }

    public enum State {
        FALLING, JUMPING, GROUND
    }

    @Override
    public void render(GraphicsContext gc, Screen screen) {
        screen.render(gc,(int) getPos().x,(int) getPos().y,(rightDirection ? ImageHolder.INSTANCE.PLAYER_RIGHT_IMAGE:ImageHolder.INSTANCE.PLAYER_LEFT_IMAGE),gameManager.getxOffset());
    }

    @Override
    public void update() {
        checkInput();
        move();
    }

    private void move() {

        double lastXPos = getPos().x;

        getPos().x += getVel().x;
        getVel().x = 0;

        if(getPos().x <= 0) {
            getPos().x = 0;
        }

        if(lastXPos != getPos().x){
            rightDirection = lastXPos < getPos().x;
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

    public void respawn(int x, int y){
        setVel(new Vector2d(0, 0));
        setAcc(new Vector2d(0, 0));
        setPos(new Vector2d(x,y));
        this.state = State.GROUND;
        this.lastState = State.GROUND;
        rightDirection = true;
    }

    public void setState(State state) {
        this.lastState = this.state;
        this.state = state;
    }
}
