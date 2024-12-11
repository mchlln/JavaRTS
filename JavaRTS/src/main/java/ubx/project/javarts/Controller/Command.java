package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.GameManager;

/**
 * Design Pattern Command
 * Knows the controller for easier implementation
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param model      {@link GameManager} that handles game logic
     * @param controller {@link Controller} providing the selected building type if
     *                   needed
     */
    void execute(GameManager model, Controller controller);
}
