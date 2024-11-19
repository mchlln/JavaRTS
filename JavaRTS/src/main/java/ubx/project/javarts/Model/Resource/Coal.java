package ubx.project.javarts.Model.Resource;

public class Coal extends ResourceDecorator{
    public Coal(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.COAL;
    }
}
