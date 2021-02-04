package de.hdm_stuttgart.mi.se2.game.level.map;

import de.hdm_stuttgart.mi.se2.game.level.tiles.Tile;
import de.hdm_stuttgart.mi.se2.game.level.tiles.TileFactory;
import de.hdm_stuttgart.mi.se2.game.util.Config;
import de.hdm_stuttgart.mi.se2.game.util.ImageHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class to instance the Tiles into an ArrayList with a subclass for handling the .json-files.
 * @author Daniel Hiller
 * @version 0.1.0
 */

public class Map {

    private static final Logger log = LogManager.getLogger(Map.class);

    // Variables
    ArrayList<Tile> mapArray;

    public Map(final ArrayList<Tile> mapArray) {
        this.mapArray = mapArray;
    }

    public void exportMap(final String mapName) throws IOException {

        final JSONArray mapArrayJson = new JSONArray();

        // Get objects for Json file
        for (int i = 0; i < this.mapArray.size(); i++) {
            final JSONObject jsonObject = this.mapArray.get(i).getAsJson();
            mapArrayJson.add(jsonObject);
        }
        //Write JSON file
        try (final FileWriter file = new FileWriter(Config.Map.MAP_PATH + mapName + ".json")) {
            file.write(mapArrayJson.toString());
            file.flush();
            log.info("The Map was exported as followed: " + Config.Map.MAP_PATH + mapName + ".json");
        } catch (final IOException e) {
            log.fatal(e.getMessage());
            e.printStackTrace();
        }
    }

    public static class MapManager{

        private static final Logger log = LogManager.getLogger(Map.MapManager.class);

        public static ArrayList<Tile> getMap(final String mapName) throws IOException {

            // StringBuilder for storing the whole file and get each char
            final StringBuilder sb = new StringBuilder();
            final BufferedReader rd = new BufferedReader(new FileReader("src/main/resources/maps/" + mapName + ".json"));
            int cp;
            final ArrayList<Tile> mapArray = new ArrayList<>();

            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            rd.close();

            // Converts the strings into a JSONArray
            final JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = new JSONArray();

            try {
                jsonArray = (JSONArray) jsonParser.parse(sb.toString());
            } catch (final ParseException e) {
                log.fatal(e.getMessage());
                e.printStackTrace();
            }

            // Converts the JSONArray in single JSONObjects
            for (int i = 0; i < jsonArray.size(); i++) {
                final JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                final Tile tile = new TileFactory().makeTile(Math.toIntExact((long) jsonObject.get("x")), Math.toIntExact((long) jsonObject.get("y")), ImageHolder.INSTANCE.getImage((String) jsonObject.get("image")));
                mapArray.add(tile);
            }
            log.info("The Map " + mapName + ".json was read successfully");
            return mapArray;
        }

        public static String[] getMapNamesAsArray() throws IOException{
            List<String> filenames = null;

            // Stream which returns all the FileNames as String without the fileTag
                filenames = Files.list(Paths.get(Config.Map.MAP_PATH))
                        .parallel()
                        .map(p -> p.getFileName().toString().replaceFirst("[.][^.]+$", ""))
                        .collect(Collectors.toList());
                log.info("Read all mapNames from following destination: " + "src/main/resources/maps/");

            return filenames.toArray(String[]::new);
        }
    }
}


