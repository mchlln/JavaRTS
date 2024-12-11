package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

/**
 * Command to boost the production of a specific building.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of boosting a building
 * so it can be executed later or managed in a queue.
 *
 */
public class BoostBuildingCommand implements Command {
    private final Building building;

    public BoostBuildingCommand(Building building) {
        this.building = building;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.boostBuilding(building);
    }
}