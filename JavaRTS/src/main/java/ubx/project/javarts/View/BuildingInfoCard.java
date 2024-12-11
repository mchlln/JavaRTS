package ubx.project.javarts.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingBuilder;
import ubx.project.javarts.Model.Building.BuildingFunction;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.Map;

public class BuildingInfoCard extends VBox{
    private Building selectedBuilding;
    private final HBox root = new HBox();
    public BuildingInfoCard(Building building, Building selectedBuilding) {
        root.setPadding(new Insets(10));
        root.setSpacing(15);
        root.setAlignment(Pos.CENTER);

        this.selectedBuilding = selectedBuilding;

        VBox house = new VBox();
        ImageView houseView = new ImageView(new Image(getClass().getResource(ImagePath.getBuildingSpritePath(building.getType())).toExternalForm()));
        houseView.setFitWidth(100);
        houseView.setFitHeight(100);

        // Text Label below House sprite
        Label farmLabel = new Label(building.getName());
        farmLabel.setFont(Font.font("Arial", 18));
        farmLabel.setTextFill(Color.DARKCYAN);

        // Bottom section - Left and right icons with text
        VBox bottomSection = new VBox(50);
        bottomSection.setPrefHeight(100);
        bottomSection.setAlignment(Pos.CENTER);

        for (BuildingFunction buildingFunction : building.getFunctions()) {
            switch (buildingFunction) {
                case LIVING:
                    HBox personBox = createSpriteWithTextRight("/ubx/project/javarts/icons/house.png", String.valueOf(building.getInhabitants().size()), String.valueOf(building.getMaxInhabitants()));
                    bottomSection.getChildren().add(personBox);
                    break;
                case WORKING:
                    HBox workerBox = createSpriteWithTextRight("/ubx/project/javarts/icons/worker.png", String.valueOf(building.getWorkers().size()), String.valueOf(building.getMaxWorkers()));
                    bottomSection.getChildren().add(workerBox);
                    break;
            }
        }
        house.getChildren().addAll(houseView, farmLabel);
        root.getChildren().addAll(house, bottomSection);
       // root.setPrefSize(500,250);

        this.getChildren().addAll(root);
        isSelected(building);
        /*
        this.setBackground(new Background(new BackgroundImage(
                new Image(getClass().getResource("/ubx/project/javarts/buildingCards/not_selected_info_background.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT,    // Repeat horizontally
                BackgroundRepeat.NO_REPEAT,    // Repeat vertically
                BackgroundPosition.CENTER,     // Position
                new BackgroundSize(
                        BackgroundSize.DEFAULT.getWidth(),
                        BackgroundSize.DEFAULT.getHeight(),
                        true, true, true, false // Scale to container size
                )
        )));*/
    }

    private HBox createSpriteWithTextRight(String imagePath, String current, String max) {
        ImageView imageView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        Label label = new Label(current + "/" + max);
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.BLACK);

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(imageView, label);
        return hbox;
    }


    public void isSelected(Building b) {
        if (b == this.selectedBuilding) {
            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(getClass().getResource("/ubx/project/javarts/buildingCards/selected_info_background.png").toExternalForm()),
                    BackgroundRepeat.NO_REPEAT,    // Repeat horizontally
                    BackgroundRepeat.NO_REPEAT,    // Repeat vertically
                    BackgroundPosition.CENTER,     // Position
                    new BackgroundSize(
                            BackgroundSize.DEFAULT.getWidth(),
                            BackgroundSize.DEFAULT.getHeight(),
                            true, true, true, false // Scale to container size
                    )
            );

            root.setBackground(new Background(backgroundImage));
        } else {
            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(getClass().getResource("/ubx/project/javarts/buildingCards/not_selected_info_background.png").toExternalForm()),
                    BackgroundRepeat.NO_REPEAT,    // Repeat horizontally
                    BackgroundRepeat.NO_REPEAT,    // Repeat vertically
                    BackgroundPosition.CENTER,     // Position
                    new BackgroundSize(
                            BackgroundSize.DEFAULT.getWidth(),
                            BackgroundSize.DEFAULT.getHeight(),
                            true, true, true, false // Scale to container size
                    )
            );

            root.setBackground(new Background(backgroundImage));
        }
    }

}
