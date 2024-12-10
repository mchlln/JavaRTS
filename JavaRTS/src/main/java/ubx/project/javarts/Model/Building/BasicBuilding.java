package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Building.State.*;
import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.Resource;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.*;

public class BasicBuilding implements Building{
    private final UUID id;
    private final Map<ResourceType, Integer> cost;
    private final Size size;
    private final Position position;
    private final BuildingType type;
    private final ArrayList<BuildingFunction> functions = new ArrayList<BuildingFunction>();
    private final String name;
    private final int constructionTime;
    private final Automata buildingState = new Automata();
    private int stateCycleRemaining;
    private boolean stateChanged = false;
    private Random rand = new Random();

    public BasicBuilding(Position pos, Size s, String name, BuildingType type, Map<ResourceType, Integer> cost, int constructionTime){
        this.id = UUID.randomUUID();
        this.position = pos;
        this.size = s;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.constructionTime = constructionTime;
        stateCycleRemaining = constructionTime;
        buildingState.setCurrentState(new CreationState(buildingState));
    }
    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public Map<ResourceType, Integer> getCost() {
        return cost;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getConstructionTime() {
        return constructionTime;
    }

    @Override
    public int getRemainingTime(){
        return stateCycleRemaining;
    }



    @Override
    public BuildingType getType() {
        return type;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public ArrayList<BuildingFunction> getFunctions() {
        return functions;
    }

    @Override
    public void addFunction(ArrayList<BuildingFunction> functions){
        this.functions.addAll(functions);
    }

    @Override
    public Map<ResourceType, Integer> getDailyConsumption() {
        throw new WrongThreadException("Building cannot consume resources");
    }

    @Override
    public List<People> getWorkers() {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public int getMaxWorkers() {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public int getNumberWorkers() {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public void addWorker(People people) {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public void removeWorker(People people) {
        throw new WrongThreadException("Building cannot have workers");
    }

    @Override
    public Map<ResourceType, Integer> getDailyProduction() {
        throw new WrongThreadException("Building cannot produce resources");
    }

    @Override
    public List<People> getInhabitants() {
        throw new WrongThreadException("Building cannot have inhabitants");
    }

    @Override
    public int getMaxInhabitants() {
        throw new WrongThreadException("Building cannot have inhabitants");
    }

    @Override
    public int getNumberInhabitants() {
        throw new WrongThreadException("Building cannot have inhabitants");
    }

    @Override
    public void addInhabitant(People people) {
        throw new WrongThreadException("Building cannot have inhabitants");
    }

    @Override
    public void removeInhabitant(People people) {
        throw new WrongThreadException("Building cannot have inhabitants");
    }
    @Override
    public HashMap<ResourceType,Integer> handle(){
        handleState();
        return new HashMap<>();
    }

    private void handleState(){
        switch (buildingState.getCurrentState()){
            case CREATION: // CREATION --> RUNNING
                if (stateCycleRemaining == 0){
                    buildingState.setCurrentState(new RunningState(buildingState));
                    stateChanged = true;
                    System.out.println("[STATE] building switched to running state");
                    stateCycleRemaining = -1;
                } else {
                    stateChanged =false;
                    stateCycleRemaining--;
                }
                break;
            case RUNNING: // RUNNING --> BROKEN
                if (rand.nextInt(100) == 0){// 1/100 chance to break
                    buildingState.setCurrentState(new BrokenState(buildingState));
                    stateChanged = true;
                    stateCycleRemaining = -1;
                } else {
                    stateChanged =false;
                }
                break;
            case BOOSTED: // BOOSTED --> BROKEN
                if (stateCycleRemaining == 0){
                    if (rand.nextInt(4) == 0){
                        buildingState.setCurrentState(new BrokenState(buildingState));
                    }else{
                        buildingState.setCurrentState(new RunningState(buildingState));
                    }
                    stateChanged = true;
                    stateCycleRemaining = -1;
                } else {
                    stateChanged =false;
                    stateCycleRemaining--;
                }
                break;
            default:
                stateChanged = false;
                break;
        }
    }

    public void switchState(States state, int numberOfCycles){
        switch (state){
            case RUNNING:
                buildingState.setCurrentState(new RunningState(buildingState));
                stateChanged = true;
                stateCycleRemaining = -1;
                break;
            case BOOSTED:
                buildingState.setCurrentState(new BoostState(buildingState));
                stateChanged = true;
                stateCycleRemaining = numberOfCycles;
                break;
            case BROKEN:
                buildingState.setCurrentState(new BrokenState(buildingState));
                stateChanged = true;
                stateCycleRemaining = -1;
                break;
            default:
                break;
        }
    }

    @Override
    public boolean needViewUpdate(){
        return stateChanged;
    }

    public States getState(){
        return buildingState.getCurrentState();
    }

    @Override
    public String getName() {
        return name;
    }
}
