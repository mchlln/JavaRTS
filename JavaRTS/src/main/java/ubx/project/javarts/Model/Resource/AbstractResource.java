package ubx.project.javarts.Model.Resource;

import ubx.project.javarts.Exception.NotEnoughResources;

import java.util.HashMap;

public abstract class AbstractResource implements Resource {
    private int quantity;
    public AbstractResource(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public void addResources(int quantity){
        this.quantity += quantity;
    }
    public void removeResources(int quantity){
        if (this.quantity < quantity) {
            throw new NotEnoughResources("Quantity must be less than or equal to quantity");
        }
        this.quantity -= quantity;
    }

    public HashMap<ResourceType,Integer> handle(){
        return new HashMap<>();
    }

}
