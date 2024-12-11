package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.GameManager;

/**
 * Command to set the selected building in the controller.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of setting a building
 * so it can be executed later or managed in a queue.
 *
 */
public class SetSelectedBuilding implements Command {
    private BuildingType type;

    public SetSelectedBuilding(BuildingType type) {
        this.type = type;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        controller.ChangeSelectedBuilding(type);
    }
}
