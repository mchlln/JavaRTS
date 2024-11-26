package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;

import java.util.HashSet;
import java.util.Set;

public class WorkingBuilding extends BuildingDecorator{
    private Set<People> workers;
    private final int maxWorkers;
    private final int minWorkers;

    public WorkingBuilding(Building b, int maxWorkers, int minWorkers) {
        super(b);
        workers = new HashSet<>();
        this.maxWorkers = maxWorkers;
        this.minWorkers = minWorkers;
    }


    public Set<People> getWorkers() {
        return workers;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }
    public int getMinWorkers() {
        return minWorkers;
    }

    public int getNumberWorkers(){
        return workers.size();
    }

    public void addWorker(People people){
        if(getNumberWorkers()<maxWorkers){
            workers.add(people);
        }
    }

    public void removeWorker(People people){
        if(getNumberWorkers()>getMinWorkers()){
            workers.remove(people);
        }
    }

    @Override
    public void addFunction(BuildingFunction function){
        super.addFunction(BuildingFunction.WORKING);
    }
}
