package ubx.project.javarts.Model.Building;


import ubx.project.javarts.Exception.WrongBuildingType;
import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public abstract class BuildingDecorator implements Building {
   private final Building decoratedBuilding;

    public BuildingDecorator(Building b) {
        this.decoratedBuilding = b;
    }

    @Override
    public Size getSize() {
        return decoratedBuilding.getSize();
    }

    @Override
    public Map<ResourceType, Integer> getCost() {
        return decoratedBuilding.getCost();
    }

    @Override
    public UUID getId() {
        return decoratedBuilding.getId();
    }

    @Override
    public void getConstructionTime() {

    }

    @Override
    public BuildingType getType() {
        return decoratedBuilding.getType();
    }

    @Override
    public Position getPostion() {
        return decoratedBuilding.getPostion();
    }
    @Override
    public boolean equals(Object obj) {
        //TODO: implement correctly
        return super.equals(obj);
    }

    @Override
    public ArrayList<BuildingFunction> getFunctions() {
        return decoratedBuilding.getFunctions();
    }

    @Override
    public void addFunction(BuildingFunction function){
        decoratedBuilding.addFunction(function);
    }

    @Override
    public Map<ResourceType, Integer> getDailyConsumption() {
        throw new WrongBuildingType("Building cannot consume resources");
    }

    @Override
    public Set<People> getWorkers() {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public int getMaxWorkers() {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public int getMinWorkers() {
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
    public Set<People> getInhabitants() {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public int getMaxInhabitants() {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public int getMinInhabitants() {
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
    public void handle(){

    }
}
