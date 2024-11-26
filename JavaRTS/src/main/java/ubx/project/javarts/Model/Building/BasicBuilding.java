package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.Resource;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.Map;
import java.util.UUID;

public class BasicBuilding implements Building{
    private UUID id = new UUID(0, 0);
    private Map<ResourceType, Integer> cost;
    private Size size;
    private Position position;
    private BuildingType type;

    @Override
    public Size getSize() {
        return null;
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
}
