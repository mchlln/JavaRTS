package ubx.project.javarts.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import ubx.project.javarts.Exception.NotEnoughInhabitants;
import ubx.project.javarts.Exception.NotEnoughResources;
import ubx.project.javarts.Exception.TooManyInhabitants;
import ubx.project.javarts.Exception.TooManyWorkers;
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
    private Set<Runnable> observers;
    public Exception currentException;
    private Runnable errorListener;
    private Timeline timeline;
    private final BuildingBuilder b = new BuildingBuilder();

    private GameManager() {
        resources = ResourceManager.getInstance();
        worldInhabitants = new HashSet<>();
        observers = new HashSet<>();
        map = Map.getInstance();
        loop();
    }

    private void loop(){
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {buildings.handle(); notifyObservers();}));
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
                map.construct(building.getPostion(), building.getSize());
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
        map.destruct(building.getPostion(), building.getSize());
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
        }catch (TooManyInhabitants e){
            notifyErrorListener(e);
        }

    }

    public void deleteInhabitantFrom(Building building, People people) {
        if (!buildings.exists(building) || !worldInhabitants.contains(people)){
            return;
        }
        building.removeInhabitant(people);
        worldInhabitants.remove(people);
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
