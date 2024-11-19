package ubx.project.javarts.Model.Resource;

public class Food extends ResourceDecorator{
    public Food(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.FOOD;
    }
}
