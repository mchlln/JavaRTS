package ubx.project.javarts.Model;

import ubx.project.javarts.Exception.NotEnoughResources;
import ubx.project.javarts.Exception.WrongBuildingType;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingBuilder;
import ubx.project.javarts.Model.Building.BuildingManager;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.Resource.ResourceManager;
import ubx.project.javarts.View.Observer;

import java.util.HashSet;
import java.util.Set;

public class GameManager implements Subject {
    private static GameManager instance;
    private BuildingManager buildings = new BuildingManager();
    private Set<People> worldInhabitants;
    private Map map;
    private ResourceManager resources;
    private Set<Observer> observers;

    private GameManager() {
        resources = ResourceManager.getInstance();
        worldInhabitants = new HashSet<>();
        observers = new HashSet<>();
        map = Map.getInstance();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void addBuilding(BuildingType type, Position position) { // TODO: review + refactor
        if (type == null){
            errorNotif(new WrongBuildingType("No building type selected."));
            return;
        }
        BuildingBuilder b = new BuildingBuilder(); // TODO: Don't index each times
        Building building = b.build(type, position);
        System.out.println(building.getType());
        if (map.isAreaFree(position, building.getSize())){
            try{
                System.out.println("Adding building " + type + " to position " + position);
                buildings.addBuilding(building);
                map.construct(building.getPostion(), building.getSize());
            }catch (NotEnoughResources e){
                errorNotif(e);
            }

            // Add
        }
        notifyObservers();
    }

    public void removeBuilding(Building building) {
        if (!buildings.exists(building)){
            throw new RuntimeException("cannot remove building " + building);
        }
        map.destruct(building.getPostion(), building.getSize());
        buildings.removeBuilding(building);
        notifyObservers();
    }

    public void createInhabitantInto(Building building) {
        if (!buildings.exists(building)){
            return;
        }
        People people = new People();
        worldInhabitants.add(people);
        //TODO: add world inhabitant to the given building
        people.affectHouse(building);
    }

    public void deleteInhabitantFrom(Building building, People people) {
        if (!buildings.exists(building) || !worldInhabitants.contains(people)){
            return;
        }
        building.removeInhabitant(people);
        worldInhabitants.remove(people);
    }

    public void assignWorkerTo(Building building, People people) {
        if (building.getMaxWorkers() < building.getWorkers().size()){
            building.addWorker(people);
        }
    }

    public Set<Building> getBuildings() {
        return buildings.getBuildings();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public void errorNotif(Exception e) {
        for(Observer o : observers){
            o.updateError(e);
        }
    }
}
