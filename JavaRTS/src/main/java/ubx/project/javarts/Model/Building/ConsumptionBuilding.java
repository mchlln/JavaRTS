package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.Map;

public class ConsumptionBuilding extends BuildingDecorator{
    private Map<ResourceType, Integer> dailyConsumption;

    public ConsumptionBuilding(Building b, Map<ResourceType, Integer> dailyConsumption) {
        super(b);
        this.dailyConsumption = dailyConsumption;
    }

    public Map<ResourceType, Integer> getDailyConsumption() {
        return dailyConsumption;
    }

    @Override
    public void addFunction(BuildingFunction function){
        super.addFunction(BuildingFunction.CONSUMING);
    }
}
