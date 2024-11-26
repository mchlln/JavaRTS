package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;

import java.util.HashSet;
import java.util.Set;

public class LivingBuilding extends BuildingDecorator{
    private Set<People> inhabitants = new HashSet<>();
    private int maxInhabitants;
    private int minInhabitants;

    public LivingBuilding(Building b, int maxInhabitants, int minInhabitants) {
        super(b);
        this.maxInhabitants = maxInhabitants;
        this.minInhabitants = minInhabitants;
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
    public void addFunction(BuildingFunction function){
        super.addFunction(BuildingFunction.LIVING);
    }
}
