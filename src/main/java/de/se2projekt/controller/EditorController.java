package de.se2projekt.controller;

import com.sun.javafx.fxml.FXMLLoaderHelper;
import de.se2projekt.level.tiles.BasicClimbableTile;
import de.se2projekt.level.tiles.BasicSolidTile;
import de.se2projekt.level.tiles.BasicTile;
import de.se2projekt.level.tiles.Tile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class EditorController {
    private static final Logger log = LogManager.getLogger(EditorController.class);

    //Variablen aus der GUI
    public AnchorPane rootPane;
    public VBox rootVBox;
    public AnchorPane rootEditorPane;
    public Button exportButtonEditor;
    public Button scrollLeftButtonEditor;
    public Button scrollRightButtonEditor;
    public AnchorPane rootItemPane;
    public Button scrollLeftButtonItems;
    public HBox itemBox;
    public Button scrollRightButtonItems;


    private final Boolean errorMessageIsVisible = false;
    private Text errorMessage;

    //dummyTiles
    private final List<Tile> items = new ArrayList<Tile>();

    BasicTile tree = new BasicTile(1, -1,-1,200,300, "/images/tiles/tree.png");
    BasicSolidTile tileOne = new BasicSolidTile(2,-1,-1,300,300,"/images/tiles/tile_1.png");
    BasicSolidTile tileTwo = new BasicSolidTile(3, -1,-1,500,300,"/images/tiles/tile_2.png");
    BasicSolidTile tileThree = new BasicSolidTile(4,-1,-1,500,300,"/images/tiles/tile_3.png");
    BasicClimbableTile liana = new BasicClimbableTile(5,-1,-1,100,200,"/images/tiles/liana_1.png");

    @FXML
    public void initialize(){
        // Fill list with items
        items.add(tree);
        items.add(tileOne);
        items.add(tileTwo);
        items.add(tileThree);
        items.add(liana);

        displayItems();
    }

    @FXML
    public void displayItems() {

        ListView<ImageView> imageList = new ListView<ImageView>();

        for (Tile tile : items) {
            // Instance all images from the dummyList above
            final Image image = new Image(EditorController.class.getResource(tile.getImagePath()).toString());
            final ImageView imv = new ImageView(image);

            // Add the Images from above into a list
            imageList.getItems().add(imv);
        }

        // Add the list with images to the itemBox
        itemBox.getChildren().add(imageList);
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
