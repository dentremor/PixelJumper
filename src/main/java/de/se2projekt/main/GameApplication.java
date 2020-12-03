package de.se2projekt.main;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.application.Application;

public class GameApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World");

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(1280,720);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.fillText("TEST",200,200);

        startGameLoop(gc, stage);
    }

    private void startGameLoop(GraphicsContext gc,Stage stage) {
        new AnimationTimer(){

            long lastTime = System.nanoTime();
            double nsPerUpdate = 1000000000D / 120;

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
    }

    private void update() {
    }

    public void load(){
        launch();
    }
}
