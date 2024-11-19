package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.Map;
import java.util.Set;

import static ubx.project.javarts.Model.Resource.ResourceType.*;

public class LumberMill extends BuildingDecorator{
    private static final Size size = new Size(3,3);
    private static final Map<ResourceType, Integer> cost = Map.of( WOOD,50, STONE,50);
    private static final Map<ResourceType, Integer> dailyConsumption = Map.of( WOOD,4);
    private static final Map<ResourceType, Integer> dailyProduction = Map.of( LUMBER,4);

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
        return 0;
    }

    @Override
    public int getMaxWorkers() {
        return 15;
    }

    @Override
    public BuildingType getType() {
        return BuildingType.LUMBERMILL;
    }

    public Map<ResourceType, Integer> getDailyConsumption() {
        return dailyConsumption;
    }
    public Map<ResourceType, Integer> getDailyProduction() {
        return dailyProduction;
    }
}
