package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Building.State.States;
import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.*;

public class ProductionBuilding extends BuildingDecorator{
    private final Map<ResourceType, Integer> dailyProduction;
    Building b;

    public ProductionBuilding(Building b, Map<ResourceType, Integer> dailyProduction) {
        super(b);
        this.dailyProduction = dailyProduction;
        this.b = b;
    }
    @Override
    public Map<ResourceType, Integer> getDailyProduction() {
        return dailyProduction;
    }

    @Override
    public void addFunction(ArrayList<BuildingFunction> functions){
        functions.add(BuildingFunction.PRODUCING);
        super.addFunction(functions);
    }

    @Override
    public HashMap<ResourceType, Integer> handle(){
        States currentState = b.getState();
        HashMap<ResourceType,Integer> resources = b.handle();
        if (currentState == States.CONSTRUCTION || currentState == States.BROKEN || currentState == States.BLOCKED) return resources;
        int boost = 1;
        if (currentState == States.BOOSTED) boost = 2;
        for(ResourceType rt : dailyProduction.keySet()){
            resources.put(rt, resources.getOrDefault(rt, 0) + dailyProduction.get(rt)*boost);
        }
        return resources;
    }
    @Override
    public List<People> getInhabitants() {
        return b.getInhabitants();
    }

    @Override
    public int getMaxInhabitants() {
        return b.getMaxInhabitants();
    }

    @Override
    public int getNumberInhabitants(){
        return b.getNumberInhabitants();
    }

    @Override
    public void addInhabitant(People people){
        b.addInhabitant(people);
    }
    @Override
    public void removeInhabitant(People people){
        b.removeInhabitant(people);
    }
    @Override
    public Map<ResourceType, Integer> getDailyConsumption() {
        return b.getDailyConsumption();
    }
    @Override
    public List<People> getWorkers() {
        return b.getWorkers();
    }
    @Override
    public int getMaxWorkers() {
        return b.getMaxWorkers();
    }
    @Override
    public int getNumberWorkers(){
        return b.getNumberWorkers();
    }
    @Override
    public void addWorker(People people){
        b.addWorker(people);
    }
    @Override
    public void removeWorker(People people){
        b.removeWorker(people);
    }
}
