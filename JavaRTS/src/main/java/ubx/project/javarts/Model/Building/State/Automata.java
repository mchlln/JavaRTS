package ubx.project.javarts.Model.Building.State;

/**
 * Class to automate the change of state
 */
public class Automata {
    private AbstractState currentState;

    /**
     *
     * @param currentState {@link AbstractState} to assign to the private field
     */
    public void setCurrentState(AbstractState currentState) {
        this.currentState = currentState;
    }

    /**
     * @return  the private field currentState {@link AbstractState}
     */
    public AbstractState getCurrentState() {
        return currentState;
    }

    /**
     * @return the name of the currentState
     */
    public States getCurrentStateName() {
        return currentState.getState();
    }

}
