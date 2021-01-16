package de.hdm_stuttgart.mi.se2.game.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * JavaFX controller for the level selection menu
 * @author Niklas Mäckle
 */

public class LevelSelectController {

    private final Logger log = LogManager.getLogger(LevelSelectController.class);

    @FXML
    public Button returnButton;

    @FXML
    public Button level1;

    @FXML
    public Button level2;

    @FXML
    public Button level3;

    @FXML
    public Button level4;

    @FXML
    public Button level5;

    @FXML
    public Button level6;

    @FXML
    public Button level7;

    @FXML
    public Button level8;

    @FXML
    public Button level9;

    public void handleReturnButtonClick() throws IOException {
        log.info("return button clicked");

        returnButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml")));
    }

    public void handleLvl1ButtonClick(){

    }

    public void handleLvl2ButtonClick(){

    }

    public void handleLvl3ButtonClick(){

    }

    public void handleLvl4ButtonClick(){

    }

    public void handleLvl5ButtonClick(){

    }

    public void handleLvl6ButtonClick(){

    }

    public void handleLvl7ButtonClick(){

    }

    public void handleLvl8ButtonClick(){

    }

    public void handleLvl9ButtonClick(){

    }

}
