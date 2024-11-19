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

    }

    public void removeBuilding(Building building) {

    }

    public void createInhabitantsInto(Building building, int number) {

    }

    public void deleteInhabitantsFrom(Building building, int number) {

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
