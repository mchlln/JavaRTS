package ubx.project.javarts.Controller.Commands;

import ubx.project.javarts.Controller.Command;
import ubx.project.javarts.Controller.Controller;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

/**
 * Command to repair a specific building.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of repairing a building
 * so it can be executed later or managed in a queue.
 *
 */
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
