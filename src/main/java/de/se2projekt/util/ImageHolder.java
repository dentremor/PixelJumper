package de.se2projekt.util;

import de.se2projekt.controller.EditorController;
import javafx.scene.image.Image;

public class ImageHolder {

    // Variables
    public static final ImageHolder INSTANCE = new ImageHolder();

    private static int numberOfImages = 0;

    public final MyImage IMAGE_4 = new MyImage("/images/tiles/image4.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_5 = new MyImage("/images/tiles/image5.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_6 = new MyImage("/images/tiles/image6.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_16 = new MyImage("/images/tiles/image16.png", Config.TileType.BASIC_TYPE);
    public final MyImage IMAGE_17 = new MyImage("/images/tiles/image17.png", Config.TileType.BASIC_TYPE);
    public final MyImage DUMMY_IMAGE = new MyImage("/images/tiles/dummy_tile.png", Config.TileType.DUMMY_TYPE);
    public final MyImage PLAYER_LEFT_IMAGE = new MyImage("/images/player_left.png", Config.TileType.BASIC_TYPE);
    public final MyImage PLAYER_RIGHT_IMAGE = new MyImage("/images/player_right.png", Config.TileType.BASIC_TYPE);

    private static Image loadImageFromPath(final String imagePath) {
        numberOfImages++;
        final String resource = EditorController.class.getResource(imagePath).toString();
        return new Image(resource);
    }

    public MyImage[] getImagesAsArray() {
        return new MyImage[]{
                this.IMAGE_4, this.IMAGE_5, this.IMAGE_6, this.IMAGE_16, this.IMAGE_17, this.DUMMY_IMAGE
        };
    }

    public int getNumberOfImages() {
        return numberOfImages;
    }

    public String toString(Image image) {
        return image.getUrl();
    }

    public MyImage getImage(String imageURL) {
        for (int i = 0; i < getImagesAsArray().length; i++) {
            if (imageURL.equals(getImagesAsArray()[i].getUrl())) {
                return getImagesAsArray()[i];
            }
        }
        return null;
    }
}
