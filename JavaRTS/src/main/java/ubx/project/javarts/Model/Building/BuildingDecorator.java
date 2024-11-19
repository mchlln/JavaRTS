package ubx.project.javarts.Model.Building;

import ubx.project.javarts.Model.People;

import java.util.Set;
import java.util.UUID;

public abstract class BuildingDecorator implements Building {
    private UUID id;
    private Set<People> inhabitants;
    private Set<People> workers;


}
