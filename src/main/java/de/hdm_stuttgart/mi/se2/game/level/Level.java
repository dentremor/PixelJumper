package de.hdm_stuttgart.mi.se2.game.level;

import de.hdm_stuttgart.mi.se2.game.level.map.Map;
import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import de.hdm_stuttgart.mi.se2.game.main.GameManager;
import de.hdm_stuttgart.mi.se2.game.util.Config;
import de.hdm_stuttgart.mi.se2.game.util.Vector2d;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import java.io.IOException;
import java.util.ArrayList;

public class Level {

    private GameManager gameManager;

    private ArrayList<Tile> tiles;
    private Tile startTile;
    private Tile finishTile;
    private int score;
    private Image background;
    private int xOffset;

    public Level(GameManager gameManager){
        this.gameManager = gameManager;
        this.score = 0;
        this.background = new Image(GameManager.class.getResource("/images/guiElements/background900x900_loopable.png").toString());
        this.xOffset = 0;
    }

    public void createLevel() {
        try {
            tiles = Map.MapManager.getMap("cazim");
            for(Tile t : tiles){
                t.setX(t.getX() * Config.Global.TILE_SIZE);
                t.setY(t.getY() * Config.Global.TILE_SIZE);
                if(t.getImage().getTileType().equals(Config.TileType.START_TYPE)){
                    startTile = t;
                    gameManager.getPlayer().setPos(new Vector2d(t.getX(),t.getY()));
                }else if(t.getImage().getTileType().equals(Config.TileType.FINISH_TYPE)){
                    finishTile = t;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getBackground() {
        return background;
    }

    public void addScore(){
        score++;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public Tile getFinishTile() {
        return finishTile;
    }
}
