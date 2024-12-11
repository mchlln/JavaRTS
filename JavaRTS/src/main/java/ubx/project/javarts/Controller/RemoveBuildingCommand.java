package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

/**
 * Command to remove a specific building on the map.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of removing a building
 * so it can be executed later or managed in a queue.
 *
 */
public class RemoveBuildingCommand implements Command {
    private Building building;

    public RemoveBuildingCommand(Building building) {
        this.building = building;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.removeBuilding(building);
    }
}
