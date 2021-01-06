package de.se2projekt.controller;


import de.se2projekt.util.ImageHolder;
import de.se2projekt.util.TileMap;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class EditorController {
    private static final Logger log = LogManager.getLogger(EditorController.class);

    // Variablen aus der GUI
    public AnchorPane rootPane;
    public HBox rootHBox;
    public GridPane mapEditor;
    public GridPane itemBox;
    // TODO Danny remove unused code
    // private final Boolean errorMessageIsVisible = Boolean.FALSE;
    // private Text errorMessage;


    // Custom variables
    private Optional<Image> selectedImage;
    private final Image[] editorImageArray;
    private final Image[] selctionImageArray;

    // Constructor for custom variables
    public EditorController() {
        this.selectedImage = Optional.empty();
        this.editorImageArray = new TileMap().getEditorMap();
        this.selctionImageArray = ImageHolder.INSTANCE.getImagesAsArray();
    }

    @FXML
    public void initialize() {
        this.displayItems();
        this.displayEditorPane();
    }


    @FXML
    public void displayItems() {

        // Get instance of TileMap

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

            canvas.setOnMouseClicked(e -> {

                if (this.selectedImage.isPresent()) {
                    // Store the value or the index
                    final int id = Integer.parseInt(canvas.getId());

                    this.editorImageArray[id] = this.selectedImage.get();
                    log.info("Item: " + this.selectedImage + " was replaced in the map.");

                    final ImageView imvNew = new ImageView(this.selectedImage.get());

                    // Scale size
                    imvNew.setFitHeight(46);
                    imvNew.setFitWidth(46);

                    // Remove and add
                    canvas.getChildren().remove(0);
                    canvas.getChildren().add(imvNew);
                }
            });
        }
    }
}
