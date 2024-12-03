package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Exception.WrongBuildingType;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.ArrayList;
import java.util.Hashtable;

public class BuildingBuilder {

    public BuildingBuilder() {

    }

    public Building build(BuildingType buildingType, Position position) {
        switch (buildingType) {
            case WOODENCABIN -> {
                Building woodenCabin = new WorkingBuilding(new ProductionBuilding(new LivingBuilding(new BasicBuilding(position, new Size(1, 1), "WodenCabin"), 2, 0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.WOOD, 2); put(ResourceType.FOOD, 2);}}), 2, 0);
                woodenCabin.addFunction(new ArrayList<BuildingFunction>());
                return woodenCabin;
            }
            case HOUSE -> {
                Building house = new LivingBuilding(new BasicBuilding(position, new Size(2, 2), "House"), 4,0);
                house.addFunction(new ArrayList<BuildingFunction>());
                return house;
            }
            case APPARTMENTBUILDING -> {
                Building apartment = new LivingBuilding(new BasicBuilding(position, new Size(3, 2), "ApartmentBuilding"), 60,0);
                apartment.addFunction(new ArrayList<BuildingFunction>());
                return apartment;
            }
            case FARM -> {
                Building farm = new WorkingBuilding(new ProductionBuilding(new LivingBuilding(new BasicBuilding(position, new Size(3, 3), "Farm"), 5,0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.FOOD, 2);}}), 3, 0);
                farm.addFunction(new ArrayList<BuildingFunction>());
                return farm;
            }
            case QUARRY -> {
                Building quarry = new LivingBuilding( new ProductionBuilding( new WorkingBuilding( new BasicBuilding(position, new Size(2,2), "Quarry"), 30, 0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STONE, 4); put(ResourceType.IRON, 4); put(ResourceType.COAL, 4);}}), 2, 0);
                quarry.addFunction(new ArrayList<BuildingFunction>());
                return quarry;
            }
            case LUMBERMILL -> {
                Building lumbermill = new ProductionBuilding(new ConsumptionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(3,3), "LumberMill"), 10, 0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.WOOD, 4);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.LUMBER, 4);}});
                lumbermill.addFunction(new ArrayList<BuildingFunction>());
                return lumbermill;
            }
            case CEMENTPLANT -> {
                Building cementplant = new ProductionBuilding(new ConsumptionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(4, 3), "CementPlant"), 10, 0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STONE, 4); put(ResourceType.COAL, 4);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.CEMENT, 4);}});
                cementplant.addFunction(new ArrayList<BuildingFunction>());
                return cementplant;
            }
            case STEELMILL -> {
                Building steelmill = new ProductionBuilding(new ConsumptionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(4,3), "SteelMill"), 40, 0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.IRON, 4); put(ResourceType.COAL, 2);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STEEL, 4);}});
                steelmill.addFunction(new ArrayList<BuildingFunction>());
                return steelmill;
            }
            case TOOLFACTORY -> {
                Building toolfactory = new ConsumptionBuilding(new ProductionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(4, 3), "ToolFactory"), 12,0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.TOOLS, 4);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STEEL, 4); put(ResourceType.COAL, 4);}});
                toolfactory.addFunction(new ArrayList<BuildingFunction>());
                return toolfactory;
            }
        }
        throw new WrongBuildingType("Can't find building type " + buildingType);
    }
}
