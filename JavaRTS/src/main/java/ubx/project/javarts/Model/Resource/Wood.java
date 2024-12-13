package ubx.project.javarts.Model.Resource;

public class Wood extends AbstractResource {
    public Wood(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.WOOD;
    }
}
