package de.se2projekt.gui;

import de.se2projekt.highscore.HighscoreManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

/**
 * JavaFX controller for the Highscore menu
 * @author Niklas Mäckle
 */

public class HighscoreController {

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

    @FXML
    public Label highscore;

    public void handleReturnButtonClick() throws IOException {
        log.info("Return button clicked");

        returnButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml")));
    }

    public void handleLvl1ButtonClick(){

        //temporary setup for a Highscore in Level 1
        HighscoreManager hmlvl1 = new HighscoreManager("lvl1");

        hmlvl1.addScore("Name 1", 2020);
        hmlvl1.addScore("Name 2", 200);
        hmlvl1.addScore("Name 3", 20);
        hmlvl1.addScore("Name 4", 202);
        hmlvl1.addScore("Name 5", 220);

        highscore.setText(hmlvl1.getHighscoreString());

    }

    public void handleLvl2ButtonClick(){

        //temporary setup for a Highscore in Level 2
        HighscoreManager hmlvl2 = new HighscoreManager("lvl2");

        hmlvl2.addScore("Name 1", 456);
        hmlvl2.addScore("Name 2", 678);
        hmlvl2.addScore("Name 3", 342);
        hmlvl2.addScore("Name 4", 123);
        hmlvl2.addScore("Name 5", 455);

        highscore.setText(hmlvl2.getHighscoreString());

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
