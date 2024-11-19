package ubx.project.javarts.Model.Resource;

public class Cement extends ResourceDecorator{
    public Cement(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.CEMENT;
    }
}
