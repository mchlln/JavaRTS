package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.GameManager;

public class SetSelectedBuilding  implements Command {
    private BuildingType type;

    public SetSelectedBuilding(BuildingType type) {
        this.type = type;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        controller.ChangeSelectedBuilding(type);
    }
}
