package de.se2projekt.highscore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 * Score class to create a new Score object containing of an int "points" and a String "name" to add to the HighscoreList of a level
 * @author Niklas Mäckle
 */
public class Score implements Serializable, Comparable<Score> {
    private final Logger log = LogManager.getLogger(Score.class);

    private int points;
    private String name;

    public Score(String name, int points){
        log.info("creating new score...");
        this.points = points;
        this.name = name;
    }

    public int getPoints(){
        log.info("returning points...");
        return points;
    }

    public String getName(){
        log.info("returning name...");
        return name;
    }

    @Override
    public int compareTo(Score score1){
        return((Integer)(score1.getPoints())).compareTo(getPoints());
    }

}
