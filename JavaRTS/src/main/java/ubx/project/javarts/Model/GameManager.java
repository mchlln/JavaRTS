package ubx.project.javarts.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import ubx.project.javarts.Exception.*;
import ubx.project.javarts.Model.Building.*;
import ubx.project.javarts.Model.Building.State.States;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Model from the Design pattern Model View Controller (MVC).
 * Implemented following the Design pattern Singleton
 */
public class GameManager implements Subject {
    private static GameManager instance;
    private BuildingManager buildings = new BuildingManager();
    private List<People> worldInhabitants;
    private Map map;
    private Set<Runnable> listener;
    public Exception currentException;
    private Runnable errorListener;
    private Timeline timeline;
    private final BuildingBuilder b = new BuildingBuilder();

    /**
     * private constructor (design pattern singleton)
     */
    private GameManager() {
        worldInhabitants = new ArrayList<>();
        listener = new HashSet<>();
        map = Map.getInstance();
        loop();
    }

    /**
     * GameLoop
     * Creates a {@link Timeline} that calls the method Handle from the building
     * manager to execute all
     * actions from each building of the map.
     * We notify the observers to update the view.
     * If we don't have enough resources, we notify the error listener to be able to
     * show an error on the map.
     *
     */
    private void loop() {
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            try {
                System.out.println("World Inhabitants = " + worldInhabitants.size());
                buildings.handle();
                notifyListener();
            } catch (NotEnoughResources ex) {
                notifyErrorListener(ex);
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.play(); // Start the timeline
    }

    /**
     * Following the design pattern singleton, creates an instance if there are none
     * or return the existing one if it has been created previously.
     *
     * @return instance of {@link GameManager} needed to do operations on the model
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Adds a new building of the specified type to the map at the given position.
     *
     * This method performs the following:
     * - Validates the building type. If the type is `null`, it notifies an error
     * listener with a {@link WrongBuildingType} exception.
     * - Creates a new building instance using the specified type and position.
     * - Checks if the specified area on the map is free to place the building.
     * - If the area is free, attempts to add the building to the list of buildings
     * and marks the area as occupied on the map.
     * - If there are insufficient resources to construct the building, notifies an
     * error listener with a {@link NotEnoughResources} exception.
     * - Notifies all observers after attempting to add the building, regardless of
     * the outcome.
     *
     * @param type     the {@link BuildingType} of the building to add
     * @param position the {@link Position} where the building should be placed
     */

    public void addBuilding(BuildingType type, Position position) {
        if (type == null) {
            notifyErrorListener(new WrongBuildingType("No building type selected."));
            return;
        }
        Building building = b.build(type, position);
        System.out.println(building.getType());
        if (map.isAreaFree(position, building.getSize())) {
            try {
                System.out.println("Adding building " + type + " to position " + position);
                buildings.addBuilding(building);
                map.construct(building.getPosition(), building.getSize());
            } catch (NotEnoughResources e) {
                notifyErrorListener(e);
            }
        }
        notifyListener();
    }

    /**
     * Remove a building of the specified type to the map at the given position.
     *
     * The method performs the following :
     * - If the building doesn't exist it throws a {@link RuntimeException}
     * - If it exists :
     * - Removes it from the map
     * - Removes it from the {@link BuildingManager}
     * - Notifies the observers
     *
     * @param building the {@link Building} to remove
     */
    public void removeBuilding(Building building) {
        if (!buildings.exists(building)) {
            throw new RuntimeException("cannot remove building " + building);
        }
        map.destruct(building.getPosition(), building.getSize());
        buildings.removeBuilding(building);
        while (!building.getInhabitants().isEmpty()) {
            deleteInhabitantFrom(building);
        }
        while (!building.getWorkers().isEmpty()) {
            deleteWorkerFrom(building);
        }
        notifyListener();
    }

    /**
     * Checks whether the building exists, if not notifies the error Listener
     * If the building is not in the right state, it will notify the error listener
     * that
     * we are in the wrong state.
     * If the conditions are right, it tries to repair the building by calling the
     * {@link BuildingManager}
     * Notifies the error listener if we don't have enough resources.
     *
     * @param building the {@link Building} to repair
     */
    public void repairBuilding(Building building) {
        if (!buildings.exists(building)) {
            notifyErrorListener(new WrongBuildingType("The building doesn't exist"));
            return;
        }
        if (building.getState() != States.BROKEN) {
            notifyErrorListener(new WrongState("No need to repair this building."));
            return;
        }
        try {
            buildings.repairBuilding(building);
            notifyListener();
        } catch (NotEnoughResources e) {
            notifyErrorListener(e);
        }
    }

    /**
     * Checks whether the building exists, if not notifies the error Listener
     * If the building is not in the right state, it will notify the error listener
     * that
     * we are in the wrong state.
     * If the conditions are right, it tries to boost the building by calling the
     * {@link BuildingManager}
     * Notifies the error listener if we don't have enough resources.
     *
     * @param building the {@link Building} to boost
     */
    public void boostBuilding(Building building) {
        if (!buildings.exists(building)) {
            notifyErrorListener(new WrongBuildingType("The building doesn't exist"));
            return;
        }
        if (building.getState() != States.RUNNING) {
            notifyErrorListener(new WrongState("Cannot boost a building in state" + building.getState()));
            return;
        }
        try {
            buildings.boostBuilding(building);
            notifyListener();
        } catch (NotEnoughResources e) {
            notifyErrorListener(e);
        }
    }

    /**
     * Checks whether the building exists, if not notifies the error Listener
     * If the building is not in the right state, it will notify the error listener
     * that
     * we are in the wrong state.
     * If the conditions are right, it tries to block the building by calling the
     * {@link BuildingManager}
     * Notifies the error listener if we don't have enough resources.
     *
     * @param building the {@link Building} to block
     */
    public void blockBuilding(Building building) {
        if (!buildings.exists(building)) {
            notifyErrorListener(new WrongBuildingType("The building doesn't exist"));
        }
        if (building.getState() != States.RUNNING && building.getState() != States.BLOCKED) {
            notifyErrorListener(new WrongState("Cannot block a building in state" + building.getState()));
        }
        try {
            buildings.blockBuilding(building);
            notifyListener();
        } catch (NotEnoughResources e) {
            notifyErrorListener(e);
        }
    }

    /**
     * Checks whether the building exists, if not notifies the error Listener
     * If the building is not in the right state, it will notify the error listener
     * that
     * we are in the wrong state.
     * If the conditions are right, it tries to repair the building by calling the
     * {@link BuildingManager}
     * Notifies the error listener if we don't have enough resources.
     *
     * @param building the {@link Building} to put in running {@link State}
     */
    public void runBuilding(Building building) {
        if (!buildings.exists(building)) {
            notifyErrorListener(new WrongBuildingType("The building doesn't exist"));
        }
        try {
            buildings.runBuilding(building);
            notifyListener();
        } catch (NotEnoughResources e) {
            notifyErrorListener(e);
        }
    }

    /**
     * Create an inhabitant and adds it to the building.
     *
     * If the building doesn't exist : it returns.
     * It tries to create a {@link People} and calls the {@link BuildingManager} to
     * add him in the building.
     * It gets added to the list of WorldInhabitants and then notify the observers.
     *
     * If too many inhabitants are in the building or the building is in a wrong
     * state, it notifies the error listeners.
     *
     * @param building the {@link Building} to add an inhabitant into
     */
    public void createInhabitantInto(Building building) {
        if (!buildings.exists(building)) {
            notifyErrorListener(new WrongBuildingType("The building doesn't exist"));
        }
        People people = new People();
        try {
            worldInhabitants.add(people);
            buildings.addInhabitantInto(building, people);
            notifyListener();
        } catch (TooManyInhabitants | WrongState e) {
            worldInhabitants.remove(people); // NEED TO CHECK WORLD INHABITANT LENGTH
            notifyErrorListener(e);
        }

    }

    /**
     * Finds the first inhabitant of the building and delete him.
     *
     * If the building doesn't exist or doesn't have inhabitants, it notifies the
     * error listener.
     *
     * It finds the first inhabitant in the list of the building, removes him from
     * its job and his house.
     * It notifies the observers
     *
     * @param building the {@link Building} to remove an inhabitant from
     */
    public void deleteInhabitantFrom(Building building) {
        if (!buildings.exists(building)) {
            notifyErrorListener(new WrongBuildingType("The building doesn't exist"));
        }
        try {
            if (building.getInhabitants().isEmpty()) {
                throw new NotEnoughInhabitants("There are no inhabitants");
            }
            People people = building.getInhabitants().getFirst();
            if (people.getJobPlace() != null) {
                // people.getJobPlace().removeWorker(people);
                people.affectJobPlace(null);
                building.removeWorker(people);
            }
            building.removeInhabitant(people);
            people.affectHouse(null);
            worldInhabitants.remove(people);
            notifyListener();
        } catch (NotEnoughInhabitants | WrongBuildingType e) {
            notifyErrorListener(e);
        }
    }

    /**
     * Tries to find an unemplyed person in towns, add it to the list of workers of
     * the building, and assign the job place to the worker. Notifies the observer.
     * Sends to the error listener a {@link TooManyWorkers} or
     * {@link NotEnoughInhabitants} if there is
     * no space for a new worker in the building or if no inhabitant is enemployed.
     *
     * @param building the {@link Building} to add a worker into
     */
    public void assignWorkerTo(Building building) {
        try {
            People worker = findUnemployed();
            if (building.getMaxWorkers() > building.getNumberWorkers()) {
                building.addWorker(worker);
                worker.affectJobPlace(building);
                notifyListener();
            }
        } catch (TooManyWorkers | NotEnoughInhabitants | WrongBuildingType e) {
            notifyErrorListener(e);
        }

    }

    /**
     * Finds a person without a job in the world inhabitants.
     * If there are none, throws a {@link NotEnoughInhabitant} exception.
     * 
     * @return {@link People} without a job yet
     */
    public People findUnemployed() {
        for (People people : worldInhabitants) {
            if (people.getJobPlace() == null) {
                return people;
            }
        }
        throw new NotEnoughInhabitants("No unemployed person in town.");
    }

    /**
     * Finds the first worker of the building and delete him.
     *
     * If the building doesn't exist or doesn't have worker, it notifies the error
     * listener.
     *
     * It finds the first worker in the list of the building, removes him from its
     * job.
     * It notifies the observers
     * 
     * @param building the {@link Building} to remove an worker from
     */
    public void deleteWorkerFrom(Building building) {
        if (!buildings.exists(building)) {
            notifyErrorListener(new WrongBuildingType("The building doesn't exist"));
        }
        try {
            if (building.getWorkers().isEmpty()) {
                throw new NotEnoughWorkers("There are no workers");
            }
            People people = building.getWorkers().getFirst();
            people.getJobPlace().removeWorker(people);
            people.affectJobPlace(null);
            building.removeWorker(people);
            notifyListener();
        } catch (NotEnoughWorkers | WrongBuildingType e) {
            notifyErrorListener(e);
        }

    }

    /**
     * Remove a given number of people
     * Used when you don't have enough food to feed everyone
     *
     * @param count number of {@link People} to remove from the game
     */
    public void killPeople(int count) {
        if(worldInhabitants.size() < count) {
            count = worldInhabitants.size();
        }
        while (count != 0) {
            for (Building b : getBuildings()) {
                if (b.getFunctions().contains(BuildingFunction.LIVING) && b.getNumberInhabitants() != 0) {
                    deleteInhabitantFrom(b);
                    count--;
                }
            }
        }
    }

    /**
     * Retrieves the set of buildings currently managed by the system.
     *
     * @return a {@link Set} of {@link Building} objects representing all buildings.
     */
    public Set<Building> getBuildings() {
        return buildings.getBuildings();
    }

    /**
     * Adds the observer to the list
     *
     * @param o {@link Runnable} to add to the list of observers
     */
    @Override
    public void addListener(Runnable o) {
        listener.add(o);
    }

    /**
     * Assign the error listener field
     *
     * @param o {@link Runnable}
     */
    @Override
    public void addErrorListener(Runnable o) {
        errorListener = o;
    }

    /**
     * Notifies all the observers from the list
     */
    @Override
    public void notifyListener() {
        for (Runnable o : listener) {
            Platform.runLater(o);
        }
    }

    /**
     * Notifies the error listener with the provided exception.
     * The exception is stored in the {@code currentException} field, and if an
     * error listener is set,
     * it is executed on the JavaFX Application thread.
     *
     * @param e the {@link Exception} to be sent to the error listener.
     */
    @Override
    public void notifyErrorListener(Exception e) {
        currentException = e;
        if (errorListener != null) {
            Platform.runLater(errorListener);
        }
    }

}
