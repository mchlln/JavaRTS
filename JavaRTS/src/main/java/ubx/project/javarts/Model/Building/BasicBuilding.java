package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.Building.State.*;
import ubx.project.javarts.Exception.WrongBuildingType;
import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Position;
import ubx.project.javarts.Model.Resource.ResourceType;
import ubx.project.javarts.Model.Size;

import java.util.*;

public class BasicBuilding implements Building {
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

    public BasicBuilding(Position pos, Size s, String name, BuildingType type, Map<ResourceType, Integer> cost,
            int constructionTime) {
        this.id = UUID.randomUUID();
        this.position = pos;
        this.size = s;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.constructionTime = constructionTime;
        stateCycleRemaining = constructionTime;
        buildingState.setCurrentState(new ConstructionState(buildingState));
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
    public int getRemainingTime() {
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
    public void addFunction(ArrayList<BuildingFunction> functions) {
        this.functions.addAll(functions);
    }

    @Override
    public Map<ResourceType, Integer> getDailyConsumption() {
        throw new WrongBuildingType("Building cannot consume resources");
    }

    @Override
    public List<People> getWorkers() {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public int getMaxWorkers() {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public int getNumberWorkers() {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public void addWorker(People people) {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public void removeWorker(People people) {
        throw new WrongBuildingType("Building cannot have workers");
    }

    @Override
    public Map<ResourceType, Integer> getDailyProduction() {
        throw new WrongBuildingType("Building cannot produce resources");
    }

    @Override
    public List<People> getInhabitants() {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public int getMaxInhabitants() {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public int getNumberInhabitants() {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public void addInhabitant(People people) {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public void removeInhabitant(People people) {
        throw new WrongBuildingType("Building cannot have inhabitants");
    }

    @Override
    public HashMap<ResourceType, Integer> handle() {
        handleState();
        return new HashMap<>();
    }

    private void handleState() {
        switch (buildingState.getCurrentStateName()) {
            case CONSTRUCTION: // CREATION --> RUNNING
                if (stateCycleRemaining == 0) {
                    buildingState.getCurrentState().running();
                    stateChanged = true;
                    System.out.println("[STATE] building switched to running state");
                    stateCycleRemaining = -1;
                } else {
                    stateChanged = false;
                    stateCycleRemaining--;
                }
                break;
            case RUNNING: // RUNNING --> BROKEN
                if (rand.nextInt(1000) == 0) {// 1/1000 chance to break
                    buildingState.getCurrentState().broken();
                    stateChanged = true;
                    stateCycleRemaining = -1;
                } else {
                    stateChanged = false;
                }
                break;
            case BOOSTED: // BOOSTED --> BROKEN
                if (stateCycleRemaining == 0) {
                    if (rand.nextInt(4) == 0) {
                        buildingState.getCurrentState().broken();
                    } else {
                        buildingState.getCurrentState().running();
                    }
                    stateChanged = true;
                    stateCycleRemaining = -1;
                } else {
                    stateChanged = false;
                    stateCycleRemaining--;
                }
                break;
            default:
                stateChanged = false;
                break;
        }
    }

    public void switchState(States state, int numberOfCycles) {
        switch (state) {
            case RUNNING:
                buildingState.getCurrentState().running();
                stateChanged = true;
                stateCycleRemaining = -1;
                break;
            case BOOSTED:
                buildingState.getCurrentState().boost();
                stateChanged = true;
                stateCycleRemaining = numberOfCycles;
                break;
            case BROKEN:
                buildingState.getCurrentState().broken();
                stateChanged = true;
                stateCycleRemaining = -1;
                break;
            case BLOCKED:
                buildingState.getCurrentState().blocked();
                stateChanged = true;
                stateCycleRemaining = -1;
            default:
                break;
        }
    }

    @Override
    public boolean needViewUpdate() {
        return stateChanged;
    }

    public States getState() {
        return buildingState.getCurrentStateName();
    }

    @Override
    public String getName() {
        return name;
    }
}
