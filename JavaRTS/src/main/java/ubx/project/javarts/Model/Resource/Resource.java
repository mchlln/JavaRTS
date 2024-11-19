package ubx.project.javarts.Model.Resource;

public interface Resource {
    ResourceType getType();
    int getQuantity();
    boolean addResources(int quantity);
    boolean removeResources(int quantity);
}
