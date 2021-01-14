package de.se2projekt.controller;


import de.se2projekt.level.map.MapManager;
import de.se2projekt.level.tiles.Tile;
import de.se2projekt.level.tiles.TileFactory;
import de.se2projekt.util.Config;
import de.se2projekt.util.ImageHolder;
import de.se2projekt.util.MyImage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class EditorController {
    private static final Logger log = LogManager.getLogger(EditorController.class);

    // Variablen aus der GUI
    public AnchorPane rootPane;
    public HBox rootHBox;
    public GridPane mapEditor;
    public GridPane itemBox;
    public Button exportButton;
    // TODO Danny remove unused code


    // Custom variables
    private Optional<MyImage> selectedImage;
    private final MyImage[] editorImageArray;
    private final MyImage[] selctionImageArray;

    // Constructor for custom variables
    public EditorController() {
        this.selectedImage = Optional.empty();
        this.editorImageArray = new de.se2projekt.level.map.EditorTileMap().getEditorMap();
        this.selctionImageArray = ImageHolder.INSTANCE.getImagesAsArray();
    }

    @FXML
    public void initialize() {
        this.displayItems();
        this.displayEditorPane();
        this.displayExportButton();
    }


    @FXML
    public void displayItems() {

        // Get instance of EditorTileMap

        for (int i = 0; i < this.selctionImageArray.length; i++) {
            // Instance all images from tiles above
            final ImageView imv = new ImageView(this.selctionImageArray[i]);

            // Scale size
            imv.setFitHeight(Config.EditorTiles.HEIGHT);
            imv.setFitWidth(Config.EditorTiles.WIDTH);

            // Stack them into StackPanes, because its not possible to style an ImageView
            final StackPane canvas = new StackPane(imv);
            canvas.setId(String.valueOf(i));
            canvas.getStyleClass().add("image-view");

            canvas.setOnMouseClicked(e -> {
                final int id = Integer.parseInt(canvas.getId());
                this.selectedImage = Optional.of(this.selctionImageArray[id]);
                log.info("Image: " + id + " was clicked.");
            });

            this.itemBox.add(canvas, i % Config.Selection.ROW_SIZE, i / Config.Selection.ROW_SIZE);
        }
    }


    @FXML
    public void displayEditorPane() {

        for (int i = 0; i < this.editorImageArray.length; i++) {
            // Instance all images
            final ImageView imv = new ImageView(this.editorImageArray[i]);

            // Scale size
            imv.setFitHeight(Config.EditorTiles.HEIGHT);
            imv.setFitWidth(Config.EditorTiles.WIDTH);

            // Stack them into StackPanes, because its not possible to style an ImageView
            final StackPane canvas = new StackPane(imv);
            canvas.setId(String.valueOf(i));
            canvas.getStyleClass().add("image-view");

            this.mapEditor.add(canvas, i / Config.Map.COLUMN_SIZE, i % Config.Map.COLUMN_SIZE);


            this.mapEditor.setOnDragDetected(e -> {
                if (this.selectedImage.isPresent()) {
                    this.mapEditor.startFullDrag();
                }
            });

            canvas.setOnMouseDragEntered(e -> {
                if (this.selectedImage.isPresent()) {
                    e.consume();
                    updateCanvas(canvas);
                }
            });

            canvas.setOnMousePressed(e -> {
                if (this.selectedImage.isPresent()) {
                    updateCanvas(canvas);
                }
            });
        }
    }

    @FXML
    public void displayExportButton() {
        this.exportButton.setOnMouseClicked(e -> {
            if (mapIsEmpty()) {
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("This maps does not contain tiles");
                alert.setContentText("Set tiles to save a map!");
                alert.showAndWait();
            } else {
                actionForExportButton();
            }
        });
    }


    // Functions for displayExportButton()
    public void actionForExportButton() {
        final TextInputDialog dialog = new TextInputDialog("mapName");
        dialog.setTitle("MapName");
        dialog.setHeaderText("Below you can name the map");
        dialog.setContentText("Please enter a name:");

        final Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            if (mapExists(name)) {
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("This name is not available for selection");
                alert.setContentText("The name " + name + " is already taken!");
                alert.showAndWait();
                actionForExportButton();
            } else {
                final ArrayList<Tile> mapArray = new ArrayList<>();
                int index = 0;
                for (int i = 0; i < this.editorImageArray.length; i++) {
                    if (ImageHolder.INSTANCE.DUMMY_IMAGE != this.editorImageArray[i]) {
                        final Tile tile = new TileFactory().makeTile(i / Config.Map.COLUMN_SIZE, i % Config.Map.COLUMN_SIZE, this.editorImageArray[i]);
                        mapArray.add(tile);
                        index++;
                    }
                }
                final MapManager mapManager = new MapManager(mapArray);
                try {
                    mapManager.exportMap(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean mapIsEmpty() {
        int index = 0;

        for (int i = 0; i < this.editorImageArray.length; i++) {
            if (ImageHolder.INSTANCE.DUMMY_IMAGE != this.editorImageArray[i]) {
                index++;
            }
        }
        if (index == 0) {
            return true;
        }
        return false;
    }

    // TODO Danny *implement* check map which already exists
    public boolean mapExists(final String name) {
        return false;
    }

    //Functions for displayEditorPane()
    public void updateCanvas(StackPane canvas) {
        // Store the value or the index
        final int id = Integer.parseInt(canvas.getId());

        this.editorImageArray[id] = this.selectedImage.get();
        log.info("Item: " + id + " was replaced by" + this.selectedImage + " in the map.");

        final ImageView imvNew = new ImageView(this.selectedImage.get());

        // Scale size
        imvNew.setFitHeight(46);
        imvNew.setFitWidth(46);

        // Remove and add
        canvas.getChildren().remove(0);
        canvas.getChildren().add(imvNew);
    }
}
