package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.GameManager;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.View.MainView;

/**
 * Controller of the Design Pattern MVC (Model View Controller)
 */
public class Controller {
    private GameManager model;
    private MainView view;
    private BagOfCommands bagOfCommands;
    public BuildingType selectedBuilding;
    public Building selectedBuildingInfo;

    /**
     * Instanciation of a controller needed to execute commands given by the view on the model
     *
     * @param model {@link GameManager} to send commands to
     * @param view {@link MainView} to update after execution of commands
     * @param bagOfCommands {@link BagOfCommands} to put commands into
     */
    public Controller(GameManager model, MainView view, BagOfCommands bagOfCommands) {
        this.model = model;
        this.view = view;
        this.bagOfCommands = bagOfCommands;
        bagOfCommands.setModel(model);
    }

    public void ChangeSelectedBuilding(BuildingType buildingType) {
        selectedBuilding = buildingType;
        view.setSelectedBuilding(buildingType);
    }

    public void ChangeSelectedBuildingInfo(Building building) {
        selectedBuildingInfo= building;
        view.setSelectedBuildingInfo(building);
    }

}
