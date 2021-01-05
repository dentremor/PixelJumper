package de.se2projekt.main;

import de.se2projekt.controller.GameController;
import de.se2projekt.gfx.Screen;
import de.se2projekt.util.TileMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;


public class GameManager {



    public GameManager() {

    }

    public void update() {

    }

    public void render(GraphicsContext gc){

        gc.clearRect(0,0,1600,900);

        Image i = new Image(GameController.class.getResource(TileMap.getInstance().getArray().get(0).getImagePath()).toString());
        gc.drawImage(i,0,800);
        gc.drawImage(i,100,800);
    }
}
