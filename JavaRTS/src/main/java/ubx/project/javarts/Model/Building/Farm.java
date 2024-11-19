package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.Map;
import java.util.Set;

import static ubx.project.javarts.Model.Resource.ResourceType.*;

public class Farm extends BuildingDecorator {
    private static final Size size = new Size(3,3);
    private static final Map<ResourceType, Integer> cost = Map.of( WOOD,5, STONE,5);
    private static final Map<ResourceType, Integer> dailyProduction = Map.of( FOOD,10);

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public Map<ResourceType, Integer> getCost() {
        return cost;
    }

    @Override
    public void getConstructionTime() {

    }

    @Override
    public int getMaxInhabitants() {
        return 7;
    }

    @Override
    public int getMaxWorkers() {
        return 6;
    }

    @Override
    public BuildingType getType() {
        return BuildingType.FARM;
    }

    public Map<ResourceType, Integer> getDailyProduction() {
        return dailyProduction;
    }
}
