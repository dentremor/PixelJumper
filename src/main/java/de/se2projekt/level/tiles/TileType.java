//package de.se2projekt.level.tiles;
//
//import de.se2projekt.util.ImageHolder;
//import javafx.scene.image.Image;
//
//public enum TileType {
//
//    int BASIC_TYPE = 0;
//    int SOLID_TYPE = 1;
//    int CLIMBABLE_TYPE = 2;
//    int DUMMY_TYPE = 3;
//    int START_TYPE = 4;
//    int FINISH_TYPE = 5;
//    int COLLECTABLE_TYPE = 6;
//
// TODO Danny delete!!!!!
//
//
//
//    BASIC_TYPE(new Image[]{
//    }),
//
//    BASIC_SOLID_TYPE(new Image[]{
//            ImageHolder.INSTANCE.IMAGE_4, ImageHolder.INSTANCE.IMAGE_16, ImageHolder.INSTANCE.IMAGE_17
//    }),
//
//    BASIC_CLIMABLE_TILE(new Image[]{
//            ImageHolder.INSTANCE.IMAGE_5, ImageHolder.INSTANCE.IMAGE_6
//    }),
//
//    DUMMY_TILE(new Image[]{
//            ImageHolder.INSTANCE.DUMMY_IMAGE
//    });
//
//    public static TileType getTyleType(final Image image) {
//
//        for (int i = 0; i < BASIC_TYPE.array.length; i++) {
//            if (image == BASIC_TYPE.array[i]) {
//                return BASIC_TYPE;
//            }
//        }
//
//        for (int i = 0; i < BASIC_SOLID_TYPE.array.length; i++) {
//            if (image == BASIC_SOLID_TYPE.array[i]) {
//                return BASIC_SOLID_TYPE;
//            }
//        }
//
//        for (int i = 0; i < BASIC_CLIMABLE_TILE.array.length; i++) {
//            if (image == BASIC_CLIMABLE_TILE.array[i]) {
//                return BASIC_CLIMABLE_TILE;
//            }
//        }
//
//        for (int i = 0; i < DUMMY_TILE.array.length; i++) {
//            if (image == DUMMY_TILE.array[i]) {
//                return DUMMY_TILE;
//            }
//        }
//        return DUMMY_TILE;
//    }
//
//    private final Image[] array;
//
//    TileType(final Image[] array) {
//        this.array = array;
//    }
//}
