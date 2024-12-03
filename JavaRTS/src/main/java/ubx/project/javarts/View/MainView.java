package ubx.project.javarts.View;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ubx.project.javarts.Model.GameManager;

public class MainView {
    public MainView(Stage stage, GameManager model) {
        stage.setTitle("PROJET JAVA TU CONNAIS ON VA AVOIR 20/20");
        Scene scene = new Scene(new VBox(), 600, 400);

        stage.setScene(scene);
        stage.show();
    }
}
