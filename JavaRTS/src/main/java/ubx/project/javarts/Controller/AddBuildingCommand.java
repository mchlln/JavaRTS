package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.GameManager;
import ubx.project.javarts.Model.Position;

/**
 * Command to add a building at a specific position in the game.
 *
 * Part of the Command design pattern and works with the Bag of Commands.
 * Encapsulates the action of adding a building
 * so it can be executed later or managed in a queue.
 *
 */
public class AddBuildingCommand implements Command {
    private Position position;

    public AddBuildingCommand(Position position) {
        this.position = position;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.addBuilding(controller.selectedBuilding, position);
    }
}
