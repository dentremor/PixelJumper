package de.se2projekt.controller;

import de.se2projekt.input.Keyboard;
import de.se2projekt.main.GameManager;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameController {
    private static final Logger log = LogManager.getLogger(GameController.class);

    private GameManager gameManager;
    private AnimationTimer timer;

    //Variablen aus der GUI
    public AnchorPane rootPane;

    @FXML
    public void initialize() {

        this.gameManager = new GameManager();

        final Canvas canvas = new Canvas(1600, 900);
        this.rootPane.getChildren().add(canvas);

        canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.err.println("TEWSAFR");
            }
        });

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        startGameLoop(gc);
    }

    private void startGameLoop(final GraphicsContext gc) {
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

}
