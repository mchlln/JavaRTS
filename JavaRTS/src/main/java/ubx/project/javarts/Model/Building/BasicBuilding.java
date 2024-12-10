package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Exception.WrongBuildingType;
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
    private String name;

    public BasicBuilding(Position pos, Size s, String name, BuildingType type, Map<ResourceType, Integer> cost){
        this.id = UUID.randomUUID();
        this.position = pos;
        this.size = s;
        this.name = name;
        this.type = type;
        this.cost = cost;
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
    public Position getPosition() {
        return position;
    }

    @Override
    public ArrayList<BuildingFunction> getFunctions() {
        return functions;
    }

    @Override
    public void addFunction(ArrayList<BuildingFunction> functions){
        this.functions.addAll(functions);
    }

    @Override
    public Map<ResourceType, Integer> getDailyConsumption() {
        throw new WrongBuildingType("Building cannot consume resources");
    }

    @Override
    public List<People> getWorkers() {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public int getMaxWorkers() {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public int getNumberWorkers() {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public void addWorker(People people) {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public void removeWorker(People people) {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public Map<ResourceType, Integer> getDailyProduction() {
        throw new WrongBuildingType("Building cannot produce resources");
    }

    @Override
    public List<People> getInhabitants() {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public int getMaxInhabitants() {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public int getNumberInhabitants() {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public void addInhabitant(People people) {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public void removeInhabitant(People people) {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }
    @Override
    public HashMap<ResourceType,Integer> handle(){
        return new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }
}
