package ubx.project.javarts.Model.Resource;

import ubx.project.javarts.Exception.NotEnoughResources;
import ubx.project.javarts.Model.GameManager;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

public class ResourceManager {
    private static Map<ResourceType, AbstractResource> resources;
    private static ResourceManager instance = null;

    private ResourceManager() {
        resources = new HashMap<>();
        resources.put(ResourceType.WOOD, new Wood(100));
        resources.put(ResourceType.IRON, new Iron(0));
        resources.put(ResourceType.STONE, new Stone(100));
        resources.put(ResourceType.COAL, new Coal(0));
        resources.put(ResourceType.STEEL, new Steel(0));
        resources.put(ResourceType.CEMENT, new Cement(0));
        resources.put(ResourceType.LUMBER, new Lumber(0));
        resources.put(ResourceType.FOOD, new Food(25));
        resources.put(ResourceType.TOOLS, new Tools(5));
    }

    public static void addResource(ResourceType type, int quantity) {
        if (resources.containsKey(type)) {
            resources.get(type).addResources(quantity);
            if(resources.get(type).getQuantity() <0 ){
                if(type.equals(ResourceType.FOOD)){
                    //kill people when you don't have enough food
                    int killCount = abs(resources.get(type).getQuantity());
                    GameManager.getInstance().killPeople(killCount);
                    resources.get(type).addResources(killCount);
                    throw new NotEnoughResources("Not Enough food, killed " + killCount + " people");
                }else{
                    //for now just warn user that you are in negative
                    throw new NotEnoughResources("Pay attention: not enough " +type+ ", value negative");
                }

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

    public static Map<ResourceType, AbstractResource> getResources() {
        return resources;
    }
}