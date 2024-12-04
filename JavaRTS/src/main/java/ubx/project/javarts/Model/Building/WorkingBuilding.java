package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Exception.TooManyWorkers;
import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.*;

public class WorkingBuilding extends BuildingDecorator{
    private Set<People> workers;
    private final int maxWorkers;
    private final int minWorkers;
    Building b;

    public WorkingBuilding(Building b, int maxWorkers, int minWorkers) {
        super(b);
        workers = new HashSet<>();
        this.maxWorkers = maxWorkers;
        this.minWorkers = minWorkers;
        this.b = b;
    }

    @Override
    public Set<People> getWorkers() {
        return workers;
    }
    @Override
    public int getMaxWorkers() {
        return maxWorkers;
    }
    @Override
    public int getMinWorkers() {
        return minWorkers;
    }
    @Override
    public int getNumberWorkers(){
        return workers.size();
    }
    @Override
    public void addWorker(People people){
        if(getNumberWorkers()<maxWorkers){
            workers.add(people);
        }else{
            throw new TooManyWorkers("Too many workers in building.");
        }
    }
    @Override
    public void removeWorker(People people){
        if(getNumberWorkers()>getMinWorkers()){
            workers.remove(people);
        }
    }

    @Override
    public void addFunction(ArrayList<BuildingFunction> functions){
        functions.add(BuildingFunction.WORKING);
        super.addFunction(functions);
    }

    @Override
    public Set<People> getInhabitants() {
        return b.getInhabitants();
    }

    @Override
    public int getMaxInhabitants() {
        return b.getMaxInhabitants();
    }

    @Override
    public int getMinInhabitants() {
        return b.getMinInhabitants();
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
    public Map<ResourceType, Integer> getDailyProduction() {
        return b.getDailyProduction();
    }

    @Override
    public HashMap<ResourceType, Integer> handle(){
        return b.handle();
    }

}
