package de.se2projekt.main;

import de.se2projekt.gfx.Screen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;


public class GameManager {



    public GameManager() {

    }

    public void render(GraphicsContext gc, Screen screen){

        gc.fillRect(0,0,400,400);
    }
}
