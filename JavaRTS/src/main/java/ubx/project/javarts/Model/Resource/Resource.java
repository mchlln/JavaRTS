package ubx.project.javarts.Model.Resource;

public interface Resource {
    ResourceType getType();
    int getQuantity();
    void addResources(int quantity);
    void removeResources(int quantity);
}
