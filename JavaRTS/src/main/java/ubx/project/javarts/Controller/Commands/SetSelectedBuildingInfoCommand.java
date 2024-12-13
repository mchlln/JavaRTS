package ubx.project.javarts.Controller.Commands;

import ubx.project.javarts.Controller.Command;
import ubx.project.javarts.Controller.Controller;
import ubx.project.javarts.Model.Building.Building;

import ubx.project.javarts.Model.GameManager;

/**
 * Command to set the selected building info in the controller.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of setting a building info
 * so it can be executed later or managed in a queue.
 *
 */
public class SetSelectedBuildingInfoCommand implements Command {
    private Building b;

    public SetSelectedBuildingInfoCommand(Building b) {
        this.b = b;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        controller.ChangeSelectedBuildingInfo(b);
    }
}