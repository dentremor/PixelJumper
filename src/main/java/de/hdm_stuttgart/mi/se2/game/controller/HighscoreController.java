package de.hdm_stuttgart.mi.se2.game.controller;

import de.hdm_stuttgart.mi.se2.game.highscore.HighscoreManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * JavaFX controller for the Highscore menu
 * @author Niklas Mäckle
 */

public class HighscoreController {

    private final Logger log = LogManager.getLogger(LevelSelectController.class);

    private boolean levelSelected = false;

    @FXML
    public Button returnButton;

    @FXML
    public Button showButton;

    @FXML
    public ListView listView;

    @FXML
    public Label highscore;

    /**
     * initialize() is called when the Highscore screen is opened to get all needed information to display the available levels you can then click on
     * to get information about their highscores
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
        log.info("Return button clicked");
        returnButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml")));
    }

    /**
     * if a level got selected in the list and you click the show button then handleShowButtonClicked() is called to display all highscores of that level
     */
    @FXML
    public void handleShowButtonClicked(){
        if(levelSelected == true) {
            log.info("Showing scores of selected level...");
            String levelName = listView.getSelectionModel().getSelectedItem().toString();
            System.out.println("Level Name: " + levelName);
            HighscoreManager highscoreManager = new HighscoreManager(levelName);
            highscoreManager.getHighscoreString();
            highscore.setText(highscoreManager.getHighscoreString());
        }
        else{
            highscore.setText("No level selected yet");
        }
    }

    /**
     * handleMouseClick() sets the boolean levelSelected to true, if a level in the ListView got selected
     */
    @FXML
    public void handleMouseClick(){
        levelSelected = true;
    }

    /**
     * getLevelAmount() returns an integer of the amount of levels there are
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
