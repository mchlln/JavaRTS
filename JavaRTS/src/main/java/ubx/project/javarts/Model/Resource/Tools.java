package ubx.project.javarts.Model.Resource;

public class Tools extends ResourceDecorator{
    public Tools(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.TOOLS;
    }
}
