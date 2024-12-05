package ubx.project.javarts.View;

import ubx.project.javarts.Exception.WrongBuildingType;
import ubx.project.javarts.Exception.WrongResourceType;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.Resource.ResourceType;

public class ImagePath {



        // Private constructor to prevent instantiation
        private ImagePath() {
            throw new UnsupportedOperationException("Utility class");
        }

        public static String getResourceLogoPath(ResourceType resource) {
            switch (resource) {
                case COAL -> {
                    return "/ubx/project/javarts/resourcesIcons/coal.png";
                }
                case FOOD -> {
                    return "/ubx/project/javarts/resourcesIcons/food.png";
                }
                case IRON -> {
                    return "/ubx/project/javarts/resourcesIcons/iron.png";
                }
                case WOOD -> {
                    return "/ubx/project/javarts/resourcesIcons/wood.png";
                }
                case STEEL -> {
                    return "/ubx/project/javarts/resourcesIcons/steel.png";
                }
                case STONE -> {
                    return "/ubx/project/javarts/resourcesIcons/stone.png";
                }
                case TOOLS -> {
                    return "/ubx/project/javarts/resourcesIcons/tools.png";
                }
                case CEMENT -> {
                    return "/ubx/project/javarts/resourcesIcons/cement.png";
                }
                case LUMBER -> {
                    return "/ubx/project/javarts/resourcesIcons/lumber.png";
                }
                default -> throw new WrongResourceType("Wrong resource type");
            }
    }

    public static String getBuildingSpritePath(BuildingType buildingType) {
            switch (buildingType) {
                case WOODENCABIN -> {
                    return "/ubx/project/javarts/buildingSprites/woodenCabin.png";
                }
                case HOUSE -> {
                    return "/ubx/project/javarts/buildingSprites/woodenCabin.png";
                }
                case APPARTMENTBUILDING -> {
                    return "/ubx/project/javarts/buildingSprites/woodenCabin.png";
                }
                case FARM -> {
                    return "/ubx/project/javarts/buildingSprites/woodenCabin.png";
                }
                case QUARRY -> {
                    return "/ubx/project/javarts/buildingSprites/quarry.png";
                }
                case LUMBERMILL -> {
                    return "/ubx/project/javarts/buildingSprites/woodenCabin.png";
                }
                case CEMENTPLANT -> {
                    return "/ubx/project/javarts/buildingSprites/woodenCabin.png";
                }
                case STEELMILL -> {
                    return "/ubx/project/javarts/buildingSprites/woodenCabin.png";
                }
                case TOOLFACTORY -> {
                    return "/ubx/project/javarts/buildingSprites/factory.png";
                }
                default -> throw new WrongBuildingType("Wrong building type");

            }

    }
}
