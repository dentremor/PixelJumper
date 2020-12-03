package de.se2projekt.main;

import de.se2projekt.gfx.Screen;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.application.Application;

public class GameApplication extends Application {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int BLOCK_SIZE = 16;

    private final int UPS = 120;

    private GameManager gameManager;
    private Screen screen;

    @Override
    public void start(Stage stage) throws Exception {


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
    }

    private void startGameLoop(GraphicsContext gc,Stage stage) {
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


        stage.show();

    }

    private void render(GraphicsContext gc) {
        gameManager.render(gc,screen);
    }

    private void update() {
    }

    public void load(){
        launch();
    }
}
