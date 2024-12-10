package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

public class BoostBuildingCommand implements Command{
    private final Building building;

    public BoostBuildingCommand(Building building) {
        this.building = building;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.boostBuilding(building);
    }
}