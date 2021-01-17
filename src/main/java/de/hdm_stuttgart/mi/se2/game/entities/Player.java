package de.hdm_stuttgart.mi.se2.game.entities;

import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import de.hdm_stuttgart.mi.se2.game.gfx.Screen;
import de.hdm_stuttgart.mi.se2.game.input.Keyboard;
import de.hdm_stuttgart.mi.se2.game.main.GameManager;
import de.hdm_stuttgart.mi.se2.game.util.Config;
import de.hdm_stuttgart.mi.se2.game.util.ImageHolder;
import de.hdm_stuttgart.mi.se2.game.util.Vector2d;
import javafx.scene.canvas.GraphicsContext;

import java.awt.event.KeyEvent;

/**
 * Class which represents the Player in the Game
 * @author Cazim Ukela
 */
public class Player extends Entity{

    // Enum which holds the state of the Player
    private State state;

    // Enum which holds the last state of the Player
    private State lastState;

    // Walkdirection od the Player
    private boolean rightDirection;

    // GameManager instance
    private GameManager gameManager;

    /**
     * Constructor
     *
     * @param gameManager | Instance of the GameManager
     * @param xPos | X - Position of the Entity
     * @param yPos | Y - Postiion of the Entity
     */
    public Player(GameManager gameManager, int xPos, int yPos) {
        super(xPos, yPos);
        this.gameManager = gameManager;
        this.rightDirection = true;
        init();
    }

    /**
     * Enum which represents the State of the Player
     */
    public enum State {
        FALLING, JUMPING, GROUND
    }


    /**
     * Set start values of the Player
     */
    private void init() {
        setVel(new Vector2d(0, 0));
        setAcc(new Vector2d(0, Config.Global.GRAVITY));
        this.state = State.GROUND;
        this.lastState = State.GROUND;
    }

    /**
     * Draws the Player to the Screen
     *
     * @param gc | Needed to draw the images
     * @param screen | Helper Class to draw everything right
     */
    @Override
    public void render(GraphicsContext gc, Screen screen) {
        screen.render(gc,(int) getPos().x,(int) getPos().y,(rightDirection ? ImageHolder.INSTANCE.PLAYER_RIGHT_IMAGE:ImageHolder.INSTANCE.PLAYER_LEFT_IMAGE),gameManager.getLevel().getxOffset());
    }

    /**
     * Updates the Player values
     */
    @Override
    public void update() {
        checkInput();
        move();
    }

    /**
     * Moves the Player depending on the State and Velocity
     */
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
            getAcc().y = Config.Global.GRAVITY;
            getPos().y = getPos().y + getVel().y * (1 / 60D) + (0.5 * getAcc().y * (1 / 60D) * (1 / 60D));
            if (getVel().y < 500) {
                getVel().y += getAcc().y * (1 / 60D);
            }
        }
    }

    /**
     * Controls weather the Player is falling or jumping
     *
     * @param tile
     */
    public void defineJumpingOrFalling(Tile tile) {
        if (state == State.FALLING || state == State.JUMPING) {
            setState((getVel().y > 0) ? State.FALLING : State.JUMPING);
        }else if(tile == null) {
            getVel().y = 0;
            setState(State.FALLING);
        }

    }


    /**
     * Reads the inputs of the Keyboard and set the Velocity depending on the inputs
     */
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

    /**
     * Calculates the Position, Velocity and Acceleration depending on the Collisionside with the Tile
     *
     * @param side | The Collisionside between the Player and the Tile
     * @param tile | Tile which the Player has collided with
     */
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

    /**
     * Respawns the Player if he dies.
     *
     * @param pos | Respawnposition
     */
    public void respawn(Vector2d pos){
        setVel(new Vector2d(0, 0));
        setAcc(new Vector2d(0, 0));
        setPos(pos);
        this.state = State.GROUND;
        this.lastState = State.GROUND;
        rightDirection = true;
    }

    /**
     * Sets the new state and saves the old state in lastState
     *
     * @param state | Playerstate
     */
    public void setState(State state) {
        this.lastState = this.state;
        this.state = state;
    }
}
