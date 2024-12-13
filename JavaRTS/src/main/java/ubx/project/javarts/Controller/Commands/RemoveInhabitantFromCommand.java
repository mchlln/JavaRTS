package ubx.project.javarts.Controller.Commands;

import ubx.project.javarts.Controller.Command;
import ubx.project.javarts.Controller.Controller;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

/**
 * Command to remove an inhabitant of a specific building.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of removing an inhabitant
 * so it can be executed later or managed in a queue.
 *
 */
public class RemoveInhabitantFromCommand implements Command {
    private final Building building;

    public RemoveInhabitantFromCommand(Building b) {
        this.building = b;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.deleteInhabitantFrom(building);
    }
}
