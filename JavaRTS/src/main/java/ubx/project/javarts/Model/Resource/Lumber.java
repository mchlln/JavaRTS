package ubx.project.javarts.Model.Resource;

public class Lumber extends AbstractResource {
    public Lumber(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.LUMBER;
    }
}
