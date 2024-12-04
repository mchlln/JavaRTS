package ubx.project.javarts.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingFunction;

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

    public void updateBuildings(Set<Building> buildings) {
        int inhabitants = 0;
        int workers = 0;
        int maxInhabitants = 0;
        int maxWorkers = 0;
        container.getChildren().clear();
        VBox container2 = new VBox();
        container2.setAlignment(Pos.CENTER);
        Label label = new Label("Building List");
        label.setAlignment(Pos.CENTER);
        container2.getChildren().add(label);
        HBox container3 = new HBox();
        container2.getChildren().add(container3);
        generateButtons();
        for (Building building : buildings) {
            if (building.getFunctions().contains(BuildingFunction.LIVING)){
                inhabitants += building.getInhabitants().size();
                maxInhabitants += building.getMaxInhabitants();
            }
            if (building.getFunctions().contains(BuildingFunction.WORKING)){
                workers += building.getWorkers().size();
                maxWorkers += building.getMaxWorkers();
            }

            BuildingInfoCard bc = new BuildingInfoCard(building);
            container3.getChildren().add(bc);
        }
        label.setText("Inhabitants: " + inhabitants + "/" + maxInhabitants + "Workers:" + workers + "/" + maxWorkers);
        container.getChildren().addAll(container2);

    }
}
