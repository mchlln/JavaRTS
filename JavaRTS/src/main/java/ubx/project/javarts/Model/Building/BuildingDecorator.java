package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;
import ubx.project.javarts.Model.Resource.ResourceType;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public abstract class BuildingDecorator implements Building {
    private UUID id;
    private Set<People> inhabitants;
    private Set<People> workers;


    public Set<People> getInhabitants() {
        return inhabitants;
    }

    public Set<People> getWorkers() {
        return workers;
    }


    public int getNumberWorkers() {
        return workers.size();
    }

    public int getNumberInhabitants() {
        return inhabitants.size();
    }

    public boolean addInhabitant(People inhabitant) {
        return this.inhabitants.add(inhabitant);
    }

    public boolean addWorker(People worker) {
        return this.workers.add(worker);
    }

    public void removeInhabitant(People inhabitant) {
        this.inhabitants.remove(inhabitant);
    }

    public void removeWorker(People worker) {
        this.workers.remove(worker);
    }

    public Map<ResourceType, Integer> getDailyConsumption() {
        return null;
    }
    public Map<ResourceType, Integer> getDailyProduction() {
        return null;
    }

}
