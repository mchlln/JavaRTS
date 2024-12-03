package ubx.project.javarts.Controller;

import ubx.project.javarts.Model.GameManager;

public interface Command {
    public void execute(GameManager model, Controller controller);
}
