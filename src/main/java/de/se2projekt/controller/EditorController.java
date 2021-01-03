package de.se2projekt.controller;

import com.sun.javafx.fxml.FXMLLoaderHelper;
import de.se2projekt.level.tiles.BasicTile;
import de.se2projekt.level.tiles.Tile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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


    private Boolean errorMessageIsVisible = false;
    private Text errorMessage;

    //dummyTiles
    private List<Tile> dummyTiles = new ArrayList<Tile>();

    BasicTile tree = new BasicTile(1, -1,-1,200,300, "/images/tiles/tree.png");

    @FXML
    public void initialize(){
        // Fill list with items
        for (int i = 0; i < 12; i++){
            dummyTiles.add(tree);
        }
        displayItems();
    }

    @FXML
    public void displayItems() {
        // instance one image from the itemList above
        final Image image = new Image(EditorController.class.getResource(dummyTiles.get(0).getImagePath()).toString());
        final ImageView imv = new ImageView(image);

        // Add the image to the itemBox
        itemBox.getChildren().add(imv);
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
