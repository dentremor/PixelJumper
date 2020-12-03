package de.se2projekt.util;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;

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
