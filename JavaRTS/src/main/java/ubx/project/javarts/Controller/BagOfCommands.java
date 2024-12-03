package ubx.project.javarts.Controller;

import java.util.LinkedList;
import java.util.Queue;

public class BagOfCommands {
    Queue<Command> commands;
    BagOfCommands instance = null;

    BagOfCommands() {
        commands = new LinkedList<>();
    }

    public void executeFirst() {
        Command command = commands.poll();
        if (command == null) {
            return;
        }
        command.execute();
    }

    public void executeAll(){
        for (Command command : commands) {
            command.execute();
        }
    }

    public BagOfCommands getInstance() {
        if (instance == null) {
            instance = new BagOfCommands();
        }
        return instance;
    }

}
