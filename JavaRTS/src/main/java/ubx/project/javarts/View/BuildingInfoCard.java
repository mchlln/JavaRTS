package ubx.project.javarts.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingFunction;

public class BuildingInfoCard extends VBox {
        private Building selectedBuilding;
        private final HBox root = new HBox();

        /**
         * Creates a building card for the {@link PeopleFooter} composed of a building,
         * its number of workers and inhabitants.
         *
         * @param building on the card
         * @param selectedBuilding building selected by the user by clicking on a card
         */
        public BuildingInfoCard(Building building, Building selectedBuilding) {
                root.setPadding(new Insets(10));
                root.setSpacing(15);
                root.setAlignment(Pos.CENTER);

                this.selectedBuilding = selectedBuilding;

                VBox house = new VBox();
                ImageView houseView = new ImageView(new Image(
                                getClass().getResource(ImagePath.getBuildingSpritePath(building.getType()))
                                                .toExternalForm()));
                houseView.setFitWidth(100);
                houseView.setFitHeight(100);

                // Text Label below House sprite
                Label farmLabel = new Label(building.getName());
                farmLabel.setFont(Font.font("Arial", 18));
                farmLabel.setTextFill(Color.DARKCYAN);

                // Bottom section - Left and right icons with text
                VBox bottomSection = new VBox(50);
                bottomSection.setPrefHeight(100);
                bottomSection.setAlignment(Pos.CENTER);

                for (BuildingFunction buildingFunction : building.getFunctions()) {
                        switch (buildingFunction) {
                                case LIVING:
                                        HBox personBox = createSpriteWithTextRight(
                                                        "/ubx/project/javarts/icons/house.png",
                                                        String.valueOf(building.getInhabitants().size()),
                                                        String.valueOf(building.getMaxInhabitants()));
                                        bottomSection.getChildren().add(personBox);
                                        break;
                                case WORKING:
                                        HBox workerBox = createSpriteWithTextRight(
                                                        "/ubx/project/javarts/icons/worker.png",
                                                        String.valueOf(building.getWorkers().size()),
                                                        String.valueOf(building.getMaxWorkers()));
                                        bottomSection.getChildren().add(workerBox);
                                        break;
                        }
                }
                house.getChildren().addAll(houseView, farmLabel);
                root.getChildren().addAll(house, bottomSection);
                // root.setPrefSize(500,250);

                this.getChildren().addAll(root);
                setBackground(building);
        }

        /**
         * Creates an image with text on the right
         * usage : logo  current/max
         *
         * @param imagePath {@link String} of the image to load
         * @param current number of inhabitants/workers
         * @param max number of inhabitans/workers
         * @return {@link HBox} of an image next to a text
         */
        private HBox createSpriteWithTextRight(String imagePath, String current, String max) {
                ImageView imageView = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);

                Label label = new Label(current + "/" + max);
                label.setFont(Font.font("Arial", 14));
                label.setTextFill(Color.BLACK);

                HBox hbox = new HBox(10);
                hbox.setAlignment(Pos.CENTER_LEFT);
                hbox.getChildren().addAll(imageView, label);
                return hbox;
        }

        /**
         * Sets the background of the card depending on wheter the user has clicked on it.
         * @param b {@link Building} on the card
         */
        public void setBackground(Building b) {
                if (b == this.selectedBuilding) {
                        BackgroundImage backgroundImage = new BackgroundImage(
                                        new Image(getClass().getResource(
                                                        "/ubx/project/javarts/buildingCards/selected_info_background.png")
                                                        .toExternalForm()),
                                        BackgroundRepeat.NO_REPEAT, // Repeat horizontally
                                        BackgroundRepeat.NO_REPEAT, // Repeat vertically
                                        BackgroundPosition.CENTER, // Position
                                        new BackgroundSize(
                                                        BackgroundSize.DEFAULT.getWidth(),
                                                        BackgroundSize.DEFAULT.getHeight(),
                                                        true, true, true, false // Scale to container size
                                        ));

                        root.setBackground(new Background(backgroundImage));
                } else {
                        BackgroundImage backgroundImage = new BackgroundImage(
                                        new Image(getClass()
                                                        .getResource("/ubx/project/javarts/buildingCards/not_selected_info_background.png")
                                                        .toExternalForm()),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        new BackgroundSize(
                                                        BackgroundSize.DEFAULT.getWidth(),
                                                        BackgroundSize.DEFAULT.getHeight(),
                                                        true, true, true, false));

                        root.setBackground(new Background(backgroundImage));
                }
        }

}
