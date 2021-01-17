package de.hdm_stuttgart.mi.se2.game.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * JavaFX controller for the level selection menu
 * @author Niklas Mäckle
 */

public class LevelSelectController {

    private final Logger log = LogManager.getLogger(LevelSelectController.class);

    @FXML
    public Button returnButton;

    @FXML
    public Button playButton;

    @FXML
    public ListView listView;

    //TODO Button Anordnung anpassen

    /**
     * initialize() is called when the Level Select screen is opened to get all needed information to display the available levels
     */
    @FXML
    public void initialize(){
        int fileAmount = getLevelAmount();
        ArrayList<String> filenames = getFileNames();
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        for(int i = 0; i < fileAmount; i++){
            listView.getItems().add(filenames.get(i));
        }
    }

    /**
     * handleReturnButton() is called when you click the button to get back into the main menu
     * @throws IOException
     */
    @FXML
    public void handleReturnButtonClicked() throws IOException {
        log.info("return button clicked");

        returnButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml")));
    }

    //TODO Methode implementieren

    /**
     * When a level is selected and you click the start button that level will get opened up by the handleStartButtonClicked() function
     */
    @FXML
    public void handleStartButtonClicked(){
        /*if(levelSelected == true) {
            log.info("Showing scores of selected level...");
            String levelName = listView.getSelectionModel().getSelectedItem().toString();
            System.out.println("Level Name: " + levelName);
            HighscoreManager highscoreManager = new HighscoreManager(levelName);
            highscoreManager.getHighscoreString();
            highscore.setText(highscoreManager.getHighscoreString());
        }
        else{
            highscore.setText("No level selected yet");
        }*/
    }

    /**
     * getLevelAmount() returns an int of the amount of levels there are
     * @return either the amount of levels there are or 0 if a NullPointerException occurs
     */
    private int getLevelAmount() {
        log.info("Trying to get the amount of levels there are...");
        try {
            File directory = new File("src/main/resources/highscores/");
            int amount = directory.list().length;
            log.info("Returning the amount of levels as an int value");
            return amount;
        } catch (NullPointerException e) {
            log.error("NullPointerException occured: " + e.getMessage());
            return 0;
        }
    }

    /**
     * getFileNames() returns an ArrayList with the names of all levels there are
     * @return ArrayList names consisting out of strings
     */
    private ArrayList<String> getFileNames(){
        log.info("Creating an ArrayList out of the names of all levels...");
        File directory = new File("src/main/resources/highscores");
        ArrayList<String> names = new ArrayList<>(Arrays.asList(directory.list()));
        for(int i = 0; i < names.size(); i++){
            StringBuffer bufferedFilename = new StringBuffer(names.get(i));
            String filename = bufferedFilename.delete(bufferedFilename.length()-4, bufferedFilename.length()).toString();
            names.set(i, filename);
        }
        return names;
    }


}
