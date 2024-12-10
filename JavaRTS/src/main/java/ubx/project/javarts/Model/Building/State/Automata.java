package ubx.project.javarts.Model.Building.State;

public class Automata {
    private AbstractState currentState;

    public void setCurrentState(AbstractState currentState) {
        this.currentState = currentState;
    }

    public AbstractState getCurrentState() {
        return currentState;
    }

    public States getCurrentStateName() {
        return currentState.getState();
    }

}
