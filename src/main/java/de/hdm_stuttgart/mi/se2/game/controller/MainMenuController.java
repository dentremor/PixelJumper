package de.hdm_stuttgart.mi.se2.game.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * JavaFX controller for the main menu
 * @author Niklas Mäckle
 */

public class MainMenuController {

    private final static Logger log = LogManager.getLogger(MainMenuController.class);

    @FXML
    public Button startButton;

    @FXML
    public Button levelSelectButton;

    @FXML
    public Button levelEditorButton;

    @FXML
    public Button highscoreButton;

    @FXML
    public Button exitButton;

    public void handleStartButtonClick() throws IOException {
        log.info("start button clicked");
        startButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/game.fxml")));
    }

    public void handleLevelSelectButtonClick() throws IOException {
        log.info("level select button clicked");

        levelSelectButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/levelSelect.fxml")));
    }

    public void handleLevelEditorButtonClicked() throws IOException{
        log.info("level editor button clicked");

        // Get the currently used stage
        Window owner = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
        owner.setWidth(1900);
        levelEditorButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/editor.fxml")));
    }

    public void handleHighscoreButtonClicked() throws IOException {
        log.info("highscore button clicked");
        highscoreButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/highscore.fxml")));
    }

    public void handleExitButtonClicked() {
        log.info("Exit button clicked");

        log.info("Closing current stage");
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
