package de.se2projekt.main;

import de.se2projekt.gfx.Screen;
import de.se2projekt.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameManager {

    private BufferedImage background;

    public GameManager() {
        this.background = ImageUtil.loadImage("res/gfx/background.png");
        //TEST

    }

    public void render(Graphics g, Screen screen) {

        screen.render(g,0,0,background,0);
        for (int i = 0; i < 16; i++) {
            screen.render(g,0,210,0,16*i);
        }
        screen.render(g,100,162,1,0);

    }
}
