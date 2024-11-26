package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.Resource;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public abstract class BuildingDecorator implements Building {
    private final ArrayList<BuildingFunction> functions = new ArrayList<BuildingFunction>();
    private final UUID id = new UUID(0, 0);
    private Map<ResourceType, Integer> cost;
    private Size size;
    private Position position;
    private BuildingType type;

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public Map<ResourceType, Integer> getCost() {
        return cost;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void getConstructionTime() {

    }

    @Override
    public BuildingType getType() {
        return type;
    }

    @Override
    public Position getPostion() {
        return position;
    }
    @Override
    public boolean equals(Object obj) {
        //TODO: implement correctly
        return super.equals(obj);
    }

    public ArrayList<BuildingFunction> getFunctions() {
        return functions;
    }

    public void addFunction(BuildingFunction function){
        functions.add(function);
    }
}
