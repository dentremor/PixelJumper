package de.se2projekt.controller;


import de.se2projekt.level.tiles.Tile;
import de.se2projekt.util.TileMap;
import de.se2projekt.util.TileSelectionMap;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
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

    // custom
    private Optional<Tile> selectedTile;
    private final HashMap<Integer, Tile> editorTileMap;

    // dummyTiles

    public EditorController() {
        this.selectedTile = Optional.empty();
        this.editorTileMap = new TileMap().getMap();
    }

    @FXML
    public void initialize() {
        this.displayItems();
        this.displayEditorPane();
    }

    @FXML
    public void displayItems() {

        for (int i = 0; i < TileSelectionMap.INSTANCE.size(); i++) {
            // Instance all images from tiles above
            final ImageView imv;
            {
                final Tile tileSelectionItem = TileSelectionMap.INSTANCE.get(i);
                final Image image = tileSelectionItem.getImage();
                imv = new ImageView(image);
            }

            // Stack them in StackPanes, because its not possible to style an ImageView
            final StackPane stackPane = new StackPane(imv);
            stackPane.setId(String.valueOf(i));
            stackPane.setOnMouseClicked(e -> {
                final int id = Integer.parseInt(stackPane.getId());
                this.selectedTile = Optional.of(TileSelectionMap.INSTANCE.get(id));
                log.info("Item: " + this.selectedTile + " was clicked.");
            });
            stackPane.getStyleClass().add("image-view");

            // Scale size
            imv.setFitHeight(46);
            imv.setFitWidth(46);
            this.itemBox.add(stackPane, i % 5, i / 5);
        }
    }

    @FXML
    public void displayEditorPane() {

        for (int i = 0; i < this.editorTileMap.size(); i++) {
            final Tile editorTile = this.editorTileMap.get(i);

            // Instance all images from tiles above
            final ImageView imv = new ImageView(editorTile.getImage());

            // Stack them in StackPanes, because its not possible to style an ImageView
            final StackPane stackPane = new StackPane(imv);
            stackPane.setId(String.valueOf(i));
            stackPane.getStyleClass().add("image-view");

            // Scale size
            imv.setFitHeight(46);
            imv.setFitWidth(46);

            this.mapEditor.add(stackPane, editorTile.getX(), editorTile.getY());
            // TODO Danny remove (used for debugging)
//            System.out.println(imageView.getId() + "  x " + tile.getX() + "  y " + tile.getY());

            stackPane.setOnMouseClicked(e -> {
                if (this.selectedTile.isPresent()) {
                    // Store the value or the index
                    final Tile tileWithId;
                    {
                        final int id = Integer.parseInt(stackPane.getId());
                        tileWithId = this.editorTileMap.get(id);
                    }
                    // TODO Danny remove (used for debugging)
//                    System.out.println("id: " + id);

                    // Set the new position to the selectedItem
                    this.selectedTile.get().setY(tileWithId.getY());
                    // TODO Danny remove (used for debugging)
//                    System.out.println("y: " + selectedItem.getY());

                    this.selectedTile.get().setX(tileWithId.getX());
                    // TODO Danny remove (used for debugging)
//                    System.out.println("x: " + selectedItem.getX());

                    this.editorTileMap.replace(Integer.valueOf(stackPane.getId()), this.selectedTile.get());
                    log.info("Item: " + this.selectedTile + " was replaced in the map.");

                    this.displayEditorPane();
                }
            });
        }
    }
}
