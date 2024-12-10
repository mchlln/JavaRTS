package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Exception.TooManyInhabitants;
import ubx.project.javarts.Exception.WrongState;
import ubx.project.javarts.Model.Building.State.States;
import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.*;

public class LivingBuilding extends BuildingDecorator{
    private final List<People> inhabitants = new ArrayList<>();
    private int maxInhabitants;
    Building b;

    public LivingBuilding(Building b, int maxInhabitants) {
        super(b);
        this.maxInhabitants = maxInhabitants;
        this.b = b;
    }

    @Override
    public List<People> getInhabitants() {
        return inhabitants;
    }

    @Override
    public int getMaxInhabitants() {
        return maxInhabitants;
    }
    @Override
    public int getNumberInhabitants(){
        return inhabitants.size();
    }
    @Override
    public void addInhabitant(People people){
        if (b.getState() == States.CREATION) throw new WrongState("You can't add an inhabitant to a building in construction.");
        if(getNumberInhabitants()<getMaxInhabitants()){
            inhabitants.add(people);
        }else{
            throw new TooManyInhabitants("Too many inhabitants in the building");
        }
    }
    @Override
    public void removeInhabitant(People people){
        if (b.getState() == States.CREATION) throw new WrongState("You can't remove an inhabitant to a building in construction.");
        if(getNumberInhabitants()>0){
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

    @Override
    public HashMap<ResourceType, Integer> handle(){
        return b.handle();
    }
}
