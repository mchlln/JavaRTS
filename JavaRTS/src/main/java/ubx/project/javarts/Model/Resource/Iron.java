package ubx.project.javarts.Model.Resource;

public class Iron extends AbstractResource {
    public Iron(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.IRON;
    }
}
