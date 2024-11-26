package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.Map;
import java.util.UUID;

public interface Building {
    Size getSize();
    Map<ResourceType, Integer> getCost();
    UUID getId();
    void getConstructionTime(); //TODO: find return type
    BuildingType getType();
    Position getPostion();
}
