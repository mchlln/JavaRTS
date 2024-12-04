package ubx.project.javarts.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import ubx.project.javarts.Model.Building.Building;

import java.util.HashMap;
import java.util.Set;

public class PeopleFooter extends ScrollPane {
    private HBox container;

    public PeopleFooter() {
        // Initialize container to hold the widgets
        container = new HBox(10); // 10px spacing between widgets
        container.setPadding(new Insets(10));
        container.setAlignment(Pos.CENTER_LEFT);

        generateButtons();

        this.setContent(container);
        this.setFitToHeight(true);  // Ensure ScrollPane fits to the full height of its content
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Enable horizontal scrolling if the content overflows
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable vertical scrolling

        // Set width to be dynamic, we want it to scale with the window size
        this.setPrefWidth(Double.MAX_VALUE);
    }

    public void generateButtons() {
        VBox buttons = new VBox();
        buttons.setPadding(new Insets(10));

        buttons.getChildren().addAll(new Button("Add Inhabitant"), new Button("Remove Inhabitant"), new Button("Assign Worker"), new Button("Fire Worker"));
        container.getChildren().addAll(buttons);
    }

    public void addWidget(javafx.scene.Node widget) {
        Scale scale = new Scale(0.8, 0.8); // Scale factor 0.5 to reduce to 50%
        widget.getTransforms().add(scale);
        container.getChildren().add(widget);
    }

    public void updateBuildings(Set<Building> buildings) {
        container.getChildren().clear();
        generateButtons();
        for (Building building : buildings) {
            BuildingInfoCard bc = new BuildingInfoCard(building);
            container.getChildren().add(bc);
        }

    }
}
