package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

public class RemoveInhabitantFrom implements Command {
    private final Building building;

    public RemoveInhabitantFrom(Building b) {
        this.building = b;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.deleteInhabitantFrom(building);
    }
}
