package ubx.project.javarts.Model.Resource;

public class Steel extends AbstractResource {
    public Steel(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.STEEL;
    }
}
