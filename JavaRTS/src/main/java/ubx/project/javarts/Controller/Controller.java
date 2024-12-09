package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.GameManager;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.View.MainView;

public class Controller {
    private GameManager model;
    private MainView view;
    private BagOfCommands bagOfCommands;
    public BuildingType selectedBuilding;
    public Building selectedBuildingInfo;

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
