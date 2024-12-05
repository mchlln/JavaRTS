package ubx.project.javarts;

import javafx.application.Application;
import javafx.stage.Stage;
import ubx.project.javarts.Controller.BagOfCommands;
import ubx.project.javarts.Controller.Controller;
import ubx.project.javarts.Model.GameManager;
import ubx.project.javarts.View.MainView;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        GameManager model = GameManager.getInstance();
        MainView view = new MainView(stage, model);
        Controller controller = new Controller(model, view, BagOfCommands.getInstance());
        view.setController(controller);
        BagOfCommands.getInstance().setModel(model);
        BagOfCommands.getInstance().setController(controller);
        //model.addObserver(view);
    }

}
