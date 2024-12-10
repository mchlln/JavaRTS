package ubx.project.javarts.Model.Building.State;

public class Automata {
    private AbstractState currentState;

    public void setCurrentState(AbstractState currentState) {
        this.currentState = currentState;
    }

    public States getCurrentState() {
        return currentState.getState();
    }

}
