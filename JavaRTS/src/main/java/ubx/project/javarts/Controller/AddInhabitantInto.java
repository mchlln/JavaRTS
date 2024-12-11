package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

/**
 * Command to add an inhabitant in a specific building.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of adding an inhabitant
 * so it can be executed later or managed in a queue.
 *
 */
public class AddInhabitantInto implements Command {
    private final Building building;

    public AddInhabitantInto(Building b) {
        this.building = b;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.createInhabitantInto(building);
    }
}