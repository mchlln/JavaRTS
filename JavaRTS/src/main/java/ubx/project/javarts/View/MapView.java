package ubx.project.javarts.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.util.Duration;
import ubx.project.javarts.Controller.*;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingFunction;
import ubx.project.javarts.Model.Building.BuildingType;
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

    public MapView() {
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
        this.setContent(grid);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void drawBuildings(Set<Building> buildings) {
        HashMap<Building, ArrayList<ArrayList<ImageView>>> newBuildings = new HashMap<>();
        for (Building building : buildings) {
            if (buildingSprites.containsKey(building)){
                if (!building.needViewUpdate()){
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
                    if (building.getState() == States.CREATION && building.getType() == BuildingType.WOODENCABIN){
                        tileImageView = new ImageView(getClass()
                                .getResource(imagePath+building.getType().toString().toLowerCase()+"/creation/"+row+"_"+col+".png").toExternalForm());
                    } else {
                        tileImageView = new ImageView(getClass()
                                .getResource(imagePath+building.getType().toString().toLowerCase()+"/"+row+"_"+col+".png").toExternalForm());
                    }

                    tileImageViews.add(tileImageView);
                    tileImageView.fitWidthProperty().bind(Bindings.divide(this.widthProperty(), width));
                    tileImageView.fitHeightProperty().bind(Bindings.divide(this.heightProperty(), height));
                    tileImageView.setPreserveRatio(true);
                    tileImageView.setOnMouseClicked(event -> {
                        showBuildingStats(building);});
                    grid.add(tileImageView, building.getPosition().getX()+col, building.getPosition().getY()+row);
                }
                buildingView.add(tileImageViews);
            }

            newBuildings.put(building, buildingView);
        }

        for (Building building : buildingSprites.keySet()) {
            if (!newBuildings.containsKey(building)) {
                eraseBuilding(building);
            }
        }

        buildingSprites = newBuildings;

    }

    public void eraseBuilding(Building building){
        int buildingHeight = building.getSize().getHeight();
        int buildingWidth = building.getSize().getWidth();
        for (int col = 0; col < buildingWidth; col++) {
            for (int row = 0; row < buildingHeight; row++) {
               grid.getChildren().remove(buildingSprites.get(building).get(col).get(row));
            }
        }
    }

    public void showBuildingStats(Building building) {
        Stage popup = new Stage();
        popup.setMinHeight(300);
        popup.setMinWidth(200);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Stats");

        Label nameLabel = new Label("Name: " + building.getName());
        Label stateLabel = new Label("State: " + building.getState());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(nameLabel, stateLabel);

        Label inhabitantsLabel = new Label();
        Label workersLabel = new Label();

        if(building.getFunctions().contains(BuildingFunction.LIVING)){
            inhabitantsLabel.setText("Inhabitants: " + building.getNumberInhabitants() + "/" + building.getMaxInhabitants());
            Button addInhabitantsButton = new Button("Add Inhabitants");
            addInhabitantsButton.setOnAction(event -> {
                BagOfCommands.getInstance().addCommand(new AddInhabitantInto(building));
                System.out.println("Inhabitant added to  " + building);
            });
            Button removeInhabitantsButton = new Button("Remove Inhabitants");
            removeInhabitantsButton.setOnAction(event -> {
                BagOfCommands.getInstance().addCommand(new RemoveInhabitantFrom(building));
                System.out.println("Inhabitant removed from  " + building);
            });
            layout.getChildren().addAll(inhabitantsLabel, addInhabitantsButton,removeInhabitantsButton);
        }
        if (building.getFunctions().contains(BuildingFunction.WORKING)) {
            workersLabel.setText("Workers: " + building.getNumberWorkers() + "/" + building.getMaxWorkers());
            Button addWorkersButton = new Button("Add Workers");
            addWorkersButton.setOnAction(event -> {
                BagOfCommands.getInstance().addCommand(new AddWorkerInto(building));
                System.out.println("Worker added to " + building);
            });
            Button removeWorkersButton = new Button("Remove Workers");
            removeWorkersButton.setOnAction(event -> {
                BagOfCommands.getInstance().addCommand(new RemoveWorkerFrom(building));
                System.out.println("Worker removed from " + building);
            });
            layout.getChildren().addAll(workersLabel, addWorkersButton, removeWorkersButton);
        }

        Button removeButton = new Button("Remove Building");
        removeButton.setOnAction(event -> {
            BagOfCommands.getInstance().addCommand(new RemoveBuildingCommand(building));
            System.out.println("Building removed: " + building);
            popup.close();
        });

        layout.getChildren().add(removeButton);
        Scene scene = new Scene(layout);
        popup.setScene(scene);

        // Update the labels every second while the popup is open
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (building.getFunctions().contains(BuildingFunction.LIVING)) {
                        inhabitantsLabel.setText("Inhabitants: " + building.getNumberInhabitants() + "/" + building.getMaxInhabitants());
                    }
                    if (building.getFunctions().contains(BuildingFunction.WORKING)) {
                        workersLabel.setText("Workers: " + building.getNumberWorkers() + "/" + building.getMaxWorkers());
                    }
                    if(building.needViewUpdate()){
                        stateLabel.setText("State: " + building.getState());
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.play();

        // Stop the timeline when the popup is closed
        popup.setOnCloseRequest(event -> timeline.stop());
        popup.showAndWait();
    }

}
