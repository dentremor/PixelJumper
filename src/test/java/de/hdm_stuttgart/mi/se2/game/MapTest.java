package de.hdm_stuttgart.mi.se2.game;

import de.hdm_stuttgart.mi.se2.game.level.map.Map;
import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MapTest {

    @Test(expected = IOException.class)
    public void testNonExistentMap_GetMap() throws IOException {
        Random r = new Random();
        int low = 100000000;
        int high = 999999999;
        int result = r.nextInt(high-low) + low;
        ArrayList<Tile> map= Map.MapManager.getMap(String.valueOf(result));
        throw new NullPointerException();
    }
}

