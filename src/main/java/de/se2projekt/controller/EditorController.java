package de.se2projekt.controller;

import com.sun.javafx.geom.Matrix3f;
import de.se2projekt.level.tiles.BasicClimbableTile;
import de.se2projekt.level.tiles.BasicSolidTile;
import de.se2projekt.level.tiles.BasicTile;
import de.se2projekt.level.tiles.Tile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    public GridPane rootEditorPane;
    public GridPane itemBox;


    private final Boolean errorMessageIsVisible = false;



    private Text errorMessage;

    //dummyTiles
    private final List<Tile> tiles = new ArrayList<>();

    //BasicTile tree = new BasicTile(1, -1,-1, "/images/tiles/tree.png");
    BasicSolidTile tileOne = new BasicSolidTile(1,-1,-1,"/images/tiles/image16.png");
    BasicSolidTile tileTwo = new BasicSolidTile(2, -1,-1,"/images/tiles/image17.png");
    BasicSolidTile tileThree = new BasicSolidTile(3,-1,-1,"/images/tiles/image4.png");
    BasicClimbableTile lianaOne = new BasicClimbableTile(4,-1,-1,"/images/tiles/image5.png");
    BasicClimbableTile lianaTwo = new BasicClimbableTile(5,-1,-1,"/images/tiles/image6.png");

    @FXML
    public void initialize(){
        // Fill list with items
        tiles.add(tileOne);
        tiles.add(tileTwo);
        tiles.add(tileThree);
        tiles.add(lianaOne);
        tiles.add(lianaTwo);

        displayItems();
        displayEditorPane();
    }

    @FXML
    public void displayItems() {


        for (int i = 0; i < tiles.size(); i++) {
            // Instance all images from tiles above
            final ImageView imv = new ImageView(new Image(EditorController.class.getResource(tiles.get(i).getImagePath()).toString()));
            StackPane imageView = new StackPane(imv);
            imageView.getStyleClass().add("image-view");

            // Scale size
            imv.setFitHeight(46);
            imv.setFitWidth(46);
            itemBox.add(imageView,i,i/5);

        }
//        // Instance ListView
//        ListView<ImageView> imageList = new ListView<ImageView>();
//        imageList.setOrientation(Orientation.VERTICAL);
//
//        int imageListWidth = 0;
//
//        for (Tile tile : items) {
//            // Instance all images from the dummyList above
//            final ImageView imv = new ImageView(new Image(EditorController.class.getResource(tile.getImagePath()).toString()));
//
//            // Add the width from each item to get the whole width
//            imageListWidth += tile.getWidth() * 0.5;
//
//            // Scale size
//            imv.setFitHeight(tile.getHeight() * 0.5);
//            imv.setFitWidth(tile.getWidth() * 0.5);
//
//            // Add the Images from above into a list
//            imageList.getItems().add(imv);
//        }
//        // Set the size of the whole list
//        imageList.setPrefHeight(235);
//        imageList.setPrefWidth(imageListWidth + 100);
//
//        // Add the list with images to the itemBox
//        itemBox.getChildren().add(imageList);
    }

    @FXML
    public void displayEditorPane() {

    }

    @FXML
    public Button scrollLefButtonItems;

    public void handleScrollLefButtonItems(){
        //todo
    }

    public void handleScrollRightButtonItems(MouseEvent mouseEvent) {
    }

    public void handleScrollLefButtonEditor(MouseEvent mouseEvent) {
        //todo
    }

    public void handleScrollRightButtonEditor(MouseEvent mouseEvent) {
        //todo
    }

    public void handleExportButtonEditor(MouseEvent mouseEvent) {
        //todo
    }
}
