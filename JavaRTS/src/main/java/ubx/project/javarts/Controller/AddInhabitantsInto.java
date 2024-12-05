package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.GameManager;

public class AddInhabitantsInto implements Command {
    private final Building building;

    public AddInhabitantsInto(Building b) {
        this.building = b;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        model.createInhabitantInto(building);
    }
}