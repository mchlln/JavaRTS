package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    public int getMaxInhabitants() {
        return b.getMaxInhabitants();
    }
}
