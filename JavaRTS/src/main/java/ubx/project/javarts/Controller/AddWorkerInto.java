package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

/**
 * Command to add a worker at into a specific building.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of adding a worker
 * so it can be executed later or managed in a queue.
 *
 */
public class AddWorkerInto implements Command {
    private Building building;

    public AddWorkerInto(Building b) {
        this.building = b;
    }

    @Override
    public void execute(GameManager model, Controller controller) {

        model.assignWorkerTo(building);
    }
}
