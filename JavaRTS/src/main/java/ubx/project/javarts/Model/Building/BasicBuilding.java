package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.Resource;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.*;

public class BasicBuilding implements Building{
    private final UUID id;
    private Map<ResourceType, Integer> cost;
    private final Size size;
    private final Position position;
    private BuildingType type;
    private final ArrayList<BuildingFunction> functions = new ArrayList<BuildingFunction>();

    public BasicBuilding(Position pos, Size s){
        this.id = UUID.randomUUID();
        this.position = pos;
        this.size = s;
    }
    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public Map<ResourceType, Integer> getCost() {
        return cost;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void getConstructionTime() {

    }

    @Override
    public BuildingType getType() {
        return type;
    }

    @Override
    public Position getPostion() {
        return position;
    }

    @Override
    public ArrayList<BuildingFunction> getFunctions() {
        return functions;
    }

    @Override
    public void addFunction(BuildingFunction function){
        functions.add(function);
    }

    @Override
    public Map<ResourceType, Integer> getDailyConsumption() {
        throw new WrongThreadException("Building cannot consume resources");
    }

    @Override
    public Set<People> getWorkers() {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public int getMaxWorkers() {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public int getMinWorkers() {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public int getNumberWorkers() {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public void addWorker(People people) {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public void removeWorker(People people) {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public Map<ResourceType, Integer> getDailyProduction() {
        throw new WrongThreadException("Building cannot produce resources");
    }

    @Override
    public Set<People> getInhabitants() {
        throw new WrongThreadException("Building cannot have inhabitants");
    }

    @Override
    public int getMaxInhabitants() {
        throw new WrongThreadException("Building cannot have inhabitants");
    }

    @Override
    public int getMinInhabitants() {
        throw new WrongThreadException("Building cannot have inhabitants");
    }

    @Override
    public int getNumberInhabitants() {
        throw new WrongThreadException("Building cannot have inhabitants");
    }

    @Override
    public void addInhabitant(People people) {
        throw new WrongThreadException("Building cannot have inhabitants");
    }

    @Override
    public void removeInhabitant(People people) {
        throw new WrongThreadException("Building cannot have inhabitants");
    }
    @Override
    public HashMap<ResourceType,Integer> handle(){
        return new HashMap<>();
    }
}
