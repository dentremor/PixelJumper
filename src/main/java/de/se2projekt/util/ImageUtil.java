package de.se2projekt.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    /*
     * Ladet ein Bild mit dem angegebenen Pfad
     */
    public static BufferedImage loadImage(String path){
        File file = new File(path);
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            new Throwable("Failed to load Image");
            return null;
        }
    }

}
