package ubx.project.javarts.Model.Building.State;

/**
 * Implementation of State for the Design Pattern State
 * Contains the different functions needed to set the automata to the right state
 */
public class AbstractState implements State {
    private final States currentState;

    public AbstractState(States currentState) {
        this.currentState = currentState;
    }

    public void creation() {
        throw new IllegalStateException("Unexpected creation state");
    }

    public void running() {
        throw new IllegalStateException("Unexpected running state");
    }

    public void broken() {
        throw new IllegalStateException("Unexpected broken state");
    }

    public void boost() {
        throw new IllegalStateException("Unexpected boost state");
    }

    public void blocked(){
        throw new IllegalStateException("Unexpected blocked state");
    }

    /**
     * @return the currentState of type {@link States}
     */
    public States getState() {
        return currentState;
    }

    @Override
    public void loop() {

    }
}
