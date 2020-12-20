package de.se2projekt.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Menu startup class
 * @author: Niklas Mäckle
 */
public class menuStartup extends Application {

   /* public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;
    public static final int BLOCK_SIZE = 16; */

   /* private final int UPS = 120;

    private GameManager gameManager;
    private Screen screen; */

    private final static Logger log = LogManager.getLogger(menuStartup.class);

    @Override
    public void start(Stage primaryStage) {
        log.info("trying to create menu stage");
        try {
            log.info("Creating parent root from mainMenu.fxml");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml"));

            log.info("Creating scene from root");
            Scene scene = new Scene(root);

            log.info("Setting stage properties");
            primaryStage.setTitle("Menu");
            primaryStage.setScene(scene);
            primaryStage.setWidth(1600);
            primaryStage.setHeight(900);

            log.info("Showing stage");
            primaryStage.show();
        } catch (IOException e){
            log.error("An Error occured while trying to create menu stage", e);
        }
    }

    /*private void startGameLoop(GraphicsContext gc,Stage gameStage) {
        new AnimationTimer(){

            long lastTime = System.nanoTime();
            double nsPerUpdate = 1000000000D / UPS;

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
                    update();
                    delta -= 1;
                    shouldRender = true;
                }

                if (shouldRender) {
                    render(gc);
                    frames++;
                }

                if (System.currentTimeMillis() - lastTimer >= 1000) {
                    lastTimer += 1000;
                    frames = 0;
                    updates = 0;
                }
            }
        }.start();


        gameStage.show();

    }*/

    /**
     *
     * Methode die gecallt wird wenn man den Startbutton im Menü anklickt
     */
    /*private void startup(Stage stage){
        gameManager = new GameManager();
            screen = new Screen();


            stage.setTitle("Hello World");

            Group root = new Group();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            Canvas canvas = new Canvas(WIDTH,HEIGHT);
            root.getChildren().add(canvas);

            GraphicsContext gc = canvas.getGraphicsContext2D();

            startGameLoop(gc, stage);
    }*/

    /*private void render(GraphicsContext gc) {
        gameManager.render(gc,screen);
    }

    private void update() {
    }
         */

    public void load(){
        launch();
    }
}
