package de.se2projekt.controller;

import de.se2projekt.main.GameManager;
import de.se2projekt.util.TileMap;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameController {
    private static final Logger log = LogManager.getLogger(GameController.class);

    private GameManager gameManager;
    private AnimationTimer timer;

    //Variablen aus der GUI
    public AnchorPane rootPane;

    @FXML
    public void initialize(){

        this.gameManager = new GameManager();

        Canvas canvas = new Canvas(1600,900);
        rootPane.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        startGameLoop(gc);
    }
    
    private void startGameLoop(GraphicsContext gc) {
        timer = new AnimationTimer() {

            long lastTime = System.nanoTime();
            double nsPerUpdate = 1000000000D / 60;

            int updates = 0;
            int frames = 0;

            long lastTimer = System.currentTimeMillis();
            double delta = 0;

            @Override
            public void handle(long nowTime) {
                delta += (nowTime - lastTime) / nsPerUpdate;

                lastTime = nowTime;
                boolean shouldRender = false;

                while (delta >= 1) {
                    updates++;
                    gameManager.update();
                    delta -= 1;
                    shouldRender = true;
                }

                if (shouldRender) {
                    gameManager.render(gc);
                    frames++;
                }

                if (System.currentTimeMillis() - lastTimer >= 1000) {
                    lastTimer += 1000;
                    frames = 0;
                    updates = 0;
                }
            }
        };

        timer.start();
    }

}
