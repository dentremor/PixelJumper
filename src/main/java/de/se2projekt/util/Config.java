package de.se2projekt.util;

/**
 * A class for spacing the magic numbers.
 * @author Daniel Hiller
 * @version 0.1.0
 */

public class Config {

    public static class Map {
        public static final int COLUMN_SIZE = 18;
        public static final int ROW_SIZE = 32;
        public static final int INDEX_SIZE = 576;
    }

    public static class Selection {
        public static final int ROW_SIZE = 6;
    }

    public static class EditorTiles {
        public static final int WIDTH = 46;
        public static final int HEIGHT = 46;
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
