package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

/**
 * Command to put a specific building in run state.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of running a building
 * so it can be executed later or managed in a queue.
 *
 */
public class RunBuildingCommand implements Command {
    private final Building building;

    public RunBuildingCommand(Building building) {
        this.building = building;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.runBuilding(building);
    }
}
