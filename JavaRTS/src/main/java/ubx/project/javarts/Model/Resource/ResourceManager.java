package ubx.project.javarts.Model.Resource;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private static Map<ResourceType,ResourceDecorator> resources;
    private static ResourceManager instance = null;

    private ResourceManager() {
        resources = new HashMap<>();
        resources.put(ResourceType.IRON, new Iron(0));
        resources.put(ResourceType.STONE, new Iron(0));
        resources.put(ResourceType.COAL, new Coal(0));
        resources.put(ResourceType.STEEL, new Steel(0));
        resources.put(ResourceType.CEMENT, new Cement(0));
        resources.put(ResourceType.LUMBER, new Lumber(0));
        resources.put(ResourceType.FOOD, new Food(0));
        resources.put(ResourceType.TOOLS, new Tools(0));
    }

    public void addResource(ResourceType type, int quantity) {
        if (resources.containsKey(type)) {
            resources.get(type).addResources(quantity);
        }
    }

    public static void removeResource(ResourceType type, int quantity) {
        if (resources.containsKey(type)) {
            resources.get(type).removeResources(quantity);
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
}