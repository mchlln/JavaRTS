package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.GameManager;
import ubx.project.javarts.View.MainView;

public class Controller {
    private GameManager model;
    private MainView view;
    private BagOfCommands bagOfCommands;

    public Controller(GameManager model, MainView view, BagOfCommands bagOfCommands) {
        this.model = model;
        this.view = view;
        this.bagOfCommands = bagOfCommands;
    }

}
