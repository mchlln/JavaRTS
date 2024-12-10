package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Exception.WrongBuildingType;
import ubx.project.javarts.Model.Building.State.States;
import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.*;

public class ConsumptionBuilding extends BuildingDecorator{
    private Map<ResourceType, Integer> dailyConsumption;
    Building b;

    public ConsumptionBuilding(Building b, Map<ResourceType, Integer> dailyConsumption) {
        super(b);
        this.dailyConsumption = dailyConsumption;
        this.b = b;
    }

    @Override
    public Map<ResourceType, Integer> getDailyConsumption() {
        return dailyConsumption;
    }

    @Override
    public void addFunction(ArrayList<BuildingFunction> functions){
        functions.add(BuildingFunction.CONSUMING);
        super.addFunction(functions);
    }

    @Override
    public HashMap<ResourceType, Integer> handle(){
        HashMap<ResourceType,Integer> resources = b.handle();
        if (b.getState() == States.RUNNING || b.getState() == States.BOOSTED){
            for(ResourceType rt : dailyConsumption.keySet()){
                resources.put(rt, resources.getOrDefault(rt, 0) - dailyConsumption.get(rt));
            }
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
    public Map<ResourceType, Integer> getDailyProduction() {
        return b.getDailyProduction();
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
