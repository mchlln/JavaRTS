package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.GameManager;
import ubx.project.javarts.Model.Position;

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
