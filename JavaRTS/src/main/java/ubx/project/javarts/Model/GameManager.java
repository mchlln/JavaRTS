package ubx.project.javarts.Model;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingManager;
import ubx.project.javarts.Model.Resource.ResourceManager;
import ubx.project.javarts.View.Observer;

import java.util.HashSet;
import java.util.Set;

public class GameManager implements Subject {
    private static GameManager instance;
    private BuildingManager buildings;
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

    public void addBuilding(Building building, Position position) {
        if (map.isAreaFree(position, building.getSize())){
            buildings.addBuilding(building);
            // Add
        }
    }

    public void removeBuilding(Building building) {
        if (!buildings.exists(building)){
            return;
        }
        map.destruct(new Position(0,0), building.getSize()); //TODO: Replace Position(0,0) by building.getPosition() when implemented
        buildings.removeBuilding(building);
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

    public void deleteInhabitantFrom(Building building) {
        if (!buildings.exists(building)){
            return;
        }
        //building.removeInhabitant();


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
}
