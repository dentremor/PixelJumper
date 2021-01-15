package de.se2projekt.controller;

import de.se2projekt.highscore.HighscoreManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.ColumnConstraints;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void initialize(){
        int fileAmount = getLevelAmount();
        ArrayList<String> filenames = getFileNames();
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        for(int i = 0; i < fileAmount; i++){
            listView.getItems().add(filenames.get(i));
        }
    }

    public void handleReturnButtonClicked() throws IOException {
        log.info("return button clicked");

        returnButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml")));
    }

    @FXML

    //TODO Methode implementieren
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

    private int getLevelAmount() {
        try {
            File directory = new File("src/main/resources/highscores/");
            int amount = directory.list().length;
            return amount;
        } catch (NullPointerException e) {
            log.error("NullPointerException occured: " + e.getMessage());
            return 0;
        }
    }

    private ArrayList<String> getFileNames(){
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
