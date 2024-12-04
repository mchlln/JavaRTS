package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

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
