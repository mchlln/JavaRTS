package ubx.project.javarts.View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import ubx.project.javarts.Controller.*;
import ubx.project.javarts.Controller.Commands.*;
import ubx.project.javarts.Model.Building.Building;
import ubx.project.javarts.Model.Building.BuildingFunction;
import ubx.project.javarts.Model.Building.State.States;

public class BuildingInfoPopup {

    /**
     * Displays a popup window showing the statistics and management options for a specified building.
     * The popup includes dynamic information such as the building's state, number of inhabitants,
     * number of workers, and buttons to add or remove inhabitants and workers.
     *
     * If the building has a "LIVING" function, options for managing inhabitants are displayed.
     * If the building has a "WORKING" function, options for managing workers are displayed.
     *
     * The popup also includes a "Remove Building" button to issue a command for removing the building.
     * While the popup is open, the displayed statistics are updated every second to reflect real-time changes.
     *
     * @param building the {@link Building} instance for which the stats and management options are displayed
     */

    public BuildingInfoPopup(Building building) {
            Stage popup = new Stage();
            popup.setMinHeight(400);
            popup.setMinWidth(400);
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Stats");

            Label nameLabel = new Label("Name: " + building.getName());
            Label stateLabel = new Label("State: " + building.getState());

            Button repairButton = new Button("Repair (1 Tool)");
            repairButton.setOnAction(event -> {
                BagOfCommands.getInstance().addCommand(new RepairBuildingCommand(building));
                System.out.println("Building repaired: " + building.getName());
            });

            ProgressBar constructionProgressBar = new ProgressBar();
            if(building.getState()!= States.CONSTRUCTION){
                constructionProgressBar.setVisible(false);
            }
            constructionProgressBar.setMinWidth(200);

            HBox layout = new HBox(10);
            layout.setSpacing(10);

            VBox buildingManagement = new VBox(10);

            buildingManagement.getChildren().addAll(nameLabel, stateLabel, repairButton, constructionProgressBar);
            layout.getChildren().addAll();

            VBox peopleManagement = new VBox(10);
            Label inhabitantsLabel = new Label();
            Label workersLabel = new Label();

        //features availaible only for buildings having the living function
            if(building.getFunctions().contains(BuildingFunction.LIVING)){
                inhabitantsLabel.setText("Inhabitants: " + building.getNumberInhabitants() + "/" + building.getMaxInhabitants());
                Button addInhabitantsButton = new Button("Add Inhabitants");
                addInhabitantsButton.setOnAction(event -> {
                    BagOfCommands.getInstance().addCommand(new AddInhabitantIntoCommand(building));
                    System.out.println("Inhabitant added to  " + building);
                });
                Button removeInhabitantsButton = new Button("Remove Inhabitants");
                removeInhabitantsButton.setOnAction(event -> {
                    BagOfCommands.getInstance().addCommand(new RemoveInhabitantFromCommand(building));
                    System.out.println("Inhabitant removed from  " + building);
                });
                peopleManagement.getChildren().addAll(inhabitantsLabel, addInhabitantsButton,removeInhabitantsButton);
            }

            //features availaible only for buildings having the working function
            if (building.getFunctions().contains(BuildingFunction.WORKING)) {
                workersLabel.setText("Workers: " + building.getNumberWorkers() + "/" + building.getMaxWorkers());
                Button addWorkersButton = new Button("Add Workers");
                addWorkersButton.setOnAction(event -> {
                    BagOfCommands.getInstance().addCommand(new AddWorkerIntoCommand(building));
                    System.out.println("Worker added to " + building);
                });
                Button removeWorkersButton = new Button("Remove Workers");
                removeWorkersButton.setOnAction(event -> {
                    BagOfCommands.getInstance().addCommand(new RemoveWorkerFromCommand(building));
                    System.out.println("Worker removed from " + building);
                });
                Button boostBuildingButton = new Button("Boost Production (1 Tool)");
                boostBuildingButton.setOnAction(event -> {
                    BagOfCommands.getInstance().addCommand(new BoostBuildingCommand(building));
                    System.out.println("Building " + building.getName()+ " production boosted");
                });
                peopleManagement.getChildren().addAll(workersLabel, addWorkersButton, removeWorkersButton, boostBuildingButton);
            }
            if (building.getFunctions().contains(BuildingFunction.PRODUCING) || building.getFunctions().contains(BuildingFunction.CONSUMING)) {
                if (building.getState() == States.BLOCKED){
                    Button running = new Button("Run building");
                    running.setOnAction(event -> {
                        BagOfCommands.getInstance().addCommand(new RunBuildingCommand(building));
                        System.out.println("Building " + building.getName()+ " now run");
                    });
                    peopleManagement.getChildren().add(running);
                } else if (building.getState() == States.RUNNING || building.getState() == States.BOOSTED) {
                    Button blockBuildingButton = new Button("Stop Building");
                    blockBuildingButton.setOnAction(event -> {
                        BagOfCommands.getInstance().addCommand(new BlockBuildingCommand(building));
                        System.out.println("Building " + building.getName()+ " blocked");
                    });
                    peopleManagement.getChildren().add(blockBuildingButton);
                }


            }

            Button removeButton = new Button("Remove Building");
            removeButton.setOnAction(event -> {
                BagOfCommands.getInstance().addCommand(new RemoveBuildingCommand(building));
                System.out.println("Building removed: " + building);
                popup.close();
            });
            buildingManagement.getChildren().add(removeButton);

            layout.getChildren().addAll( buildingManagement, peopleManagement);
            Scene scene = new Scene(layout);
            popup.setScene(scene);

            // Update the labels every second while the popup is open
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> {
                        if (building.getFunctions().contains(BuildingFunction.LIVING)) {
                            inhabitantsLabel.setText("Inhabitants: " + building.getNumberInhabitants() + "/" + building.getMaxInhabitants());
                        }
                        if (building.getFunctions().contains(BuildingFunction.WORKING)) {
                            workersLabel.setText("Workers: " + building.getNumberWorkers() + "/" + building.getMaxWorkers());
                        }
                        if(building.needViewUpdate()){
                            stateLabel.setText("State: " + building.getState());
                        }
                        if (building.getState() == States.CONSTRUCTION) {
                            constructionProgressBar.setVisible(true);
                            double progress = (double) (building.getConstructionTime()-building.getRemainingTime()) / building.getConstructionTime();
                            constructionProgressBar.setProgress(progress);
                        } else {
                            constructionProgressBar.setVisible(false);
                        }
                    })
            );
            timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
            timeline.play();

            // Stop the timeline when the popup is closed
            popup.setOnCloseRequest(event -> timeline.stop());
            popup.showAndWait();
        }
}
