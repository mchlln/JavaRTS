package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.Building.Building;

import ubx.project.javarts.Model.GameManager;

public class SetSelectedBuildingInfo implements Command {
    private Building b;

    public SetSelectedBuildingInfo(Building b) {
        this.b = b;
    }

    @Override
    public void execute(GameManager model, Controller controller) {
        controller.ChangeSelectedBuildingInfo(b);
    }
}