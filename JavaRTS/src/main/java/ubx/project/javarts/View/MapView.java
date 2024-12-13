package ubx.project.javarts.View;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ubx.project.javarts.Controller.*;
import ubx.project.javarts.Controller.Commands.AddBuildingCommand;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.State.States;
import ubx.project.javarts.Model.Map;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Size;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MapView extends ScrollPane {
    private GridPane grid;
    private HashMap<Building, ArrayList<ArrayList<ImageView>>> buildingSprites = new HashMap<>();
    private final String imagePath = "/ubx/project/javarts/buildingSprites/";

    /**
     * Creates the view of the {@link Map}.
     * Gets the size of the map and creates a {@link GridPane} of the same size.
     * Link an event on the click of a tile : send a command to the
     * {@link BagOfCommands} to add a building on this tile.
     * Contains a {@link ScrollPane} to see the map even if it is larger than our
     * window to avoid having tiles too small.
     */
    public MapView() {
        grid = new GridPane();
        Size mapSize = Map.getInstance().getSize();
        int width = mapSize.getWidth();
        int height = mapSize.getHeight();

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                ImageView tileImageView = new ImageView(getClass()
                        .getResource("/ubx/project/javarts/mapTiles/tile_0002.png").toExternalForm());
                tileImageView.setOnMouseEntered((MouseEvent t) -> {

                    tileImageView.setOpacity(0.5);
                });
                tileImageView.setOnMouseExited((MouseEvent t) -> {
                    tileImageView.setOpacity(1);
                });
                int finalCol = col;
                int finalRow = row;
                tileImageView.setOnMouseClicked(event -> {
                    BagOfCommands.getInstance().addCommand(new AddBuildingCommand(new Position(finalCol, finalRow)));
                    System.out.println("X=" + finalCol + ", Y=" + finalRow);
                });
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
        this.setContent(grid);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    /**
     * Draws a set of buildings on the map.
     *
     * This method handles the following:
     * - If a building already exists on the map and its state has not changed, the
     * existing sprites are reused.
     * - If a building exists on the map but its state has changed, the old sprites
     * are removed, and the building is redrawn with the updated state.
     * - New buildings are drawn and their corresponding sprites are added to a
     * {@link HashMap} for future reference.
     *
     * For each building event handlers are attached to the tiles to allow an operation
     *
     * Buildings that no longer exist in the provided set are erased from the map
     * and removed from the sprite collection.
     *
     * @param buildings the set of {@link Building} objects currently on the map
     */
    public void drawBuildings(Set<Building> buildings) {
        HashMap<Building, ArrayList<ArrayList<ImageView>>> newBuildings = new HashMap<>();
        for (Building building : buildings) {
            if (buildingSprites.containsKey(building)) {
                if (!building.needViewUpdate()) {
                    newBuildings.put(building, buildingSprites.get(building));
                    continue;
                } else {
                    eraseBuilding(building);
                }
            }
            Size mapSize = Map.getInstance().getSize();
            int width = mapSize.getWidth();
            int height = mapSize.getHeight();
            int buildingHeight = building.getSize().getHeight();
            int buildingWidth = building.getSize().getWidth();
            System.out.println("height=" + buildingHeight + ", width=" + buildingWidth);
            ArrayList<ArrayList<ImageView>> buildingView = new ArrayList<>();
            for (int col = 0; col < buildingWidth; col++) {
                ArrayList<ImageView> tileImageViews = new ArrayList<>();
                for (int row = 0; row < buildingHeight; row++) {
                    ImageView tileImageView;
                    System.out.println(building.getState());
                    if (building.getState() != States.RUNNING && row == buildingHeight - 1 && col == 0) {
                        System.out.println(imagePath + building.getType().toString().toLowerCase() + "/" + row + "_"
                                + col + "_" + building.getState().toString().toLowerCase() + ".png");
                        tileImageView = new ImageView(getClass()
                                .getResource(imagePath + building.getType().toString().toLowerCase() + "/" + row + "_"
                                        + col + "_" + building.getState().toString().toLowerCase() + ".png")
                                .toExternalForm());
                    } else {
                        tileImageView = new ImageView(getClass()
                                .getResource(imagePath + building.getType().toString().toLowerCase() + "/" + row + "_"
                                        + col + ".png")
                                .toExternalForm());
                    }

                    tileImageViews.add(tileImageView);
                    tileImageView.fitWidthProperty().bind(Bindings.divide(this.widthProperty(), width));
                    tileImageView.fitHeightProperty().bind(Bindings.divide(this.heightProperty(), height));
                    tileImageView.setPreserveRatio(true);
                    tileImageView.setOnMouseClicked(event -> {
                        new BuildingInfoPopup(building);
                    });
                    grid.add(tileImageView, building.getPosition().getX() + col, building.getPosition().getY() + row);
                }
                buildingView.add(tileImageViews);
            }

            newBuildings.put(building, buildingView);
        }

        // Erase the buildings that were removed from the map
        for (Building building : buildingSprites.keySet()) {
            if (!newBuildings.containsKey(building)) {
                eraseBuilding(building);
            }
        }

        buildingSprites = newBuildings;

    }

    /**
     * Erase the specified building from the map.
     *
     * @param building the {@link Building} instance that we need to remove
     */
    public void eraseBuilding(Building building) {
        int buildingHeight = building.getSize().getHeight();
        int buildingWidth = building.getSize().getWidth();
        for (int col = 0; col < buildingWidth; col++) {
            for (int row = 0; row < buildingHeight; row++) {
                grid.getChildren().remove(buildingSprites.get(building).get(col).get(row));
            }
        }
    }

}
