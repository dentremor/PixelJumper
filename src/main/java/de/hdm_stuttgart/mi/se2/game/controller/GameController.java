package de.hdm_stuttgart.mi.se2.game.controller;

import de.hdm_stuttgart.mi.se2.game.main.GameManager;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * JavaFX Controller for the Game.
 * @author Cazim Ukela
 */
public class GameController {

    //Logger
    private static final Logger log = LogManager.getLogger(GameController.class);

    // Custom Variables
    private GameManager gameManager;
    private AnimationTimer timer;

    // Variables from the .fxml-file
    public AnchorPane rootPane;

    /**
     * Runs when the GameController is launched
     */
    @FXML
    public void initialize() {

        this.gameManager = new GameManager(this);

        final Canvas canvas = new Canvas(1600, 900);
        this.rootPane.getChildren().add(canvas);

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        startGameLoop(gc);
    }

    /**
     * Starts the Animationtimer for the gameloop
     *
     * @param gc | GraphicsContext to draw Images
     */
    private void startGameLoop(final GraphicsContext gc) {

        log.info("Start GameLoop");

        this.timer = new AnimationTimer() {

            long lastTime = System.nanoTime();
            final double nsPerUpdate = 1000000000D / 60;

            int updates = 0;
            int frames = 0;

            long lastTimer = System.currentTimeMillis();
            double delta = 0;

            @Override
            public void handle(final long nowTime) {
                this.delta += (nowTime - this.lastTime) / this.nsPerUpdate;

                this.lastTime = nowTime;
                boolean shouldRender = false;

                while (this.delta >= 1) {
                    this.updates++;
                    GameController.this.gameManager.update();
                    this.delta -= 1;
                    shouldRender = true;
                }

                if (shouldRender) {
                    GameController.this.gameManager.render(gc);
                    this.frames++;
                }

                if (System.currentTimeMillis() - this.lastTimer >= 1000) {
                    this.lastTimer += 1000;
                    this.frames = 0;
                    this.updates = 0;
                }
            }
        };

        this.timer.start();
    }

    /**
     * Stopps the gameloop
     */
    public void stopGameLoop(){
        log.info("Stop GameLoop");
        timer.stop();
    }


}
