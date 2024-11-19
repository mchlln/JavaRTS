package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import static ubx.project.javarts.Model.Resource.ResourceType.STONE;
import static ubx.project.javarts.Model.Resource.ResourceType.WOOD;

public class House extends BuildingDecorator{
    private static final Size size = new Size(2,2);
    private static final Map<ResourceType, Integer> cost = Map.of( WOOD,2, STONE,2);

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
        //TODO:
    }

    @Override
    public int getMaxInhabitants() {
        return 8;
    }

    @Override
    public int getMaxWorkers() {
        return 0;
    }

    @Override
    public BuildingType getType() {
        return BuildingType.HOUSE;
    }
}
