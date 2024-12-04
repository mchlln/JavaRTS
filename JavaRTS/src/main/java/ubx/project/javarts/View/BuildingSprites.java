package ubx.project.javarts.View;

import ubx.project.javarts.Model.Building.BuildingType;

public enum BuildingSprites {
    WOODENCABIN (BuildingType.WOODENCABIN, "/ubx/project/javarts/buildingSprites/woodenCabin.png"),
    HOUSE (BuildingType.HOUSE, "/ubx/project/javarts/buildingSprites/woodenCabin.png"),
    APPARTMENTBUILDING (BuildingType.APPARTMENTBUILDING, "/ubx/project/javarts/buildingSprites/woodenCabin.png"),
    FARM (BuildingType.FARM, "/ubx/project/javarts/buildingSprites/woodenCabin.png"),
    QUARRY (BuildingType.QUARRY, "/ubx/project/javarts/buildingSprites/woodenCabin.png"),
    LUMBERMILL (BuildingType.LUMBERMILL, "/ubx/project/javarts/buildingSprites/woodenCabin.png"),
    CEMENTPLANT (BuildingType.CEMENTPLANT, "/ubx/project/javarts/buildingSprites/woodenCabin.png"),
    STEELMILL (BuildingType.STEELMILL, "/ubx/project/javarts/buildingSprites/woodenCabin.png"),
    TOOLFACTORY (BuildingType.TOOLFACTORY,"/ubx/project/javarts/buildingSprites/woodenCabin.png");

    private BuildingType buildingType;
    private String path;

    BuildingSprites(BuildingType buildingType, String path) {
        this.buildingType = buildingType;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }
}
