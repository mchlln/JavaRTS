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
import ubx.project.javarts.Model.GameManager;

public class MainView {
    public MainView(Stage stage, GameManager model) {
        stage.setTitle("JAVA RTS");

        CustomMenu topContainer = new CustomMenu();
        MapView map = new MapView();
        BorderPane root = new BorderPane();
        root.setTop(topContainer);
        root.setCenter(map);

        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }
}
