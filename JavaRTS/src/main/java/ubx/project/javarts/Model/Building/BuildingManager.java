package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;

import java.util.HashSet;
import java.util.Set;

public class BuildingManager {
    private Set<Building> buildings;

    public BuildingManager() {
        buildings = new HashSet<>();
    }

    public void addBuilding(Building building) {
        if (!exists(building)) {
            buildings.add(building);
        }
    }

    public void removeBuilding(Building building) {
        if (!exists(building)) {
            return;
        }
        buildings.remove(building);
    }

    public boolean exists(Building building) {
        return buildings.contains(building);
    }

    public void addInhabitantInto(Building building, People people) {
        if (buildings.contains(building)) {
            if (building.getFunctions().contains(BuildingFunction.LIVING)){
                building.addInhabitant(people);
            }

        }
    }

    public void removeInhabitantFrom(Building building) {
        if (buildings.contains(building)) {
            if (building.getFunctions().contains(BuildingFunction.LIVING)){
                building.removeInhabitant(null); // TODO: change to a random inhabitant
            }
        }
    }

    public void handle(){
        for (Building building : buildings) {
            building.handle();
        }
    }
}
