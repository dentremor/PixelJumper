package de.se2projekt.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

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
    public Button optionsButton;

    @FXML
    public Button exitButton;

    public void handleStartButtonClick() throws IOException {
        log.info("start button clicked");

        //startButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/game.fxml")));
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

    public void handleOptionsButtonClicked() throws IOException {
        log.info("options button clicked");

        optionsButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/options.fxml")));
    }

    public void handleExitButtonClicked() {
        log.info("Exit button clicked");

        log.info("Closing current stage");
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
