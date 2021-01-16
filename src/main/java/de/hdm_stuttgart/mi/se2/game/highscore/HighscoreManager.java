package de.hdm_stuttgart.mi.se2.game.highscore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * HighscoreManager that holds all methods for displaying the Scores achieved in each level and creating a list of scores
 * Required in a level to display a Highscore
 * HighscoreManager requires String filename to be created
 *  @author Niklas Mäckle
 */
public class HighscoreManager {
    private final Logger log = LogManager.getLogger(HighscoreManager.class);
    private ArrayList<Score> scores;
    private static String HIGHSCORE_FILE;

    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    /**
     *
     * Constructor for HighscoreManager that takes a filename to create the .dat file's name in which the Highscores are saved of a level for when you restart the game
     */
    public HighscoreManager(String filename){
        log.info("Building .dat file's name out of given filename...");
        HIGHSCORE_FILE = filenameBuilder(filename);
        scores = new ArrayList<>();
    }

    /**
     * getScores() creates a sorted version of the "scores" ArrayList
     */
    public ArrayList<Score> getScores(){
        loadScoreFile();
        log.info("Returning scores ArrayList...");
        return scores;
    }

    /**
     * filenameBuilder() creates the filename for the .dat file in which a level's highscore information is stored
     */
    private static String filenameBuilder(String filename){
        StringBuffer bufferedName = new StringBuffer(filename);
        bufferedName.append(".dat");
        bufferedName.insert(0, "src/main/resources/highscores/");
        return bufferedName.toString();
    }

    /**
     * addScore() takes a String and an int value and creates a new Score it then adds to the scoreFile
     * @param name name of the player
     * @param points amount of points the player achieved
     */
    public void addScore(String name, int points){
        loadScoreFile();
        log.info("Adding new score to the scores ArrayList...");
        scores.add(new Score(name, points));
        log.info("Updating score file...");
        writeScoreFile();
    }

    /**
     * loadScoreFile() tries to load the highscore file and checks if it exists
     */
    public void loadScoreFile(){
        log.info("Trying to load highscore file...");
        try{
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            log.info("Creating ArrayList out of loaded values...");
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e){
            log.error("FileNotFoundException occured: " + e.getMessage());
        } catch (IOException e) {
            log.error("IOException occured: " + e.getMessage());
        } catch (ClassNotFoundException e){
            log.error("ClassNotFoundException occured: " + e.getMessage());
        }
        finally {
            try{
                if (outputStream != null){
                    log.info("Closing output stream...");
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e){
                log.error("IOException occured: " + e.getMessage());
            }
        }
    }

    /**
     * updateScoreFile() updates the score file if it exists
     */
    public void writeScoreFile(){
        try{
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e){
            log.error(e.getMessage());
        } catch (IOException e){
            log.error(e.getMessage());
        } finally {
            try{
                if(outputStream != null){
                    outputStream.flush();
                    outputStream.close();
                }
            }catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * getHighscoreString() builds the String that displays a highscore
     */
    public String getHighscoreString(){
        String highscoreString = "";

        //amount of displayed scores
        final int maxDisplayed = 10;

        ArrayList<Score> scores;
        scores = getScores();

        log.info("Sorting ArrayList scores...");
        List<Score> sortedList = scores.stream()
                .sorted(Comparator.comparingInt(Score::getPoints)
                .reversed()).collect(Collectors.toList());

        int i = 0;

        int amountOfScores = scores.size();

        if(amountOfScores > maxDisplayed){
            amountOfScores = maxDisplayed;
        }
        log.info("Building highscore string...");
        while(i < amountOfScores){
            highscoreString += (i + 1) + ".\t" + sortedList.get(i).getName() + "\t  " + sortedList.get(i).getPoints() + "\n";
            i++;
        }
        log.info("Displaying highscore string...");
        return highscoreString;
    }
}
