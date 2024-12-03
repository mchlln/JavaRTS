package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class ConsumptionBuilding extends BuildingDecorator{
    private Map<ResourceType, Integer> dailyConsumption;

    public ConsumptionBuilding(Building b, Map<ResourceType, Integer> dailyConsumption) {
        super(b);
        this.dailyConsumption = dailyConsumption;
    }

    @Override
    public Map<ResourceType, Integer> getDailyConsumption() {
        return dailyConsumption;
    }

    @Override
    public void addFunction(BuildingFunction function){
        super.addFunction(BuildingFunction.CONSUMING);
    }

    @Override
    public HashMap<ResourceType, Integer> handle(){
        HashMap<ResourceType,Integer> resources = super.handle();
        for(ResourceType rt : dailyConsumption.keySet()){
            resources.put(rt, resources.getOrDefault(rt, 0) - dailyConsumption.get(rt));
        }
        return resources;
    }


}
