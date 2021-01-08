package de.se2projekt.level.map;

import de.se2projekt.level.tiles.Tile;
import de.se2projekt.level.tiles.TileFactory;
import de.se2projekt.util.ImageHolder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MapManager {

    // Variables
    ArrayList<Tile> mapArray;

    public MapManager(final ArrayList<Tile> mapArray) {
        this.mapArray = mapArray;
    }

    //TODO Danny TRY WITH GSON
    public void exportMap(final String mapName) throws IOException {
        // New
//        System.out.println(mapArray.toString());
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(new File("src/main/resources/maps/" + mapName + ".json"), mapArray.toString());

        // Old

        JSONArray mapArrayJson = new JSONArray();

        // Get objects for Json file
        for (int i = 0; i < this.mapArray.size(); i++) {
            JSONObject jsonObject = this.mapArray.get(i).getAsJson();
            mapArrayJson.add(jsonObject);
        }
        //Write JSON file
        try (final FileWriter file = new FileWriter("src/main/resources/maps/" + mapName + ".json")) {
            file.write(mapArrayJson.toString());
            System.out.println(mapArrayJson.toString());
            file.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tile> getMap(final String mapName) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader rd = new BufferedReader(new FileReader("src/main/resources/maps/" + mapName + ".json"));
        int cp;
        ArrayList<Tile> mapArray = new ArrayList<>();

        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        rd.close();
        System.out.println(sb.toString());
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();

        try {
            jsonArray = (JSONArray) jsonParser.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("dsfjdsafkjhsdakjhlfadsjhdsfalhadsfjhlkjhfdlhkj");

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            final Tile tile = new TileFactory().makeTile(i, Math.toIntExact((long) jsonObject.get("x")), Math.toIntExact((long) jsonObject.get("y")), ImageHolder.INSTANCE.getImage((String) jsonObject.get("image")));
            System.out.println(tile.toString());
            mapArray.add(tile);
        }
        return mapArray;
    }

    //TODO Danny *implement* File method for check all mapFiles
}


