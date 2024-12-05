package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Exception.WrongBuildingType;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class BuildingBuilder {

    public BuildingBuilder() {

    }

    public Building build(BuildingType buildingType, Position position) {
        switch (buildingType) {
            case WOODENCABIN -> {
                Building woodenCabin = new WorkingBuilding(new ProductionBuilding(new LivingBuilding(new BasicBuilding(position, new Size(1, 1), "WodenCabin",buildingType, new HashMap<ResourceType, Integer>(){{put(ResourceType.WOOD, 1);}}), 2), new Hashtable<ResourceType, Integer>(){{put(ResourceType.WOOD, 2); put(ResourceType.FOOD, 2);}}), 2);
                woodenCabin.addFunction(new ArrayList<BuildingFunction>());
                return woodenCabin;
            }
            case HOUSE -> {
                Building house = new LivingBuilding(new BasicBuilding(position, new Size(2, 2), "House",buildingType, new HashMap<ResourceType, Integer>(){{put(ResourceType.WOOD, 2);put(ResourceType.STONE,2);}}), 4);
                house.addFunction(new ArrayList<BuildingFunction>());
                return house;
            }
            case APPARTMENTBUILDING -> {
                Building apartment = new LivingBuilding(new BasicBuilding(position, new Size(3, 2), "ApartmentBuilding",buildingType, new HashMap<ResourceType, Integer>(){{put(ResourceType.WOOD, 50);put(ResourceType.STONE,50);}}), 60);
                apartment.addFunction(new ArrayList<BuildingFunction>());
                return apartment;
            }
            case FARM -> {
                Building farm = new WorkingBuilding(new ProductionBuilding(new LivingBuilding(new BasicBuilding(position, new Size(3, 3), "Farm",buildingType, new HashMap<ResourceType, Integer>(){{put(ResourceType.WOOD, 5);put(ResourceType.STONE,5);}}), 5), new Hashtable<ResourceType, Integer>(){{put(ResourceType.FOOD, 2);}}), 3);
                farm.addFunction(new ArrayList<BuildingFunction>());
                return farm;
            }
            case QUARRY -> {
                Building quarry = new LivingBuilding( new ProductionBuilding( new WorkingBuilding( new BasicBuilding(position, new Size(2,2), "Quarry",buildingType, new HashMap<ResourceType, Integer>(){{put(ResourceType.WOOD, 50);}}), 30), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STONE, 4); put(ResourceType.IRON, 4); put(ResourceType.COAL, 4);}}), 2);
                quarry.addFunction(new ArrayList<BuildingFunction>());
                return quarry;
            }
            case LUMBERMILL -> {
                Building lumbermill = new ProductionBuilding(new ConsumptionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(3,3), "LumberMill",buildingType, new HashMap<ResourceType, Integer>(){{put(ResourceType.WOOD, 50);put(ResourceType.STONE,50);}}), 10), new Hashtable<ResourceType, Integer>(){{put(ResourceType.WOOD, 4);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.LUMBER, 4);}});
                lumbermill.addFunction(new ArrayList<BuildingFunction>());
                return lumbermill;
            }
            case CEMENTPLANT -> {
                Building cementplant = new ProductionBuilding(new ConsumptionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(4, 3), "CementPlant",buildingType, new HashMap<ResourceType, Integer>(){{put(ResourceType.WOOD, 50);put(ResourceType.STONE,50);}}), 10), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STONE, 4); put(ResourceType.COAL, 4);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.CEMENT, 4);}});
                cementplant.addFunction(new ArrayList<BuildingFunction>());
                return cementplant;
            }
            case STEELMILL -> {
                Building steelmill = new ProductionBuilding(new ConsumptionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(4,3), "SteelMill",buildingType, new HashMap<ResourceType, Integer>(){{put(ResourceType.WOOD, 100);put(ResourceType.STONE,50);}}), 40), new Hashtable<ResourceType, Integer>(){{put(ResourceType.IRON, 4); put(ResourceType.COAL, 2);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STEEL, 4);}});
                steelmill.addFunction(new ArrayList<BuildingFunction>());
                return steelmill;
            }
            case TOOLFACTORY -> {
                Building toolfactory = new ConsumptionBuilding(new ProductionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(4, 3), "ToolFactory",buildingType, new HashMap<ResourceType, Integer>(){{put(ResourceType.WOOD, 50);put(ResourceType.STONE,50);}}), 12), new Hashtable<ResourceType, Integer>(){{put(ResourceType.TOOLS, 4);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STEEL, 4); put(ResourceType.COAL, 4);}});
                toolfactory.addFunction(new ArrayList<BuildingFunction>());
                return toolfactory;
            }
        }
        throw new WrongBuildingType("Can't find building type " + buildingType);
    }
}
