package de.se2projekt.controller;


import de.se2projekt.level.tiles.Tile;
import de.se2projekt.util.Map;
import de.se2projekt.util.TileMap;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class EditorController {
    private static final Logger log = LogManager.getLogger(EditorController.class);

    //Variablen aus der GUI
    public AnchorPane rootPane;
    public HBox rootHBox;
    public GridPane mapEditor;
    public GridPane itemBox;

    private Tile selectedItem;

    private Map instance = new Map();
    private HashMap<Integer, Tile> map = instance.getMap();
    private final Boolean errorMessageIsVisible = false;



    private Text errorMessage;

    //dummyTiles


    @FXML
    public void initialize(){
        displayItems();
        displayEditorPane();
    }

    @FXML
    public void displayItems() {

        // Get instance of TileMap
        TileMap instance = TileMap.getInstance();
        ArrayList<Tile> tiles = instance.getArray();

        for (int i = 0; i < tiles.size(); i++) {
            // Instance all images from tiles above
            final ImageView imv = new ImageView(new Image(EditorController.class.getResource(tiles.get(i).getImagePath()).toString()));

            // Stack them in StackPanes, because its not possible to style an ImageView
            StackPane imageView = new StackPane(imv);
            imageView.setId(String.valueOf(i));
            imageView.setOnMouseClicked(e -> {
                selectedItem = tiles.get(Integer.parseInt(imageView.getId()));
                log.info("Item: " + selectedItem +  " was clicked.");
            });
            imageView.getStyleClass().add("image-view");

            // Scale size
            imv.setFitHeight(46);
            imv.setFitWidth(46);
            itemBox.add(imageView,i%5,i/5);
        }
    }

    @FXML
    public void displayEditorPane() {

        for (int i = 0; i < map.size(); i++) {
            // Instance all images from tiles above
            final ImageView imv = new ImageView(new Image(EditorController.class.getResource(map.get(i).getImagePath()).toString()));

            // Stack them in StackPanes, because its not possible to style an ImageView
            StackPane imageView = new StackPane(imv);
            imageView.setId(String.valueOf(i));
            imageView.getStyleClass().add("image-view");

            // Scale size
            imv.setFitHeight(46);
            imv.setFitWidth(46);

            mapEditor.add(imageView,map.get(i).getX(),map.get(i).getY());
//            System.out.println(imageView.getId() + "  x " + map.get(i).getX() + "  y " + map.get(i).getY());

            imageView.setOnMouseClicked(e -> {
                if (selectedItem != null) {
                    // Store the value or the index
                    int id = Integer.parseInt(imageView.getId());
//                    System.out.println("id: " + id);

                    // Set the new position to the selectedItem
                    selectedItem.setY(map.get(id).getY());
//                    System.out.println("y: " + selectedItem.getY());

                    selectedItem.setX(map.get(id).getX());
//                    System.out.println("x: " + selectedItem.getX());

                    map.replace(Integer.valueOf(imageView.getId()), selectedItem);
                    log.info("Item: " + selectedItem + " was replaced in the map.");

                    displayEditorPane();
                }
            });
        }
    }
}
