package ubx.project.javarts.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import ubx.project.javarts.Exception.*;
import ubx.project.javarts.Model.Building.*;
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
        notifyObservers();
    }

    public void createInhabitantInto(Building building) {
        if (!buildings.exists(building)){
            return;
        }try{
            People people = new People();
            worldInhabitants.add(people);
            buildings.addInhabitantInto(building,people);
            notifyObservers();
        }catch (TooManyInhabitants | WrongBuildingType e ){
            notifyErrorListener(e);
        }

    }

    public void deleteInhabitantFrom(Building building) {
        if (!buildings.exists(building)){
            notifyErrorListener( new WrongBuildingType("The building doesn't exist"));
        }
        try{
            if(building.getInhabitants().isEmpty()){
                throw new NotEnoughInhabitants("There are no inhabitants");
            }
            People people = building.getInhabitants().getFirst();
            if(people.getJobPlace() != null){
                //people.getJobPlace().removeWorker(people);
                people.affectJobPlace(null);
                building.removeWorker(people);
            }
            building.removeInhabitant(people);
            people.affectHouse(null);
            worldInhabitants.remove(people);
            notifyObservers();
        }catch(NotEnoughInhabitants | WrongBuildingType e){
            notifyErrorListener(e);
        }
    }

    public void assignWorkerTo(Building building) {
        try{
            People worker = findUnemployed();
            if (building.getMaxWorkers() > building.getNumberWorkers()){
                building.addWorker(worker);
                worker.affectJobPlace(building);
                notifyObservers();
            }
        }catch (TooManyWorkers | NotEnoughInhabitants | WrongBuildingType e){
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
        try{
            if(building.getWorkers().isEmpty()){
                throw new NotEnoughWorkers("There are no workers");
            }
            People people = building.getWorkers().getFirst();
            people.getJobPlace().removeWorker(people);
            people.affectJobPlace(null);
            building.removeWorker(people);
            notifyObservers();
        }catch(NotEnoughWorkers | WrongBuildingType e){
            notifyErrorListener(e);
        }

    }

    public void killPeople(int count){
        while(count != 0){
           for(Building b : getBuildings()){
               if(b.getFunctions().contains(BuildingFunction.LIVING) && b.getNumberInhabitants() != 0){
                   deleteInhabitantFrom(b);
                   count--;
               }
           }
        }
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
