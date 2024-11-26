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

    public Set<People> getInhabitants() {
        return inhabitants;
    }

    public int getMaxInhabitants() {
        return maxInhabitants;
    }

    public int getMinInhabitants() {
        return minInhabitants;
    }

    public int getNumberInhabitants(){
        return inhabitants.size();
    }

    public void addInhabitant(People people){
        if(getNumberInhabitants()<getMaxInhabitants()){
            inhabitants.add(people);
        }
    }

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
