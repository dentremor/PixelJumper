package de.hdm_stuttgart.mi.se2.game;

import de.hdm_stuttgart.mi.se2.game.highscore.HighscoreManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class HighscoreTest {
    @Test
    public void testHighscoreManagerCreation() {
        HighscoreManager highscoreManager = new HighscoreManager("levelname1");
        highscoreManager.addScore("playername", 15);

        File level = new File("src/main/resources/highscores/levelname1.dat");
        Assert.assertTrue(level.exists());
    }

    @Test
    public void addingScoresTest(){
        File level = new File("src/main/resources/highscores/levelname2.dat");
        if(level.exists()){
            level.delete();
        }
        HighscoreManager highscoreManager = new HighscoreManager("levelname2");
        highscoreManager.addScore("playername", 15);
        String testString = highscoreManager.getHighscoreString();

        Assert.assertEquals(testString, "1.\tplayername\t  15\n");
    }
}
