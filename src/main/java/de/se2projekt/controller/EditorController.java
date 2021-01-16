package de.se2projekt.controller;


import de.se2projekt.highscore.HighscoreManager;
import de.se2projekt.level.map.Map;
import de.se2projekt.level.tiles.Tile;
import de.se2projekt.level.tiles.TileFactory;
import de.se2projekt.util.Config;
import de.se2projekt.util.ImageHolder;
import de.se2projekt.util.MyImage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
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

/**
 * JavaFX Controller for the MapEditor.
 * @author Daniel Hiller
 * @version 0.1.0
 */

public class EditorController {

    // Logger
    private static final Logger log = LogManager.getLogger(EditorController.class);

    // Variables from the .fxml-file
    public AnchorPane rootPane;
    public HBox rootHBox;
    public GridPane mapEditor;
    public GridPane itemBox;
    public Button exportButton;


    // Custom variables
    private Optional<MyImage> selectedImage;
    private final MyImage[] editorImageArray;
    private final MyImage[] selectionImageArray;

    // Constructor for custom variables
    public EditorController() {
        this.selectedImage = Optional.empty();
        this.editorImageArray = new de.se2projekt.level.map.EditorTileMap().getEditorMap();
        this.selectionImageArray = ImageHolder.INSTANCE.getImagesAsArray();
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
        for (int i = 0; i < this.selectionImageArray.length; i++) {

            // Instance all images from tiles above
            final ImageView imv = new ImageView(this.selectionImageArray[i]);

            // Scale size
            imv.setFitHeight(Config.EditorTiles.HEIGHT);
            imv.setFitWidth(Config.EditorTiles.WIDTH);

            // Stack them into StackPanes, because its not possible to style an ImageView
            final StackPane canvas = new StackPane(imv);
            canvas.setId(String.valueOf(i));
            canvas.getStyleClass().add("image-view");

            // ActionListener which set the selectedImage
            canvas.setOnMouseClicked(e -> {
                final int id = Integer.parseInt(canvas.getId());
                this.selectedImage = Optional.of(this.selectionImageArray[id]);
                log.info("Image: " + id + " was clicked.");
            });
            // Add the StackPane to the itemSection GridPane
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

            // Add the StackPane to the mapEditor GridPane
            this.mapEditor.add(canvas, i / Config.Map.COLUMN_SIZE, i % Config.Map.COLUMN_SIZE);

            // Make the StackPanes draggable
            this.mapEditor.setOnDragDetected(e -> {
                if (this.selectedImage.isPresent()) {
                    this.mapEditor.startFullDrag();
                }
            });

            // ActionListener for drag
            canvas.setOnMouseDragEntered(e -> {
                if (this.selectedImage.isPresent()) {
                    e.consume();
                    updateCanvas(canvas);
                }
            });

            // ActionListener for click
            canvas.setOnMousePressed(e -> {
                if (this.selectedImage.isPresent()) {
                    updateCanvas(canvas);
                }
            });
        }
    }

    @FXML
    public void displayExportButton() {

        // Add ActionListener for the export Button
        this.exportButton.setOnMouseClicked(e -> {

            // Call an AlertScreen if the Map isn't Empty
            if (mapIsEmpty()) {
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("This maps does not contain tiles");
                alert.setContentText("Set tiles to save a map!");
                alert.showAndWait();
            } else if (hasOneStartAndFinish()){
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("This maps does not contain all necessary tiles");
                alert.setContentText("Set exact one start and finish!");
                alert.showAndWait();
            }
            else {
                actionForExportButton();
            }
        });
    }


    // Function for displayExportButton() which instance the images to tiles and export them after into a .json-file
    public void actionForExportButton() {
        final TextInputDialog dialog = new TextInputDialog("mapName");
        dialog.setTitle("MapName");
        dialog.setHeaderText("Below you can name the map");
        dialog.setContentText("Please enter a name:");

        final Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            try {

                // Checks if the mapName already exists
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
                    final Map map = new Map(mapArray);
                    final HighscoreManager highscoreManager = new HighscoreManager(name);
                    highscoreManager.writeScoreFile();
                    try {
                        map.exportMap(name);
                    } catch (IOException e) {
                        log.fatal(e.getMessage());
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                log.fatal(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    // Function for displayExportButton() which checks if the map is empty
    public boolean mapIsEmpty() {
        int index = 0;

        for (int i = 0; i < this.editorImageArray.length; i++) {
            if (ImageHolder.INSTANCE.DUMMY_IMAGE != this.editorImageArray[i]) {
                index++;
            }
        }
        if (index == 0) {
            log.info("The Map is empty.");
            return true;
        }
        return false;
    }

    // Function for displayExportButton() which checks if a map with this name already exists
    public boolean mapExists(final String name) throws IOException {
        String[] mapNamesAsArray = Map.MapManager.getMapNamesAsArray();
        for (String mapName: mapNamesAsArray) {
            if (mapName.equals(name)) {
                log.info("The mapName " + name + " already exists");
                return true;
            }
        }
        return false;
    }

    // Function for displayExportButton() which checks if the map contains exact one start and finish-tile
    public boolean hasOneStartAndFinish() {
        int start = 0;
        int finish = 0;

        for (int i = 0; i < this.editorImageArray.length; i++) {
            if (ImageHolder.INSTANCE.START_IMAGE == this.editorImageArray[i]) {
                start++;
            } else if (ImageHolder.INSTANCE.FINISH_IMAGE == this.editorImageArray[i]){
                finish++;
            }
        }
        return start != 1 && finish != 1;
    }

    // Function for displayEditorPane() which updates the StackPane
    public void updateCanvas(StackPane canvas) {
        // Store the value or the index
        final int id = Integer.parseInt(canvas.getId());

        this.editorImageArray[id] = this.selectedImage.get();
        log.info("Item: " + id + " was replaced by" + this.selectedImage.toString() + " in the map.");

        final ImageView imvNew = new ImageView(this.selectedImage.get());

        // Scale size
        imvNew.setFitHeight(46);
        imvNew.setFitWidth(46);

        // Remove and add
        canvas.getChildren().remove(0);
        canvas.getChildren().add(imvNew);
    }
}
