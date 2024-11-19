package ubx.project.javarts.Model.Resource;

public class Wood extends ResourceDecorator{
    public Wood(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.WOOD;
    }
}
