package de.se2projekt.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
    public Button optionsButton;

    @FXML
    public Button exitButton;

    public void handleStartButtonClick() throws IOException {
        log.info("start button clicked");

        //startButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/game.fxml")));
    }

    public void handleLevelSelectButtonClick() throws IOException {
        log.info("level select button clicked");
    }

    public void handleLevelEditorButtonClicked() throws IOException{
        log.info("level editor button clicked");
        //auskommentiert weil's noch nicht funktioniert
        /*try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/editor.fxml"));

            Parent root1 = fxmlLoader.load();

            primaryStage.setTitle("LevelEditor");
            primaryStage.setScene(new Scene(root1));
            primaryStage.show();
        }catch(IOException e) {
            e.printStackTrace();
        }*/
    }

    public void handleOptionsButtonClicked() throws IOException {
        log.info("options button clicked");
    }

    public void handleExitButtonClicked() {
        log.info("Exit button clicked");

        log.info("Closing current stage");
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
