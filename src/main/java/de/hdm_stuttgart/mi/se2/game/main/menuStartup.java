package de.hdm_stuttgart.mi.se2.game.main;

import de.hdm_stuttgart.mi.se2.game.input.Keyboard;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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
    public void start(final Stage primaryStage) {
        log.info("trying to create menu stage");
        try {
            log.info("Creating parent root from mainMenu.fxml");
            final Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml"));

            log.info("Creating scene from root");
            final Scene scene = new Scene(root);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();

            log.info("Set Keylistener");
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(final KeyEvent keyEvent) {
                    Keyboard.getInstance().setKeyPressed(keyEvent.getCode().getCode());
                }
            });
            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(final KeyEvent keyEvent) {
                    Keyboard.getInstance().setKeyReleased(keyEvent.getCode().getCode());
                }
            });

            log.info("Setting stage properties");
            primaryStage.setTitle("Menu");
            primaryStage.setScene(scene);

            // Log the width and height of the different scenes
            scene.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(final ObservableValue<? extends Number> observableValue, final Number oldSceneWidth, final Number newSceneWidth) {
                    log.info("The width of the scene was changed from " + oldSceneWidth + " to " + newSceneWidth);
                }});
            scene.heightProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(final ObservableValue<? extends Number> observableValue, final Number oldSceneHeight, final Number newSceneHeight) {
                    log.info("The width of the scene was changed from " + oldSceneHeight + " to " + newSceneHeight);
                }});

            log.info("Showing stage");
            primaryStage.show();
        } catch (final IOException e){
            log.error("An Error occurred while trying to create menu stage", e);
        }
    }

    public void load(){
        launch();
    }
}
