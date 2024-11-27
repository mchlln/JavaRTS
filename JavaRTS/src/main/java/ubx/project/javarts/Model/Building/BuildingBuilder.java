package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.Hashtable;
import java.util.Map;

public class BuildingBuilder {

    public BuildingBuilder() {

    }

    public Building build(BuildingType buildingType, Position position) {
        switch (buildingType) {
            case WOODENCABIN -> {
                return new WorkingBuilding(new ProductionBuilding(new LivingBuilding(new BasicBuilding(position, new Size(1, 1)), 2, 0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.WOOD, 2); put(ResourceType.FOOD, 2);}}), 2, 0);
            }
            case HOUSE -> {
                return  new LivingBuilding(new BasicBuilding(position, new Size(2, 2)), 4,0);
            }
            case APPARTMENTBUILDING -> {
                return new LivingBuilding(new BasicBuilding(position, new Size(3, 2)), 60,0);
            }
            case FARM -> {
                return new WorkingBuilding(new ProductionBuilding(new LivingBuilding(new BasicBuilding(position, new Size(3, 3)), 5,0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.FOOD, 2);}}), 3, 0);
            }
            case QUARRY -> {
                return new LivingBuilding( new ProductionBuilding( new WorkingBuilding( new BasicBuilding(position, new Size(2,2)), 30, 0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STONE, 4); put(ResourceType.IRON, 4); put(ResourceType.COAL, 4);}}), 2, 0);
            }
            case LUMBERMILL -> {
                return new ProductionBuilding(new ConsumptionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(3,3)), 10, 0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.WOOD, 4);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.LUMBER, 4);}});
            }
            case CEMENTPLANT -> {
                return new ProductionBuilding(new ConsumptionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(4, 3)), 10, 0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STONE, 4); put(ResourceType.COAL, 4);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.CEMENT, 4);}});
            }
            case STEELMILL -> {
                return new ProductionBuilding(new ConsumptionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(4,3)), 40, 0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.IRON, 4); put(ResourceType.COAL, 2);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STEEL, 4);}});
            }
            case TOOLFACTORY -> {
                return new ConsumptionBuilding(new ProductionBuilding(new WorkingBuilding(new BasicBuilding(position, new Size(4, 3)), 12,0), new Hashtable<ResourceType, Integer>(){{put(ResourceType.TOOLS, 4);}}), new Hashtable<ResourceType, Integer>(){{put(ResourceType.STEEL, 4); put(ResourceType.COAL, 4);}});
            }
        }
        throw new RuntimeException("Can't find building type " + buildingType);
    }
}
