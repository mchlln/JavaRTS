package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Resource.ResourceType;

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
    public void addFunction(BuildingFunction function){
        super.addFunction(BuildingFunction.PRODUCING);
    }

}
