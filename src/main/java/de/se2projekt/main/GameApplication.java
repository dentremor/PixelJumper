package de.se2projekt.main;

import de.se2projekt.gfx.Screen;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.File;

public class GameApplication extends Application {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int BLOCK_SIZE = 16;

    private final int UPS = 120;

    private GameManager gameManager;
    private Screen screen;

    /**
     * Öffnet das Menü und bietet verschiedene Buttons mit Optionen
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Menu");

        File bgFile = new File("res\\gfx\\background1280x720.png");
        String localUrl = bgFile.toURI().toURL().toString();
        Image bgImage = new Image(localUrl);

        VBox menuBox = new VBox(20);
        Scene scene = new Scene(menuBox);
        menuBox.setPrefSize(WIDTH,HEIGHT);
        menuBox.setBackground(new Background(new BackgroundImage(bgImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        Button startButton = new Button("Start");
        Button levelButton = new Button("Select Level");
        Button levelEditorButton = new Button("Level Editor");
        Button optionsButton = new Button("Options");
        Button exitButton = new Button("Quit Game");

        startButton.setMinWidth(300);
        levelButton.setMinWidth(300);
        levelEditorButton.setMinWidth(300);
        optionsButton.setMinWidth(300);
        exitButton.setMinWidth(300);
        //temporäres Label
        Label temp = new Label("temp");

        menuBox.getChildren().addAll(startButton, levelButton, levelEditorButton, optionsButton, exitButton);
        menuBox.setAlignment(Pos.CENTER);

        startButton.setOnAction(n -> {
            stage.close();
            startup(stage);
        });
        levelButton.setOnAction(n -> temp.setText("Button clicked"));
        levelEditorButton.setOnAction(n -> temp.setText("Button clicked"));
        optionsButton.setOnAction(n -> temp.setText("Button clicked"));
        exitButton.setOnAction(n -> stage.close());

        stage.setScene(scene);
        stage.show();

    }

    private void startGameLoop(GraphicsContext gc,Stage gameStage) {
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

    }

    /**
     *
     * Methode die gecallt wird wenn man den Startbutton im Menü anklickt
     */
    private void startup(Stage stage){
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

    private void render(GraphicsContext gc) {
        gameManager.render(gc,screen);
    }

    private void update() {
    }

    public void load(){
        launch();
    }
}
