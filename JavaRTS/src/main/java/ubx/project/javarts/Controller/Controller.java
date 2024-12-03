package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.GameManager;

import javax.swing.text.View;

public class Controller {
    GameManager model;
    View view;
    BagOfCommands bagOfCommands;

    public Controller(GameManager model, View view, BagOfCommands bagOfCommands) {
        this.model = model;
        this.view = view;
        this.bagOfCommands = bagOfCommands;
    }
}
