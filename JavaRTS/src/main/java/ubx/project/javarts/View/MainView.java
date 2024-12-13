package ubx.project.javarts.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ubx.project.javarts.Controller.BagOfCommands;
import ubx.project.javarts.Controller.Controller;
import ubx.project.javarts.Controller.Commands.SetSelectedBuildingCommand;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingManager;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.GameManager;

import java.util.ArrayList;
import java.util.Objects;

/**
 * View of the application, delegates the differents features to other classes.
 */
public class MainView implements Observer {

    private Controller controller;
    private final ArrayList<BuildingCard> cards = new ArrayList<>();
    private final CustomMenu topContainer;
    private final MapView map;
    private final GameManager model;
    private final VBox footer;
    private final BuildingFooter buildingFooter;
    private final PeopleFooter peopleFooter;
    private BorderPane root;
    private String footerState = "building";

    /**
     * Creates the main window of the application
     *
     * @param stage {@link Stage} window if the application
     * @param model {@link GameManager} to save because the view know the model for
     *              simplicity
     */
    public MainView(Stage stage, GameManager model) {
        stage.setTitle("JAVA RTS");
        this.model = model;
        topContainer = new CustomMenu();
        footer = new VBox();
        map = new MapView();
        root = new BorderPane();
        buildingFooter = new BuildingFooter();
        peopleFooter = new PeopleFooter();
        root.setTop(topContainer);
        root.setCenter(map);
        root.setBottom(footer);
        HBox modeSelection = new HBox();
        Button buildingModeButton = new Button("Building");
        buildingModeButton.onMouseClickedProperty().setValue(event -> {
            switchEditionMode("building");
        });
        Button peopleModeButton = new Button("People");
        peopleModeButton.onMouseClickedProperty().setValue(event -> {
            switchEditionMode("people");
        });
        modeSelection.getChildren().addAll(buildingModeButton, peopleModeButton);
        footer.getChildren().add(modeSelection);
        footer.getChildren().add(buildingFooter);
        peopleFooter.prefHeightProperty().bind(stage.heightProperty().multiply(0.30));
        peopleFooter.setMinHeight(50); // Optional: Minimum height for the footer
        peopleFooter.setMaxHeight(Double.MAX_VALUE); // Maximum height allows resizing
        buildingFooter.prefViewportHeightProperty().bind(stage.heightProperty().multiply(0.30));
        buildingFooter.setMinHeight(50); // Optional: Minimum height for the footer
        buildingFooter.setMaxHeight(Double.MAX_VALUE); // Maximum height allows resizing
        for (BuildingType buildingType : BuildingType.values()) {
            BuildingCard b = new BuildingCard(buildingType);
            cards.add(b);
            b.setOnMouseClicked(event -> {
                BagOfCommands.getInstance().addCommand(new SetSelectedBuildingCommand(buildingType));
            });
            buildingFooter.addWidget(b);
        }

        model.addObserver(this::update);
        model.addErrorListener(this::updateError);

        setAvailability();
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setSelectedBuilding(BuildingType buildingType) {
        for (BuildingCard b : cards) {
            b.setSelected(buildingType);
        }
    }

    public void setSelectedBuildingInfo(Building building) {
        peopleFooter.setSelectedBuildingInfo(building);
    }

    public void setAvailability() {
        for (BuildingCard b : cards) {
            if (!BuildingManager.isBuildable(b.getBuildingType())) {
                b.setOpacity(0.4);
            } else {
                b.setOpacity(1);
            }
        }
    }

    public void switchEditionMode(String mode) {
        if (Objects.equals(mode, "building")) {
            if (!footerState.equals("building")) {
                footer.getChildren().remove(peopleFooter);
                footer.getChildren().add(buildingFooter);
                footerState = "building";
            }

        } else { // Because there are only two states
            if (!footerState.equals("people")) {
                footer.getChildren().remove(buildingFooter);
                footer.getChildren().add(peopleFooter);
                footerState = "people";
            }

        }
    }

    @Override
    public void update() {
        topContainer.actualiseResources();
        map.drawBuildings(model.getBuildings());
        peopleFooter.updateBuildings(model.getBuildings());
        setAvailability();

    }

    public void updateError() {
        topContainer.showError(model.currentException.getMessage());
    }

}
