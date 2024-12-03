package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductionBuilding extends BuildingDecorator{
    private final Map<ResourceType, Integer> dailyProduction;

    public ProductionBuilding(Building b, Map<ResourceType, Integer> dailyProduction) {
        super(b);
        this.dailyProduction = dailyProduction;
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
        HashMap<ResourceType,Integer> resources = super.handle();
        for(ResourceType rt : dailyProduction.keySet()){
            resources.put(rt, resources.getOrDefault(rt, 0) + dailyProduction.get(rt));
        }
        return resources;
    }
}
