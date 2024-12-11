package ubx.project.javarts.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import ubx.project.javarts.Controller.*;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingFunction;
import java.util.Set;

public class PeopleFooter extends VBox {
    private Label inhabitantsLabel = new Label();
    private Label workerLabel = new Label();
    private HBox cardContainer;
    private ScrollPane cardRoot;
    private Building selectedBuilding;

    public PeopleFooter() {
        cardRoot = new ScrollPane();
        // Initialize container to hold the widgets
        cardContainer = new HBox(10);
        this.setBackground(new Background(new BackgroundImage(
                new Image(getClass().getResource("/ubx/project/javarts/panel_blue.png").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER_LEFT);

        generateButtons();

        /*
         * this.setContent(container);
         * this.setFitToHeight(true); // Ensure ScrollPane fits to the full height of
         * its content
         * this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Enable horizontal
         * scrolling if the content overflows
         * this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable vertical
         * scrolling
         * 
         * // Set width to be dynamic, we want it to scale with the window size
         * this.setPrefWidth(Double.MAX_VALUE);
         */
        cardRoot.setContent(cardContainer);
        cardRoot.setFitToHeight(true);
        cardRoot.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Enable horizontal scrolling if content overflows
        cardRoot.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Disable vertical scrolling
        // cardRoot.setPrefWidth(300); // Set a preferred width to give it some initial
        // space
        // cardRoot.setMaxWidth(Double.MAX_VALUE); // Optional: max width (adjust as
        // needed)

        // Allow cardRoot to take as much space as possible in the container without
        // overflowing
        HBox.setHgrow(cardRoot, Priority.ALWAYS);
        this.getChildren().add(cardRoot);

        this.getChildren().addAll();
    }

    public void generateButtons() {
        HBox buttons = new HBox();
        // buttons.setStyle("-fx-background-color: lightblue;");
        buttons.setFillHeight(true);
        // buttons.setPrefWidth(Double.MAX_VALUE);
        buttons.setPadding(new Insets(10));
        buttons.setSpacing(15);
        Button addInhabitantButton = new Button("Add Inhabitant");
        addInhabitantButton.setOnAction(event -> {
            BagOfCommands.getInstance().addCommand(new AddInhabitantInto(selectedBuilding));
            System.out.println("Inhabitant added to  " + selectedBuilding);
        });
        Button removeInhabitantButton = new Button("Remove Inhabitant");
        removeInhabitantButton.setOnAction(event -> {
            BagOfCommands.getInstance().addCommand(new RemoveInhabitantFrom(selectedBuilding));
            System.out.println("Inhabitant removed from  " + selectedBuilding);
        });
        Button assignWorkerButton = new Button("Assign Worker");
        assignWorkerButton.setOnAction(event -> {
            BagOfCommands.getInstance().addCommand(new AddWorkerInto(selectedBuilding));
            System.out.println("Worker added to " + selectedBuilding);
        });
        Button fireWorkerButton = new Button("Fire Worker");
        fireWorkerButton.setOnAction(event -> {
            BagOfCommands.getInstance().addCommand(new RemoveWorkerFrom(selectedBuilding));
            System.out.println("Worker removed from " + selectedBuilding);
        });
        buttons.getChildren().addAll(addInhabitantButton, removeInhabitantButton, assignWorkerButton, fireWorkerButton);
        ImageView inhabitants = new ImageView(
                new Image(getClass().getResource("/ubx/project/javarts/icons/house.png").toExternalForm()));
        ImageView workers = new ImageView(
                new Image(getClass().getResource("/ubx/project/javarts/icons/worker.png").toExternalForm()));
        workers.fitHeightProperty().bind(workerLabel.heightProperty());
        workers.setPreserveRatio(true);
        inhabitants.fitHeightProperty().bind(inhabitantsLabel.heightProperty());
        inhabitants.setPreserveRatio(true);
        buttons.getChildren().add(inhabitants);
        buttons.getChildren().addAll(inhabitantsLabel, workers, workerLabel);
        buttons.setAlignment(Pos.CENTER);
        this.getChildren().addAll(buttons);
    }

    public void updateBuildings(Set<Building> buildings) {
        int inhabitants = 0;
        int workers = 0;
        int maxInhabitants = 0;
        int maxWorkers = 0;
        this.getChildren().clear();
        generateButtons();
        cardContainer.getChildren().clear();
        VBox container2 = new VBox();
        container2.setAlignment(Pos.CENTER);
        HBox container3 = new HBox();
        container2.getChildren().add(container3);
        for (Building building : buildings) {
            if (building.getFunctions().contains(BuildingFunction.LIVING)) {
                inhabitants += building.getInhabitants().size();
                maxInhabitants += building.getMaxInhabitants();
            }
            if (building.getFunctions().contains(BuildingFunction.WORKING)) {
                workers += building.getWorkers().size();
                maxWorkers += building.getMaxWorkers();
            }

            BuildingInfoCard bc = new BuildingInfoCard(building, selectedBuilding);
            bc.setOnMouseClicked(event -> {
                BagOfCommands.getInstance().addCommand(new SetSelectedBuildingInfo(building));
            });
            container3.getChildren().add(bc);
        }
        inhabitantsLabel.setText("Inhabitants: " + inhabitants + "/" + maxInhabitants);
        workerLabel.setText("Workers: " + workers + "/" + maxWorkers);
        cardContainer.getChildren().addAll(container2);
        this.getChildren().add(cardRoot);

    }

    public void setSelectedBuildingInfo(Building building) {
        this.selectedBuilding = building;
    }
}
