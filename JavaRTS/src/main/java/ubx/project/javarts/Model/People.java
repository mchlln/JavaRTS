package ubx.project.javarts.Model;

import ubx.project.javarts.Model.Building.Building;

import java.util.UUID;

public class People {

    private UUID id;
    private Building house;
    private Building job;
    private int foodCost;

    public People() {
        id = UUID.randomUUID();
    }

    public Building getHouse() {
        return house;
    }

    public Building getJobPlace() {
        return job;
    }

    public void affectHouse(Building house) {
        this.house = house;
    }

    public void affectJobPlace(Building job) {
        this.job = job;
    }

    public UUID getId() {
        return id;
    }
}
