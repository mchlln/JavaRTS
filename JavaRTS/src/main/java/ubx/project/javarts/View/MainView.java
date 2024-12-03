package ubx.project.javarts.View;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ubx.project.javarts.Controller.Controller;
import ubx.project.javarts.Model.Building.BuildingType;
import ubx.project.javarts.Model.GameManager;

import java.util.ArrayList;

public class MainView implements Observer {

    private Controller controller;
    private ArrayList<BuildingCard> cards = new ArrayList<>();
    private CustomMenu topContainer;

    public MainView(Stage stage, GameManager model) {
        stage.setTitle("JAVA RTS");

        topContainer = new CustomMenu();
        MapView map = new MapView();
        BorderPane root = new BorderPane();
        Footer footer = new Footer();
        root.setTop(topContainer);
        root.setCenter(map);
        root.setBottom(footer);
        for (BuildingType buildingType : BuildingType.values()) {
            BuildingCard b = new BuildingCard(buildingType);
            cards.add(b);
            b.setOnMouseClicked(event -> {
                controller.ChangeSelectedBuilding(buildingType);
            });
            footer.addWidget(b);
        }

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

    @Override
    public void update() {
        topContainer.actualiseResources();
    }
}
