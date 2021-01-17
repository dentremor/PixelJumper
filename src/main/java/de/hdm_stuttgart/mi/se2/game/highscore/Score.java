package de.hdm_stuttgart.mi.se2.game.highscore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 * Score class to create a new Score object containing of an int "points" and a String "name" to add to the HighscoreList of a level
 * @author Niklas Mäckle
 */
public class Score implements Serializable{
    private final Logger log = LogManager.getLogger(Score.class);

    private int points;
    private String name;

    public Score(String name, int points){
        log.info("creating new score...");
        this.points = points;
        this.name = name;
    }

    public int getPoints(){
        return points;
    }

    public String getName(){
        return name;
    }

}
