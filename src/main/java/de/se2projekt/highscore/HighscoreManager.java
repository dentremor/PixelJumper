package de.se2projekt.highscore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * HighscoreManager that holds all methods for displaying the Scores achieved in each level and creating a list of scores
 * @author Niklas Mäckle
 */
public class HighscoreManager {
    private final Logger log = LogManager.getLogger(HighscoreManager.class);
    private ArrayList<Score> scores;

    private static final String HIGHSCORE_FILE = "scores.dat";

    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighscoreManager(){
        scores = new ArrayList<Score>();
    }

    public ArrayList<Score> getScores(){
        loadScoreFile();
        sort();
        log.info("returning sorted scores ArrayList...");
        return scores;
    }

    private String nameBuilder(String filename){
        StringBuffer buffedName = new StringBuffer(filename);
        buffedName.append(".dat");
        return buffedName.toString();
    }
    /**
     * sort() creates a new ScoreComparator with which it then sorts the scores ArrayList
     */
    private void sort(){
        Collections.sort(scores);
    }

    /**
     * addScore() takes a String and an int value and creates a new Score it then adds to the scoreFile
     * @param name name of the player
     * @param points amount of points the player achieved
     */
    public void addScore(String name, int points){
        loadScoreFile();
        log.info("adding new score to the scores ArrayList...");
        scores.add(new Score(name, points));
        log.info("updating score file...");
        updateScoreFile();
    }

    /**
     * loadScoreFile() tries to load the highscore file and checks if it exists
     */
    public void loadScoreFile(){
        log.info("trying to load highscore file...");
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
                    log.info("closing output stream...");
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
    public void updateScoreFile(){
        try{
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                if(outputStream != null){
                    outputStream.flush();
                    outputStream.close();
                }
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * getHighscoreString() builds the String that displays a highscore
     */
    public String getHighscoreString(){
        String highscoreString = "";

        //amount of displayed scores
        final int max = 10;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;

        //amount of scores in a HighscoreManager
        int x = scores.size();

        if(x > max){
            x = max;
        }
        log.info("Building highscore string...");
        while (i < x){
            highscoreString += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getPoints() + "\n";
            i++;
        }
        log.info("Displaying highscore string...");
        return highscoreString;
    }
}
