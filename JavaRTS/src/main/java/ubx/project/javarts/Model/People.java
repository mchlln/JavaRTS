package ubx.project.javarts.Model;

import ubx.project.javarts.Model.Building.Building;

import java.util.UUID;

/**
 * Represents a person in the game with a unique identifier, assigned house and job,.
 */
public class People {

    private UUID id;
    private Building house;
    private Building job;

    public People() {
        id = UUID.randomUUID();
    }

    public Building getHouse() {
        return house;
    }

    public Building getJobPlace() {
        return job;
    }


    /**
     * @param house the building to be assigned as the person's house.
     */
    public void affectHouse(Building house) {
        this.house = house;
    }

    /**
     * @param job the building to be assigned as the person's job.
     */
    public void affectJobPlace(Building job) {
        this.job = job;
    }

    public UUID getId() {
        return id;
    }
}
