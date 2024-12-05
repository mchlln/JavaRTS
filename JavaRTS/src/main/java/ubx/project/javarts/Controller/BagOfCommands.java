package ubx.project.javarts.Controller;

import javafx.concurrent.Task;
import ubx.project.javarts.Model.GameManager;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BagOfCommands {
    //private Queue<Command> commands;
    static BagOfCommands instance = null;
    private ConcurrentLinkedQueue<Command> commands = new ConcurrentLinkedQueue<>();
    GameManager model;
    Controller controller;
    boolean isRunning = false;

    BagOfCommands() {
        /*commands = new LinkedList<>();*/
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
        if (!isRunning) {
            processCommands();
        }
       // executeAll();
    }

    public void executeAll(){
        for (Command command : commands) {
            command.execute(model, controller);
            commands.remove(command);
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

    private void processCommands() {
        isRunning = true;
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (!commands.isEmpty()) {
                    Command command = commands.poll();
                    if (command != null) {
                        command.execute(model, controller);
                    }
                    Thread.sleep(1); // Delay between commands
                }
                return null;
            }

            @Override
            protected void succeeded() {
                isRunning = false;
                // Check for more commands after finishing the current batch
                if (!commands.isEmpty()) {
                    processCommands();
                }
                super.succeeded();
            }

            @Override
            protected void failed() {
                isRunning = false;
                super.failed();
            }
        };

        new Thread(task).start();
    }

}
