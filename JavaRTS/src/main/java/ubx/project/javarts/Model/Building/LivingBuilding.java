package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.*;

public class LivingBuilding extends BuildingDecorator{
    private Set<People> inhabitants = new HashSet<>();
    private int maxInhabitants;
    private int minInhabitants;
    Building b;

    public LivingBuilding(Building b, int maxInhabitants, int minInhabitants) {
        super(b);
        this.maxInhabitants = maxInhabitants;
        this.minInhabitants = minInhabitants;
        this.b = b;
    }

    @Override
    public Set<People> getInhabitants() {
        return inhabitants;
    }

    @Override
    public int getMaxInhabitants() {
        return maxInhabitants;
    }
    @Override
    public int getMinInhabitants() {
        return minInhabitants;
    }
    @Override
    public int getNumberInhabitants(){
        return inhabitants.size();
    }
    @Override
    public void addInhabitant(People people){
        if(getNumberInhabitants()<getMaxInhabitants()){
            inhabitants.add(people);
        }
    }
    @Override
    public void removeInhabitant(People people){
        if(getNumberInhabitants()>getMinInhabitants()){
            inhabitants.remove(people);
        }
    }

    @Override
    public void addFunction(ArrayList<BuildingFunction> functions){
        functions.add(BuildingFunction.LIVING);
        super.addFunction(functions);
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
    public Set<People> getWorkers() {
        return b.getWorkers();
    }
    @Override
    public int getMaxWorkers() {
        return b.getMaxWorkers();
    }
    @Override
    public int getMinWorkers() {
        return b.getMinWorkers();
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

    @Override
    public HashMap<ResourceType, Integer> handle(){
        return b.handle();
    }
}
