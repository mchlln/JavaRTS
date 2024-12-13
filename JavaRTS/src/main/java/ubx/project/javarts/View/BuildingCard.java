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


    /**
     * Creates a BuildingCard of a certain type.
     * Composed of the caracterictics of the building such as capacity of inhabitants and workers,
     * maximum consumption and production.
     * If you let you mouse on the card (hover) you will see the construction cost.
     * If you click on the card, the card gets selected ({@link BuildingType} saved in field
     * currentlySelected).
     *
     * @param buildingType {@link BuildingType} of the card to create
     */
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
        setBackground(currentlySelected);

        this.getChildren().addAll(root);
        createAndLinkToolTip(b);
    }

    /**
     * @return {@link BuildingType} of the card
     */
    public BuildingType getBuildingType() {
        return buildingType;
    }

    /**
     *  Create a {@link VBox} composed of an image and a text below it
     *
     * @param imagePath {@link String} of the image to load
     * @param labelText {@link String} to be displayed in the label
     * @return {@link VBox} of an image on top of a text
     */
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

    /**
     * Similar method to {@link createSpriteWithLabel} but puts the label on the right of the image.
     * @param imagePath {@link String} of the image to load
     * @param labelText {@link String} to be displayed in the label
     * @return {@link VBox} of an image next to a text
     */
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

    /**
     * Sets the background of the card depending on wheter the user has clicked on it.
     * @param buildingType {@link BuildingType} of the card
     */
    public void setBackground(BuildingType buildingType) {
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

    /**
     * Sets the currently selected type to the type given
     * @param buildingType
     */
    public void setSelected(BuildingType buildingType){
        currentlySelected = buildingType;
        setBackground(buildingType);
    }

    /**
     * Create the tooltip seen on hover of a card.
     * Composed of the construction cost of the given building.
     * @param building {@link Building} to display the cost of
     */
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
