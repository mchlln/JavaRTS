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
    Position getPosition();
    ArrayList<BuildingFunction> getFunctions();
    void addFunction(ArrayList<BuildingFunction> functions);
    public Map<ResourceType, Integer> getDailyConsumption();
    public List<People> getWorkers();
    public int getMaxWorkers();
    public int getNumberWorkers();
    public void addWorker(People people);
    public void removeWorker(People people);
    public Map<ResourceType, Integer> getDailyProduction();
    public List<People> getInhabitants();
    public int getMaxInhabitants();
    public int getNumberInhabitants();
    public void addInhabitant(People people);
    public void removeInhabitant(People people);
    public HashMap<ResourceType,Integer> handle();
    public String getName();
}
