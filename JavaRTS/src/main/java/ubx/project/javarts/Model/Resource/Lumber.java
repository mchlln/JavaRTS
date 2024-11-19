package ubx.project.javarts.Model.Resource;

public class Lumber extends ResourceDecorator{
    public Lumber(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.LUMBER;
    }
}
