package ubx.project.javarts.View;

import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ubx.project.javarts.Controller.*;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.Map;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Size;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MapView extends VBox {
    private GridPane grid;
    private HashMap<Building, ImageView> buildingSprites = new HashMap<>();

    public MapView() {
        ScrollPane root = new ScrollPane();
        grid = new GridPane();
        Size mapSize = Map.getInstance().getSize();
        int width = mapSize.getWidth();
        int height = mapSize.getHeight();

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                ImageView tileImageView = new ImageView(getClass()
                        .getResource("/ubx/project/javarts/mapTiles/tile_0002.png").toExternalForm());
                int finalCol = col;
                int finalRow = row;
                tileImageView.setOnMouseClicked(event -> {
                    BagOfCommands.getInstance().addCommand(new AddBuildingCommand(new Position(finalCol, finalRow)));
                    System.out.println("X=" + finalCol + ", Y=" + finalRow);});
                // Bind the tile size to the MapView's width and height
                tileImageView.fitWidthProperty().bind(Bindings.divide(this.widthProperty(), width));
                tileImageView.fitHeightProperty().bind(Bindings.divide(this.heightProperty(), height));
                tileImageView.setPreserveRatio(true); // Maintain aspect ratio

                // Add the tile to the grid
                grid.add(tileImageView, col, row);
            }
        }

        // Ensure the GridPane resizes with the tiles
        grid.setPrefSize(width * 50, height * 50); // Default preferred size (can be adjusted)
        root.setContent(grid);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.getChildren().add(root);
    }

    public void drawBuildings(Set<Building> buildings) {
        HashMap<Building, ImageView> newBuildings = new HashMap<>();
        for (Building building : buildings) {
            if (buildingSprites.containsKey(building)) {
                newBuildings.put(building, buildingSprites.get(building));
                continue;
            }
            Size mapSize = Map.getInstance().getSize();
            int width = mapSize.getWidth();
            int height = mapSize.getHeight();
            ImageView tileImageView = new ImageView(getClass()
                    .getResource(BuildingSprites.WOODENCABIN.getPath()).toExternalForm());
            tileImageView.fitWidthProperty().bind(Bindings.divide(this.widthProperty(), width));
            tileImageView.fitHeightProperty().bind(Bindings.divide(this.heightProperty(), height));
            tileImageView.setPreserveRatio(true);
            tileImageView.setOnMouseClicked(event -> {
                showBuildingStats(building);});
            grid.add(tileImageView, building.getPostion().getX(), building.getPostion().getY());
            newBuildings.put(building, tileImageView);
        }

        for (Building building : buildingSprites.keySet()) {
            if (!newBuildings.containsKey(building)) {
                grid.getChildren().remove(buildingSprites.get(building));
            }
        }

        buildingSprites = newBuildings;

    }


    public void showBuildingStats(Building building) {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Building Stats");

        Label nameLabel = new Label("Name: " + building.getName());
        Label inhabitantsLabel = new Label("Inhabitants: " + building.getNumberInhabitants() + "/" + building.getMaxInhabitants());
        Label workersLabel = new Label("Workers: " + building.getNumberWorkers() + "/" + building.getMaxWorkers());

        Button removeButton = new Button("Remove Building");
        removeButton.setOnAction(event -> {
            BagOfCommands.getInstance().addCommand(new RemoveBuildingCommand(building));
            System.out.println("Building removed: " + building);
            popup.close();
        });

        Button addInhabitantsButton = new Button("Add Inhabitants");
        addInhabitantsButton.setOnAction(event -> {
            BagOfCommands.getInstance().addCommand(new AddInhabitantsInto(building));
            System.out.println("Inhabitant added to  " + building);
            popup.close();
        });

        Button addWorkersButton = new Button("Add Workers");
        addWorkersButton.setOnAction(event -> {
            BagOfCommands.getInstance().addCommand(new AddWorkerInto(building));
            System.out.println("Worker added to " + building);
            popup.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(nameLabel, inhabitantsLabel, workersLabel, removeButton, addInhabitantsButton,addWorkersButton);

        Scene scene = new Scene(layout, 300, 200);
        popup.setScene(scene);
        popup.showAndWait();
    }

}
