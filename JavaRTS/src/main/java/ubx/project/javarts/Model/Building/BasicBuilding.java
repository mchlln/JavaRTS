package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.Resource;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class BasicBuilding implements Building{
    private final UUID id;
    private Map<ResourceType, Integer> cost;
    private final Size size;
    private final Position position;
    private BuildingType type;
    private final ArrayList<BuildingFunction> functions = new ArrayList<BuildingFunction>();

    public BasicBuilding(Position pos, Size s){
        this.id = UUID.randomUUID();
        this.position = pos;
        this.size = s;
    }
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

    public ArrayList<BuildingFunction> getFunctions() {
        return functions;
    }

    public void addFunction(BuildingFunction function){
        functions.add(function);
    }
}
