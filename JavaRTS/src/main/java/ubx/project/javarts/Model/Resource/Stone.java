package ubx.project.javarts.Model.Resource;

public class Stone extends AbstractResource {
    public Stone(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.STONE;
    }
}
