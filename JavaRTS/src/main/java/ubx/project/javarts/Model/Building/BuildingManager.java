package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Exception.NotEnoughResources;
import ubx.project.javarts.Exception.WrongBuildingType;
import ubx.project.javarts.Model.Building.State.States;
import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Resource.ResourceManager;

import java.util.*;

public class BuildingManager {
    private final Set<Building> buildings;

    public BuildingManager() {
        buildings = new HashSet<>();
    }

    public static HashMap<ResourceType, Integer> buildingCost(BuildingType buildingType) {
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

    public static boolean isBuildable(BuildingType building) {
        HashMap<ResourceType,Integer> cost = buildingCost(building);
        return ResourceManager.areAvailable(cost);
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
                throw new NotEnoughResources("Not enough resources to create a " + building.getType());
            }
        }
    }

    public void repairBuilding(Building building) {
        ResourceManager.removeResource(ResourceType.TOOLS,1);
        building.switchState(States.RUNNING,-1);
    }

    public void boostBuilding(Building building) {
        ResourceManager.removeResource(ResourceType.TOOLS,1);
        building.switchState(States.BOOSTED,5);
    }

    public void blockBuilding(Building building) {
        building.switchState(States.BLOCKED,-1);
    }

    public void runBuilding(Building building) {
        building.switchState(States.RUNNING,-1);
    }

    public void removeBuilding(Building building) {
        if (!exists(building)) {
            return;
        }
        buildings.remove(building);
        System.out.println(buildings);
    }

    public boolean exists(Building building) {
        return buildings.contains(building);
    }

    public void addInhabitantInto(Building building, People people) {
        if (buildings.contains(building)) {
            if (building.getFunctions().contains(BuildingFunction.LIVING)){
                building.addInhabitant(people);
                people.affectHouse(building);
            }else{
                throw new WrongBuildingType("Building cannot have inhabitant " + building.getType());
            }

        }
    }


    public Set<Building> getBuildings() {
        return buildings;
    }

    public void handle(){
        //get global consumption and production
        HashMap<ResourceType, Integer> global = new HashMap<>();
        for (Building building : buildings) {
            HashMap<ResourceType, Integer> resources = building.handle();
            if (building.getFunctions().contains(BuildingFunction.WORKING)){
                double percentage = ((double) building.getNumberWorkers() / building.getMaxWorkers());
                //System.out.println("percentage : " + percentage);
                for(ResourceType rt : resources.keySet()){
                    global.put(rt, (int) (global.getOrDefault(rt, 0) + resources.get(rt)*percentage)); //update the resources according to the number of workers in the building
                }
            }
            if(building.getFunctions().contains(BuildingFunction.LIVING)){
                global.put(ResourceType.FOOD, global.getOrDefault(ResourceType.FOOD, 0) -  building.getNumberInhabitants());
            }

        }
        System.out.println("[Game cycle] Daily resources: " + global);
        // update the resources
        for(ResourceType rt : global.keySet()){
            try {
                ResourceManager.addResource(rt, global.get(rt));
            } catch (NotEnoughResources e) {
                System.out.println("[Game cycle] Not enough resources: " + global.get(rt));
                for (Building b : buildings) {
                    if(b.getFunctions().contains(BuildingFunction.CONSUMING)){
                        if (b.getDailyConsumption().containsKey(rt)){
                            if (b.getState() != States.BLOCKED || b.getState() != States.BROKEN){
                                b.switchState(States.BLOCKED,-1);
                            }
                        }
                    }
                }
            }

        }

    }
}
