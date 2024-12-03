package ubx.project.javarts.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingBuilder;
import ubx.project.javarts.Model.Building.BuildingFunction;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.Position;

public class BuildingCard extends VBox {
    private VBox container;
    private Menu menu;
    private MenuBar menuBar;
    private MenuItem newGameItem;
    private MenuItem loadGameItem;
    private MenuItem saveGameItem;
    private MenuItem exitItem;


    public BuildingCard(BuildingType buildingType) {
        Building b = new BuildingBuilder().build(buildingType, new Position(0,0));

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        // Top section - Sprites with labels
        HBox topSection = new HBox(20);
        topSection.setAlignment(Pos.CENTER);

        ImageView houseView = new ImageView(new Image(getClass().getResource("/ubx/project/javarts/house.png").toExternalForm()));
        houseView.setFitWidth(100);
        houseView.setFitHeight(100);

        // Text Label below House sprite
        Label farmLabel = new Label(b.getName());
        farmLabel.setFont(Font.font("Arial", 18));
        farmLabel.setTextFill(Color.DARKCYAN);

        // Bottom section - Left and right icons with text
        HBox bottomSection = new HBox(50);
        bottomSection.setAlignment(Pos.CENTER);

        for (BuildingFunction buildingFunction : b.getFunctions()) {
            System.out.println(buildingFunction);
            switch (buildingFunction) {
                case LIVING :
                    VBox personBox = createSpriteWithLabel("/ubx/project/javarts/icons/structure_house.png", String.valueOf(b.getMaxInhabitants()));
                    topSection.getChildren().add(personBox);
                    break;
                case WORKING :
                    VBox workerBox = createSpriteWithLabel("/ubx/project/javarts/house.png", "WOR");
                    topSection.getChildren().add(workerBox);
                    break;
                case CONSUMING :
                    HBox consumingBox = createSpriteWithTextRight("/ubx/project/javarts/house.png", "CON");
                    bottomSection.getChildren().add(consumingBox);
                    break;
                case PRODUCING :
                    HBox producingBox = createSpriteWithTextRight("/ubx/project/javarts/house.png", "PRO");
                    bottomSection.getChildren().add(producingBox);
                    break;
            }
        }

        root.getChildren().addAll(topSection, houseView, farmLabel, bottomSection);
        root.setStyle("-fx-background-color: #242b8b;");
        this.getChildren().addAll(root);
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


}
