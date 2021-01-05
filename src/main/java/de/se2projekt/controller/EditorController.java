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

        for (int i = 0; i < selctionImageArray.length; i++) {
            // Instance all images from tiles above
            final ImageView imv = new ImageView(selctionImageArray[i]);

            // Stack them into StackPanes, because its not possible to style an ImageView
            final StackPane canvas = new StackPane(imv);
            canvas.setId(String.valueOf(i));
            canvas.getStyleClass().add("image-view");

            canvas.setOnMouseClicked(e -> {
                final int id = Integer.parseInt(canvas.getId());
                this.selectedImage = Optional.of(selctionImageArray[id]);
                log.info("Image: " + id +  " was clicked.");
            });

            // Scale size
            imv.setFitHeight(46);
            imv.setFitWidth(46);

            itemBox.add(canvas,i%6,i/6);
        }
    }


    @FXML
    public void displayEditorPane() {

        for (int i = 0; i < editorImageArray.length; i++) {
            // Instance all images from tiles above
            final ImageView imv = new ImageView(editorImageArray[i]);

            // Stack them into StackPanes, because its not possible to style an ImageView
            final StackPane canvas = new StackPane(imv);
            canvas.setId(String.valueOf(i));
            canvas.getStyleClass().add("image-view");

            // Scale size
            imv.setFitHeight(46);
            imv.setFitWidth(46);

            mapEditor.add(canvas,i/18,i%18);

            canvas.setOnMouseClicked(e -> {

                if (this.selectedImage.isPresent()) {
                    // Store the value or the index
                    final int id = Integer.parseInt(canvas.getId());

                    editorImageArray[id] = selectedImage.get();
                    log.info("Item: " + selectedImage + " was replaced in the map.");

                    // Fabian
                    final ImageView imvNew = new ImageView(selectedImage.get());

                    // Scale size
                    imvNew.setFitHeight(46);
                    imvNew.setFitWidth(46);

                    // Remove and add
                    canvas.getChildren().removeAll();
                    canvas.getChildren().add(imvNew);
                }
            });
        }
    }
}
