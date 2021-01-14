package de.se2projekt.level.map;

import de.se2projekt.level.tiles.Tile;
import de.se2projekt.level.tiles.TileFactory;
import de.se2projekt.util.ImageHolder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MapManager {

    // Variables
    ArrayList<Tile> mapArray;

    public MapManager(final ArrayList<Tile> mapArray) {
        this.mapArray = mapArray;
    }

    //TODO Danny TRY WITH GSON
    public void exportMap(final String mapName) throws IOException {

        JSONArray mapArrayJson = new JSONArray();

        // Get objects for Json file
        for (int i = 0; i < this.mapArray.size(); i++) {
            JSONObject jsonObject = this.mapArray.get(i).getAsJson();
            mapArrayJson.add(jsonObject);
        }
        //Write JSON file
        try (final FileWriter file = new FileWriter("src/main/resources/maps/" + mapName + ".json")) {
            file.write(mapArrayJson.toString());
            file.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        getMapsAsArray();
    }

    public ArrayList<Tile> getMap(final String mapPath) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader rd = new BufferedReader(new FileReader(mapPath));
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

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            final Tile tile = new TileFactory().makeTile(Math.toIntExact((long) jsonObject.get("x")), Math.toIntExact((long) jsonObject.get("y")), ImageHolder.INSTANCE.getImage((String) jsonObject.get("image")));
            mapArray.add(tile);
        }
        return mapArray;
    }

    public ArrayList<File> getMapsAsArray() {
        ArrayList<File> mapsAsArray =  new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get("src/main/java/de/se2projekt/level/map/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapsAsArray;
    }
}


