package ubx.project.javarts.View;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import ubx.project.javarts.Controller.AddBuildingCommand;
import ubx.project.javarts.Controller.BagOfCommands;
import ubx.project.javarts.Controller.Controller;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.Map;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Size;

import java.util.ArrayList;
import java.util.Set;

public class MapView extends VBox {
    private GridPane grid = new GridPane();

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
        for (Building building : buildings) {
            Size mapSize = Map.getInstance().getSize();
            int width = mapSize.getWidth();
            int height = mapSize.getHeight();
            ImageView tileImageView = new ImageView(getClass()
                    .getResource(BuildingSprites.WOODENCABIN.getPath()).toExternalForm());
            tileImageView.fitWidthProperty().bind(Bindings.divide(this.widthProperty(), width));
            tileImageView.fitHeightProperty().bind(Bindings.divide(this.heightProperty(), height));
            tileImageView.setPreserveRatio(true);
            grid.add(tileImageView, building.getPostion().getX(), building.getPostion().getY());
        }


    }
}
