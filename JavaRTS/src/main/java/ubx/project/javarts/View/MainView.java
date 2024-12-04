package ubx.project.javarts.View;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ubx.project.javarts.Controller.BagOfCommands;
import ubx.project.javarts.Controller.Controller;
import ubx.project.javarts.Controller.SetSelectedBuilding;
import ubx.project.javarts.Model.Building.BuildingManager;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.GameManager;

import java.util.ArrayList;

public class MainView implements Observer {

    private Controller controller;
    private ArrayList<BuildingCard> cards = new ArrayList<>();
    private CustomMenu topContainer;
    private MapView map;
    private GameManager model;

    public MainView(Stage stage, GameManager model) {
        stage.setTitle("JAVA RTS");
        this.model = model;
        topContainer = new CustomMenu();
        map = new MapView();
        BorderPane root = new BorderPane();
        Footer footer = new Footer();
        root.setTop(topContainer);
        root.setCenter(map);
        root.setBottom(footer);
        for (BuildingType buildingType : BuildingType.values()) {
            BuildingCard b = new BuildingCard(buildingType);
            cards.add(b);
            b.setOnMouseClicked(event -> {
                BagOfCommands.getInstance().addCommand(new SetSelectedBuilding(buildingType));
            });
            footer.addWidget(b);
        }
        setAvailability();
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setSelectedBuilding(BuildingType buildingType) {
        for (BuildingCard b : cards) {
            b.setSelected(buildingType);
        }
    }

    public void setAvailability(){
        for(BuildingCard b : cards){
            if( ! BuildingManager.isBuildable(b.getBuildingType())){
                b.setOpacity(0.4);
            }else{
                b.setOpacity(1);
            }
        }
    }

    @Override
    public void update() {
        topContainer.actualiseResources();
        map.drawBuildings(model.getBuildings());
        setAvailability();

    }

    public void updateError(Exception e) {
        showErrorPopup(e.getMessage());
    }

    private void showErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
