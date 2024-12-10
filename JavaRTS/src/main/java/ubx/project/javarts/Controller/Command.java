package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.GameManager;

/**
 * Design Pattern Command
 * Knows the controller for easier implementation
 */
public interface Command {
    void execute(GameManager model, Controller controller);
}
