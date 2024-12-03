package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.*;

public interface Building {
    Size getSize();
    Map<ResourceType, Integer> getCost();
    UUID getId();
    void getConstructionTime(); //TODO: find return type
    BuildingType getType();
    Position getPostion();
    ArrayList<BuildingFunction> getFunctions();
    void addFunction(BuildingFunction function);
    public Map<ResourceType, Integer> getDailyConsumption();
    public Set<People> getWorkers();
    public int getMaxWorkers();
    public int getMinWorkers();
    public int getNumberWorkers();
    public void addWorker(People people);
    public void removeWorker(People people);
    public Map<ResourceType, Integer> getDailyProduction();
    public Set<People> getInhabitants();
    public int getMaxInhabitants();
    public int getMinInhabitants();
    public int getNumberInhabitants();
    public void addInhabitant(People people);
    public void removeInhabitant(People people);
    public HashMap<ResourceType,Integer> handle();
}
