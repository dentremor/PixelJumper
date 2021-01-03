package de.se2projekt.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * JavaFX controller for the options menu
 * @author Niklas Mäckle
 */

public class OptionsController {

    private final Logger log = LogManager.getLogger(LevelSelectController.class);

    @FXML
    public Button returnButton;

    @FXML
    public Slider volumeSlider;

    @FXML
    public Slider musicSlider;

    public void handleReturnButtonClick() throws IOException {
        log.info("level select button clicked");

        returnButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml")));
    }

}
