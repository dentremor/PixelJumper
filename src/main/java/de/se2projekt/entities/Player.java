package de.se2projekt.entities;

import de.se2projekt.gfx.Screen;
import de.se2projekt.main.GameManager;
import de.se2projekt.util.ImageHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Player extends Entity{


    public Player(int xPos, int yPos) {
        super(xPos, yPos);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc, Screen screen) {
        gc.drawImage(ImageHolder.INSTANCE.PLAYER_IMAGE,getPos().x,getPos().y);
    }
}
