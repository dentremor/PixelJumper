package de.hdm_stuttgart.mi.se2.game.util;

/**
 * A class for spacing the magic numbers.
 * @author Daniel Hiller
 * @version 0.1.0
 */

public class Config {

    public static class Global{
        public static final int GAME_WIDTH = 1600;
        public static final int GAME_HEIGHT = 900;
        public static final int TILE_SIZE = 50;
        public static final double GRAVITY = 1000;
    }

    public static class Map {
        public static final int COLUMN_SIZE = 18;
        public static final int ROW_SIZE = 32;
        public static final int INDEX_SIZE = 576;
        public static final String MAP_PATH = "src/main/resources/maps/";
    }

    public static class Selection {
        public static final int ROW_SIZE = 6;
    }

    public static class EditorTiles {
        public static final int WIDTH = 47;
        public static final int HEIGHT = 47;
    }

    public enum TileType {
        BASIC_TYPE,
        SOLID_TYPE,
        CLIMBABLE_TYPE,
        DUMMY_TYPE,
        START_TYPE,
        FINISH_TYPE,
        COLLECTABLE_TYPE,
        DEADLY_TYPE
    }
}
