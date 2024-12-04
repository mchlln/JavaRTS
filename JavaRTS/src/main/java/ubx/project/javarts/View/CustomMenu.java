package ubx.project.javarts.View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ubx.project.javarts.Model.Resource.ResourceDecorator;
import ubx.project.javarts.Model.Resource.ResourceManager;
import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class CustomMenu extends VBox {
    private HBox container;
    private HashMap<ResourceType, Label> resourcesLabels = new HashMap<>();


    public CustomMenu() {
        Menu menu = new Menu("Game");
        Label label = new Label("label");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);
        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem loadGameItem = new MenuItem("Load Game");
        MenuItem saveGameItem = new MenuItem("Save Game");
        MenuItem exitItem = new MenuItem("Exit");
        menuBar.setPrefHeight(40);

        HBox resourceBox = new HBox();
        Map<ResourceType, ResourceDecorator> resources = ResourceManager.getResources();
        for (ResourceType resource : resources.keySet()) {
            HBox resourceDisplay = new HBox();

            ImageView logo = new ImageView(new Image(getClass().getResource(ImagePath.getResourceLogoPath(resource)).toExternalForm()));
            logo.setFitWidth(24); // Set logo width
            logo.setFitHeight(24); // Set logo height


            Label quantityLabel = new Label(String.valueOf(resources.get(resource).getQuantity()));
            resourcesLabels.put(resource, quantityLabel);
            resourceDisplay.getChildren().addAll(logo, quantityLabel);
            resourceBox.getChildren().add(resourceDisplay);
        }
        resourceBox.setSpacing(15);
        resourceBox.setPadding(new Insets(10));
        resourceBox.setStyle("-fx-alignment: center-left;");
        resourceBox.setPrefHeight(menuBar.getPrefHeight());

        menu.getItems().addAll(newGameItem, loadGameItem, saveGameItem, exitItem);
        container = new HBox(menuBar,resourceBox);
        container.setSpacing(10); // Space between menu and resources
        container.setPadding(new Insets(5));
        container.setStyle("-fx-alignment: center-left;");
        container.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("/ubx/project/javarts/menu_background.png").toExternalForm()), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        this.getChildren().addAll(container);
    }

    public void actualiseResources() {
        Map<ResourceType, ResourceDecorator> resources = ResourceManager.getResources();
        for (ResourceType resource : resources.keySet()) {
            resourcesLabels.get(resource).setText(String.valueOf(resources.get(resource).getQuantity()));
        }
    }

}
