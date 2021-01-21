package de.hdm_stuttgart.mi.se2.game.controller;

import de.hdm_stuttgart.mi.se2.game.main.GameManager;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.scene.media.MediaPlayer;

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
    private Thread musicThread;

    // Variables from the .fxml-file
    public AnchorPane rootPane;

    /**
     * Runs when the GameController is launched
     */
    @FXML
    public void initialize() {


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
                    gameManager.update();
                    this.delta -= 1;
                    shouldRender = true;
                }

                if (shouldRender) {
                    gameManager.render(gc);
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
        musicThread.interrupt();


    }

    /**
     * Starts the Game with the levelName and the playerName
     *
     * @param levelName | Name of the Level
     * @param playerName | Name of the Player
     */
    public void startGame(String levelName,String playerName) {

        this.gameManager = new GameManager(this,levelName,playerName);

        final Canvas canvas = new Canvas(1600, 900);
        this.rootPane.getChildren().add(canvas);

        final GraphicsContext gc = canvas.getGraphicsContext2D();


        createMusicThread();
        startGameLoop(gc);



    }

    /**
     * Goes to the start screen
     */
    public void goToStart() {
        try {
            rootPane.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml")));
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * Creates a new Thread for the Music and plays it
     *
     */
    private void createMusicThread(){
        musicThread = new Thread(){
            @Override
            public void run() {
                MediaPlayer mediaPlayer = null;
                try {
                    mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/music/game1verykurz.wav").toURI().toString()));
                } catch (URISyntaxException e) {
                    log.error(e);
                }


                final MediaPlayer finalMediaPlayer = mediaPlayer;
                mediaPlayer.setOnEndOfMedia(new Runnable() {
                    public void run() {
                        finalMediaPlayer.seek(Duration.ZERO);
                    }
                });
                mediaPlayer.play();




                while (!isInterrupted()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        mediaPlayer.stop();
                    }
                }

            }
        };
        musicThread.setName("Music Thread");
        musicThread.start();

    }
}
