package ubx.project.javarts.Model.Building;


import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public abstract class BuildingDecorator implements Building {
   private final Building decoratedBuilding;

    public BuildingDecorator(Building b) {
        this.decoratedBuilding = b;
    }

    @Override
    public Size getSize() {
        return decoratedBuilding.getSize();
    }

    @Override
    public Map<ResourceType, Integer> getCost() {
        return decoratedBuilding.getCost();
    }

    @Override
    public UUID getId() {
        return decoratedBuilding.getId();
    }

    @Override
    public void getConstructionTime() {

    }

    @Override
    public BuildingType getType() {
        return decoratedBuilding.getType();
    }

    @Override
    public Position getPostion() {
        return decoratedBuilding.getPostion();
    }
    @Override
    public boolean equals(Object obj) {
        //TODO: implement correctly
        return super.equals(obj);
    }

    @Override
    public ArrayList<BuildingFunction> getFunctions() {
        return decoratedBuilding.getFunctions();
    }

    @Override
    public void addFunction(BuildingFunction function){
        decoratedBuilding.addFunction(function);
    }
}
