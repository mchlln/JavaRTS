package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Exception.WrongBuildingType;
import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;
import ubx.project.javarts.Model.Resource.ResourceManager;
import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.*;

public class BuildingManager {
    private Set<Building> buildings;

    public BuildingManager() {
        buildings = new HashSet<>();
    }

    public HashMap<ResourceType, Integer> buildingCost(BuildingType buildingType) {
        HashMap<ResourceType, Integer> cost;
        switch (buildingType) {
            case WOODENCABIN -> {
                cost = new HashMap<>(){{put(ResourceType.WOOD, 1);}};
            }
            case HOUSE -> {
                cost = new HashMap<>(){{put(ResourceType.WOOD, 2);put(ResourceType.STONE,2);}};
            }
            case APPARTMENTBUILDING -> {
                cost = new HashMap<>(){{put(ResourceType.WOOD, 50);put(ResourceType.STONE,50);}};
            }
            case FARM -> {
                cost = new HashMap<>(){{put(ResourceType.WOOD, 5);put(ResourceType.STONE,5);}};
            }
            case QUARRY -> {
                cost = new HashMap<>(){{put(ResourceType.WOOD, 50);}};
            }
            case LUMBERMILL -> {
                cost = new HashMap<>(){{put(ResourceType.WOOD, 50);put(ResourceType.STONE,50);}};
            }
            case CEMENTPLANT -> {
                cost = new HashMap<>(){{put(ResourceType.WOOD, 50);put(ResourceType.STONE,50);}};
            }
            case STEELMILL -> {
                cost = new HashMap<>(){{put(ResourceType.WOOD, 100);put(ResourceType.STONE,50);}};
            }
            case TOOLFACTORY -> {
                cost = new HashMap<>(){{put(ResourceType.WOOD, 50);put(ResourceType.STONE,50);}};
            }
            default -> {
                throw new WrongBuildingType("Can't find building type " + buildingType);
            }
        }
        return cost;
    }

    public void addBuilding(Building building) {
        if (!exists(building)) {
            HashMap<ResourceType,Integer> cost = buildingCost(building.getType());
            if(ResourceManager.areAvailable(cost)){
                for(ResourceType type : cost.keySet()){
                    ResourceManager.removeResource(type, cost.get(type));
                }
                buildings.add(building);
            }else{
                throw new NoSuchElementException("Not enough resources to create a " + building.getType());
            }
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

    public void removeInhabitantFrom(Building building, People people) {
        if (buildings.contains(building)) {
            if (building.getFunctions().contains(BuildingFunction.LIVING)){
                building.removeInhabitant(people);
            }
        }
    }

    public Hashtable<ResourceType, Integer> handle(){
        Hashtable<ResourceType, Integer> global = new Hashtable<>();
        for (Building building : buildings) {
            //building.handle();
        }
        return global;
    }
}
