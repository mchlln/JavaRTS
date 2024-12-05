package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

public class RemoveWorkerFrom  implements Command {
    private final Building building;

    public RemoveWorkerFrom(Building b) {
        this.building = b;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.deleteWorkerFrom(building);
    }
}

