package de.se2projekt.util;

import de.se2projekt.controller.EditorController;
import javafx.scene.image.Image;

public class ImageHolder {

    // Variables
    public static final ImageHolder INSTANCE = new ImageHolder();

    private static int numberOfImages = 0;

    public final Image IMAGE_4 = loadImageFromPath("/images/tiles/image4.png");
    public final Image IMAGE_5 = loadImageFromPath("/images/tiles/image5.png");
    public final Image IMAGE_6 = loadImageFromPath("/images/tiles/image6.png");
    public final Image IMAGE_16 = loadImageFromPath("/images/tiles/image16.png");
    public final Image IMAGE_17 = loadImageFromPath("/images/tiles/image17.png");
    public final Image DUMMY_IMAGE = loadImageFromPath("/images/tiles/dummy_tile.png");

    private static Image loadImageFromPath(final String imagePath) {
        numberOfImages++;
        final String resource = EditorController.class.getResource(imagePath).toString();
        return new Image(resource);
    }

    public Image[] getImagesAsArray() {
        return new Image[]{
                this.IMAGE_4, this.IMAGE_5, this.IMAGE_6, this.IMAGE_16, this.IMAGE_17, this.DUMMY_IMAGE
        };
    }

    public int getNumberOfImages() {
        return numberOfImages;
    }

    public Image getImage(String imageURL) {
        for (int i = 0; i < getImagesAsArray().length; i++) {
            if (imageURL.equals(getImagesAsArray()[i].getUrl())) {
                return getImagesAsArray()[i];
            }
        }
        return null;
    }
}
