package ubx.project.javarts.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingBuilder;
import ubx.project.javarts.Model.Building.BuildingFunction;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.Map;

public class BuildingInfoCard extends VBox{
    private final VBox root = new VBox();
    public BuildingInfoCard(Building building) {
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        // Top section - Sprites with labels
        HBox topSection = new HBox(20);
        topSection.setAlignment(Pos.CENTER);

        ImageView houseView = new ImageView(new Image(getClass().getResource(BuildingSprites.WOODENCABIN.getPath()).toExternalForm()));
        houseView.setFitWidth(100);
        houseView.setFitHeight(100);

        // Text Label below House sprite
        Label farmLabel = new Label(building.getName());
        farmLabel.setFont(Font.font("Arial", 18));
        farmLabel.setTextFill(Color.DARKCYAN);

        // Bottom section - Left and right icons with text
        HBox bottomSection = new HBox(50);
        bottomSection.setPrefHeight(100);
        bottomSection.setAlignment(Pos.CENTER);

        for (BuildingFunction buildingFunction : building.getFunctions()) {
            System.out.println(buildingFunction);
            switch (buildingFunction) {
                case LIVING:
                    HBox personBox = createSpriteWithTextRight("/ubx/project/javarts/icons/structure_house.png", String.valueOf(building.getInhabitants().size()), String.valueOf(building.getMaxInhabitants()));
                    topSection.getChildren().add(personBox);
                    break;
                case WORKING:
                    HBox workerBox = createSpriteWithTextRight("/ubx/project/javarts/icons/character.png", String.valueOf(building.getWorkers().size()), String.valueOf(building.getMaxWorkers()));
                    topSection.getChildren().add(workerBox);
                    break;
            }
        }

        root.getChildren().addAll(topSection, houseView, farmLabel, bottomSection);
        root.setPrefSize(250,400);

        this.getChildren().addAll(root);
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
}
