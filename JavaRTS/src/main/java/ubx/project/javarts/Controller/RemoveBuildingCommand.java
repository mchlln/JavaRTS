package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;
import ubx.project.javarts.Model.Position;

public class RemoveBuildingCommand implements Command {
    private Building building;

    public RemoveBuildingCommand(Building building) {
        this.building = building;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.removeBuilding(building);
    }
}
