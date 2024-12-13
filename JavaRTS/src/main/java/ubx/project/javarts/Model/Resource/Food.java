package ubx.project.javarts.Model.Resource;

public class Food extends AbstractResource {
    public Food(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.FOOD;
    }
}
