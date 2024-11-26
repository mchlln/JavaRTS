package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.Map;

public class ProductionBuilding extends BuildingDecorator{
    private Map<ResourceType, Integer> dailyProduction;

    public Map<ResourceType, Integer> getDailyProduction() {
        return dailyProduction;
    }

    @Override
    public void addFunction(BuildingFunction function){
        super.addFunction(BuildingFunction.PRODUCING);
    }

}
