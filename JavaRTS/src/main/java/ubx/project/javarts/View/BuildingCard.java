package ubx.project.javarts.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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

public class BuildingCard extends VBox {
    private VBox container;
    private Menu menu;
    private MenuBar menuBar;
    private MenuItem newGameItem;
    private MenuItem loadGameItem;
    private MenuItem saveGameItem;
    private MenuItem exitItem;
    private BuildingType currentlySelected;
    private final VBox root = new VBox();
    private final BuildingType buildingType;


    public BuildingCard(BuildingType buildingType) {
        Building b = new BuildingBuilder().build(buildingType, new Position(0,0));
        this.buildingType = buildingType;

        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        // Top section - Sprites with labels
        HBox topSection = new HBox(20);
        topSection.setAlignment(Pos.CENTER);

        ImageView houseView = new ImageView(new Image(getClass().getResource(ImagePath.getBuildingSpritePath(buildingType)).toExternalForm()));
        houseView.setFitWidth(100);
        houseView.setFitHeight(100);

        // Text Label below House sprite
        Label farmLabel = new Label(b.getName());
        farmLabel.setFont(Font.font("Arial", 18));
        farmLabel.setTextFill(Color.DARKCYAN);

        // Bottom section - Left and right icons with text
        HBox bottomSection = new HBox(50);
        bottomSection.setPrefHeight(100);
        bottomSection.setAlignment(Pos.CENTER);

        for (BuildingFunction buildingFunction : b.getFunctions()) {
            switch (buildingFunction) {
                case LIVING :
                    VBox personBox = createSpriteWithLabel("/ubx/project/javarts/icons/house.png", String.valueOf(b.getMaxInhabitants()));
                    topSection.getChildren().add(personBox);
                    break;
                case WORKING :
                    VBox workerBox = createSpriteWithLabel("/ubx/project/javarts/icons/worker.png", String.valueOf(b.getMaxWorkers()));
                    topSection.getChildren().add(workerBox);
                    break;
                case CONSUMING :
                    Map<ResourceType,Integer> resCons = b.getDailyConsumption();
                    VBox consumingBox = new VBox();
                    consumingBox.getChildren().add(new Label("CONS"));
                    for(ResourceType resourceType : resCons.keySet()) {

                        HBox resBox = createSpriteWithTextRight(ImagePath.getResourceLogoPath(resourceType), String.valueOf(resCons.get(resourceType)));

                        consumingBox.getChildren().addAll(resBox);
                    }

                    bottomSection.getChildren().add(consumingBox);
                    break;
                case PRODUCING :
                    Map<ResourceType,Integer> resProd = b.getDailyProduction();
                    VBox producingBox = new VBox();
                    producingBox.getChildren().add(new Label("PROD"));
                    for(ResourceType resourceType : resProd.keySet()) {

                        HBox resBox = createSpriteWithTextRight(ImagePath.getResourceLogoPath(resourceType), String.valueOf(resProd.get(resourceType)));

                        producingBox.getChildren().addAll(resBox);
                    }

                    bottomSection.getChildren().add(producingBox);
                    break;
            }
        }

        root.getChildren().addAll(topSection, houseView, farmLabel, bottomSection);
        root.setPrefSize(250,400);
        isSelected(currentlySelected);

        this.getChildren().addAll(root);
        createAndLinkToolTip(b);
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    private VBox createSpriteWithLabel(String imagePath, String labelText){
        ImageView imageView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.BLACK);

        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(imageView, label);
        return vbox;
    }

    private HBox createSpriteWithTextRight(String imagePath, String labelText) {
        ImageView imageView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.BLACK);

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(imageView, label);
        return hbox;
    }

    public void isSelected(BuildingType buildingType) {
        if (buildingType == this.buildingType) {
            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(getClass().getResource("/ubx/project/javarts/buildingCards/selected_background.png").toExternalForm()),
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
                    new Image(getClass().getResource("/ubx/project/javarts/buildingCards/not_selected_background.png").toExternalForm()),
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

    public void setSelected(BuildingType buildingType){
        currentlySelected = buildingType;
        isSelected(buildingType);
    }

 public void createAndLinkToolTip(Building building) {
     VBox tooltipContent = new VBox(5);
     Label lbl = new Label("Building cost");
     lbl.setFont(Font.font("Arial", 18));
     tooltipContent.getChildren().add(lbl);
     Map<ResourceType,Integer> cost = building.getCost();
     for(ResourceType resourceType : cost.keySet()) {
         HBox resourceDisplay = new HBox();

         ImageView logo = new ImageView(new Image(getClass().getResource(ImagePath.getResourceLogoPath(resourceType)).toExternalForm()));
         logo.setFitWidth(30); // Set logo width
         logo.setFitHeight(30); // Set logo height


         Label quantityLabel = new Label(String.valueOf(cost.get(resourceType)));
         quantityLabel.setFont(Font.font("Arial", 18));
         resourceDisplay.getChildren().addAll(logo, quantityLabel);
         tooltipContent.getChildren().add(resourceDisplay);
     }

     Tooltip tooltip = new Tooltip();
     tooltip.setGraphic(tooltipContent);

     Tooltip.install(root, tooltip);
 }

}
