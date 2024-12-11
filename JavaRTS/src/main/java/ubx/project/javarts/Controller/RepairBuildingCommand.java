package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

public class RepairBuildingCommand implements Command {
    private final Building building;

    public RepairBuildingCommand(Building building) {
        this.building = building;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.repairBuilding(building);
    }
}
