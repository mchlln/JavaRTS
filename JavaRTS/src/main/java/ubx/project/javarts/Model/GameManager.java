package ubx.project.javarts.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import ubx.project.javarts.Exception.*;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingBuilder;
import ubx.project.javarts.Model.Building.BuildingManager;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.Building.State.States;
import ubx.project.javarts.Model.Resource.ResourceManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameManager implements Subject {
    private static GameManager instance;
    private BuildingManager buildings = new BuildingManager();
    private List<People> worldInhabitants;
    private Map map;
    private ResourceManager resources;
    private Set<Runnable> observers;
    public Exception currentException;
    private Runnable errorListener;
    private Timeline timeline;
    private final BuildingBuilder b = new BuildingBuilder();

    private GameManager() {
        resources = ResourceManager.getInstance();
        worldInhabitants = new ArrayList<>();
        observers = new HashSet<>();
        map = Map.getInstance();
        loop();
    }

    private void loop(){
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            try{
                System.out.println("World Inhabitants = " + worldInhabitants.size());
                buildings.handle();
                notifyObservers();
            } catch (NotEnoughResources ex) {
                notifyErrorListener(ex);
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.play(); // Start the timeline
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void addBuilding(BuildingType type, Position position) { // TODO: review + refactor
        if (type == null){
            notifyErrorListener(new WrongBuildingType("No building type selected."));
            return;
        }
        Building building = b.build(type, position);
        System.out.println(building.getType());
        if (map.isAreaFree(position, building.getSize())){
            try{
                System.out.println("Adding building " + type + " to position " + position);
                buildings.addBuilding(building);
                map.construct(building.getPosition(), building.getSize());
            }catch (NotEnoughResources e){
                notifyErrorListener(e);
            }
        }
        notifyObservers();
    }

    public void removeBuilding(Building building) {
        if (!buildings.exists(building)){
            throw new RuntimeException("cannot remove building " + building);
        }
        map.destruct(building.getPosition(), building.getSize());
        buildings.removeBuilding(building);
        while(!building.getInhabitants().isEmpty()){
            deleteInhabitantFrom(building);
        }
        while (!building.getWorkers().isEmpty()){
            deleteWorkerFrom(building);
        }
        notifyObservers();
    }

    public void repairBuilding(Building building) {
        if (!buildings.exists(building)){
            notifyErrorListener( new WrongBuildingType("The building doesn't exist"));
        }
        if(building.getState() != States.BROKEN){
            notifyErrorListener( new WrongState("No need to repair this building."));
        }
        try{
            buildings.repairBuilding(building);
            notifyObservers();
        }catch (NotEnoughResources e){
            notifyErrorListener(e);
        }
    }

    public void boostBuilding(Building building) {
        if (!buildings.exists(building)){
            notifyErrorListener( new WrongBuildingType("The building doesn't exist"));
        }
        if(building.getState() != States.RUNNING){
            notifyErrorListener( new WrongState("Cannot boost a building in state"+ building.getState()));
        }
        try{
            buildings.boostBuilding(building);
            notifyObservers();
        }catch (NotEnoughResources e){
            notifyErrorListener(e);
        }
    }

    public void blockBuilding(Building building) {
        if (!buildings.exists(building)){
            notifyErrorListener( new WrongBuildingType("The building doesn't exist"));
        }
        if(building.getState() != States.RUNNING && building.getState() != States.BLOCKED){
            notifyErrorListener( new WrongState("Cannot block a building in state"+ building.getState()));
        }
        try{
            buildings.blockBuilding(building);
            notifyObservers();
        }catch (NotEnoughResources e){
            notifyErrorListener(e);
        }
    }

    public void runBuilding(Building building) {
        if (!buildings.exists(building)){
            notifyErrorListener( new WrongBuildingType("The building doesn't exist"));
        }
        try{
            buildings.runBuilding(building);
            notifyObservers();
        }catch (NotEnoughResources e){
            notifyErrorListener(e);
        }
    }

    public void createInhabitantInto(Building building) {
        if (!buildings.exists(building)){
            notifyErrorListener( new WrongBuildingType("The building doesn't exist"));
        }
        People people = new People();
        try{
            worldInhabitants.add(people);
            buildings.addInhabitantInto(building,people);
            notifyObservers();
        }catch (TooManyInhabitants | WrongState e){
            worldInhabitants.remove(people); // NEED TO CHECK WORLD INHABITANT LENGTH
            notifyErrorListener(e);
        }

    }

    public void deleteInhabitantFrom(Building building) {
        if (!buildings.exists(building)){
            notifyErrorListener( new WrongBuildingType("The building doesn't exist"));
        }
        if(building.getNumberInhabitants() == 0){
            notifyErrorListener( new NotEnoughInhabitants("No inhabitant to be deleted."));
        }
        People people = building.getInhabitants().getFirst();
        if(people.getJobPlace() != null){
            people.getJobPlace().removeWorker(people);
            people.affectJobPlace(null);
            building.removeWorker(people);
        }
        building.removeInhabitant(people);
        people.affectHouse(null);
        worldInhabitants.remove(people);
        notifyObservers();
    }

    public void assignWorkerTo(Building building) {
        try{
            People worker = findUnemployed();
            if (building.getMaxWorkers() > building.getWorkers().size()){
                building.addWorker(worker);
                worker.affectJobPlace(building);
                notifyObservers();
            }
        }catch (TooManyWorkers | NotEnoughInhabitants e){
            notifyErrorListener(e);
        }

    }

    public People findUnemployed(){
        for(People people : worldInhabitants){
            if(people.getJobPlace() == null){
                return people;
            }
        }
        throw new NotEnoughInhabitants("No unemployed person in town.");
    }

    public void deleteWorkerFrom(Building building) {
        if (!buildings.exists(building)){
            notifyErrorListener( new WrongBuildingType("The building doesn't exist"));
        }
        if(building.getNumberWorkers() == 0){
            notifyErrorListener( new NotEnoughWorkers("No worker to be deleted."));
        }
        People people = building.getWorkers().getFirst();
        people.getJobPlace().removeWorker(people);
        people.affectJobPlace(null);
        building.removeWorker(people);
        notifyObservers();
    }


    public Set<Building> getBuildings() {
        return buildings.getBuildings();
    }

    @Override
    public void addObserver(Runnable o) {
        observers.add(o);
    }

    @Override
    public void addErrorListener(Runnable o) {
        errorListener = o;
    }

    @Override
    public void removeObserver(Runnable o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Runnable o : observers) {
            Platform.runLater(o);
        }
    }

    @Override
    public void notifyErrorListener(Exception e) {
        currentException = e;
        if (errorListener != null) {
            Platform.runLater(errorListener);
        }
    }
}
