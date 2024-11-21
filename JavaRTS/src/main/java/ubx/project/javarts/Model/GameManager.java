package ubx.project.javarts.Model;

import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Resource.ResourceManager;
import ubx.project.javarts.View.Observer;

import java.util.HashSet;
import java.util.Set;

public class GameManager implements Subject {
    private static GameManager instance;
    private Set<Building> buildings;
    private Set<People> worldInhabitants;
    private Map map;
    private ResourceManager resources;
    private Set<Observer> observers;

    private GameManager() {
        resources = ResourceManager.getInstance();
        buildings = new HashSet<>();
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
            buildings.add(building);
        }
    }

    public void removeBuilding(Building building) {
        if (!buildings.contains(building)){
            return;
        }
        map.Destruct(new Position(0,0), building.getSize()); //TODO: Replace Position(0,0) by building.getPosition() when implemented
        buildings.remove(building);
    }

    public void createInhabitantInto(Building building) {
        if (!buildings.contains(building)){
            return;
        }
        People people = new People();
        worldInhabitants.add(people);
        //TODO: add world inhabitant to the given building
        people.affectHouse(building);
    }

    public void deleteInhabitantFrom(Building building) {
        if (!buildings.contains(building)){
            return;
        }
        Set<People> inhabitants = building.getInhabitants();
        if (inhabitants.iterator().hasNext()){
            People inhabitant = inhabitants.iterator().next();
            building.removeInhabitant(inhabitant);

        }


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
