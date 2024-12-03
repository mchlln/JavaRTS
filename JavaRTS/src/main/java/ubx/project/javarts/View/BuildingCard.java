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

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class BuildingCard extends VBox {
    private VBox container;
    private Menu menu;
    private MenuBar menuBar;
    private MenuItem newGameItem;
    private MenuItem loadGameItem;
    private MenuItem saveGameItem;
    private MenuItem exitItem;


    public BuildingCard() {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        // Top section - Sprites with labels
        HBox topSection = new HBox(20);
        topSection.setAlignment(Pos.CENTER);

        // Create person icon
        VBox personBox = createSpriteWithLabel("/ubx/project/javarts/house.png", "3");

        // Create lock icon
        VBox lockBox = createSpriteWithLabel("/ubx/project/javarts/house.png", "4");

        topSection.getChildren().addAll(personBox, lockBox);

        // Center - House sprite
        ImageView houseView = new ImageView(new Image(getClass().getResource("/ubx/project/javarts/house.png").toExternalForm()));
        houseView.setFitWidth(100);
        houseView.setFitHeight(100);

        // Text Label below House sprite
        Label farmLabel = new Label("FARM");
        farmLabel.setFont(Font.font("Arial", 18));
        farmLabel.setTextFill(Color.DARKCYAN);

        // Bottom section - Left and right icons with text
        HBox bottomSection = new HBox(50);
        bottomSection.setAlignment(Pos.CENTER);

        HBox leftBox = createSpriteWithTextRight("/ubx/project/javarts/house.png", "50");
        HBox rightBox = createSpriteWithTextRight("/ubx/project/javarts/house.png", "60");

        bottomSection.getChildren().addAll(leftBox, rightBox);

        root.getChildren().addAll(topSection, houseView, farmLabel, bottomSection);
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
