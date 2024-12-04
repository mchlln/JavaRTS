package ubx.project.javarts.View;

import ubx.project.javarts.Exception.WrongBuildingType;
import ubx.project.javarts.Exception.WrongResourceType;
import ubx.project.javarts.Model.Resource.ResourceType;

public class ImagePath {



        // Private constructor to prevent instantiation
        private ImagePath() {
            throw new UnsupportedOperationException("Utility class");
        }

        public static String getLogoPath(ResourceType resource) {
            switch (resource) {
                case COAL -> {
                    return "/ubx/project/javarts/resourcesIcons/resource_apple.png";
                }
                case FOOD -> {
                    return "/ubx/project/javarts/resourcesIcons/resource_wheat.png";
                }
                case IRON -> {
                    return "/ubx/project/javarts/resourcesIcons/resource_iron.png";
                }
                case WOOD -> {
                    return "/ubx/project/javarts/resourcesIcons/resource_wood.png";
                }
                case STEEL -> {
                    return "/ubx/project/javarts/resourcesIcons/resource_apple.png";
                }
                case STONE -> {
                    return "/ubx/project/javarts/resourcesIcons/resource_apple.png";
                }
                case TOOLS -> {
                    return "/ubx/project/javarts/resourcesIcons/resource_apple.png";
                }
                case CEMENT -> {
                    return "/ubx/project/javarts/resourcesIcons/resource_apple.png";
                }
                case LUMBER -> {
                    return "/ubx/project/javarts/resourcesIcons/resource_lumber.png";
                }
                default -> throw new WrongResourceType("Wrong resource type");
            }
    }

}
