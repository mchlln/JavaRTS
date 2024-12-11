package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

/**
 * Command to add block the production/consumption of a specific building.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of blocking a building
 * so it can be executed later or managed in a queue.
 *
 */
public class BlockBuildingCommand implements Command {
    private final Building building;

    public BlockBuildingCommand(Building building) {
        this.building = building;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.blockBuilding(building);
    }
}
