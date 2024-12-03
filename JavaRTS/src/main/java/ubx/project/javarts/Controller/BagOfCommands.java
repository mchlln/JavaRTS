package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.GameManager;

import java.util.LinkedList;
import java.util.Queue;

public class BagOfCommands {
    private Queue<Command> commands;
    static BagOfCommands instance = null;
    GameManager model;
    Controller controller;

    BagOfCommands() {
        commands = new LinkedList<>();
    }

    public void executeFirst() {
        Command command = commands.poll();
        if (command == null) {
            return;
        }
        command.execute(model, controller);
    }

    public void addCommand(Command command){
        commands.add(command);
        executeAll();
    }

    public void executeAll(){
        for (Command command : commands) {
            command.execute(model, controller);
        }
    }

    public void setModel(GameManager model) {
        this.model = model;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public static BagOfCommands getInstance() {
        if (instance == null) {
            instance = new BagOfCommands();
        }
        return instance;
    }

}
