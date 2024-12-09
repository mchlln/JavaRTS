package ubx.project.javarts.Model.Resource;

import ubx.project.javarts.Exception.NotEnoughResources;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private static Map<ResourceType,ResourceDecorator> resources;
    private static ResourceManager instance = null;

    private ResourceManager() {
        resources = new HashMap<>();
        resources.put(ResourceType.WOOD, new Wood(30));
        resources.put(ResourceType.IRON, new Iron(0));
        resources.put(ResourceType.STONE, new Stone(30));
        resources.put(ResourceType.COAL, new Coal(0));
        resources.put(ResourceType.STEEL, new Steel(0));
        resources.put(ResourceType.CEMENT, new Cement(0));
        resources.put(ResourceType.LUMBER, new Lumber(0));
        resources.put(ResourceType.FOOD, new Food(25));
        resources.put(ResourceType.TOOLS, new Tools(0));
    }

    public static void addResource(ResourceType type, int quantity) {
        if (resources.containsKey(type)) {
            resources.get(type).addResources(quantity);
            if(resources.get(type).getQuantity() <0){
                throw new NotEnoughResources("Not enough "+ type +" to live another day");
            }
        }
    }

    public static void removeResource(ResourceType type, int quantity) {
        if (resources.containsKey(type)) {
            if(resources.get(type).getQuantity() >= quantity){
                resources.get(type).removeResources(quantity);
            }else{
                throw new NotEnoughResources("Not enough "+ type +" to live another day");

            }

        }
    }

    public static ResourceManager getInstance(){
        if (instance == null){
            instance = new ResourceManager();
        }
        return instance;
    }

    public static boolean areAvailable(HashMap<ResourceType, Integer> neededResources) {
        for (ResourceType type : neededResources.keySet()) {
            if (resources.get(type).getQuantity() < neededResources.get(type)) {
                return false;
            }
        }
        return true;
    }

    public static Map<ResourceType, ResourceDecorator> getResources() {
        return resources;
    }
}