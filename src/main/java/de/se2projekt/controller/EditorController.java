package de.se2projekt.controller;


import com.sun.javafx.geom.Matrix3f;
import de.se2projekt.level.tiles.BasicClimbableTile;
import de.se2projekt.level.tiles.BasicSolidTile;
import de.se2projekt.level.tiles.BasicTile;
import de.se2projekt.level.tiles.Tile;
import de.se2projekt.util.TileMap;
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
            imageView.getStyleClass().add("image-view");

            // Scale size
            imv.setFitHeight(46);
            imv.setFitWidth(46);
            itemBox.add(imageView,i,i/5);
        }
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
