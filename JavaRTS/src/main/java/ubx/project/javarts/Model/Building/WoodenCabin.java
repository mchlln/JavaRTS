package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.Map;
import java.util.Set;

import static ubx.project.javarts.Model.Resource.ResourceType.*;

public class WoodenCabin extends BuildingDecorator{
    private static final Size size = new Size(1,1);
    private static final Map<ResourceType, Integer> cost = Map.of( WOOD,1);
    private static final Map<ResourceType, Integer> dailyProduction = Map.of( WOOD,2, FOOD, 2);

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
        return 3;
    }

    @Override
    public int getMaxWorkers() {
        return 3;
    }

    @Override
    public BuildingType getType() {
        return BuildingType.WOODENCABIN;
    }

    @Override
    public Map<ResourceType, Integer> getDailyProduction() {
        return dailyProduction;
    }

}
