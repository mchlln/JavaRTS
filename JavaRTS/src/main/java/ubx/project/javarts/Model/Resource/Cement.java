package ubx.project.javarts.Model.Resource;

public class Cement extends AbstractResource {
    public Cement(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.CEMENT;
    }
}
