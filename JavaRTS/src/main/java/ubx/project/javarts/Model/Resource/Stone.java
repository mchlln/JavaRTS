package ubx.project.javarts.Model.Resource;

public class Stone extends ResourceDecorator{
    public Stone(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.STONE;
    }
}
