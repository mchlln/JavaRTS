package ubx.project.javarts.Model.Resource;

public class Tools extends AbstractResource {
    public Tools(int quantity) {
        super(quantity);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.TOOLS;
    }
}
