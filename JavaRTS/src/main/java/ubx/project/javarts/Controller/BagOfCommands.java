package ubx.project.javarts.Controller;

import javafx.concurrent.Task;
import ubx.project.javarts.Model.GameManager;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Design Pattern Bag of Commands
 * Design Pattern Singleton
 */
public class BagOfCommands {
    //private Queue<Command> commands;
    static BagOfCommands instance = null;
    private ConcurrentLinkedQueue<Command> commands = new ConcurrentLinkedQueue<>();
    GameManager model;
    Controller controller;
    boolean isRunning = false;

    /**
     * private constructor needed for design pattern singleton
     * Nothing done inside because every field is initialized outside
     */
    private BagOfCommands() {
        /*commands = new LinkedList<>();*/
    }

    /**
     * Executes the first command in the {@link ConcurrentLinkedQueue} of {@link Commands}
     */
    public void executeFirst() {
        Command command = commands.poll();
        if (command == null) {
            return;
        }
        command.execute(model, controller);
    }

    /**
     * Adds the command in the {@link ConcurrentLinkedQueue} of {@link Commands} and calls the
     * method {@link processCommands()} if it is not running
     * @param command {@link Command} to add to the list
     */
    public void addCommand(Command command){
        commands.add(command);
        if (!isRunning) {
            processCommands();
        }
       // executeAll();
    }

    /**
     * Executes all commands in the {@link ConcurrentLinkedQueue} of {@link Commands}
     */
    public void executeAll(){
        for (Command command : commands) {
            command.execute(model, controller);
            commands.remove(command);
        }
    }

    /**
     * @param model {@link GameManager} to set in the field model
     */
    public void setModel(GameManager model) {
        this.model = model;
    }

    /**
     * @param controller {@link Controller} to set in the field controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Creates an instance if not were created or return the instance in the field if it is
     * called for the first time
     * @return instance of {@link BagOfCommands}
     */
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
