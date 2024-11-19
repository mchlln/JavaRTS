package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.Map;
import java.util.Set;

public interface Building {
    Size getSize();
    Map<ResourceType, Integer> getCost();

    void getConstructionTime(); //TODO: find return type
    Set<People> getInhabitants();
    Set<People> getWorkers();
    int getMaxInhabitants();
    int getMaxWorkers();
    int getNumberWorkers();
    int getNumberInhabitants();
    BuildingType getType();
    //Position getPostion();
    Map<ResourceType, Integer> getDailyProduction();
    Map<ResourceType, Integer> getDailyConsumption();
}
