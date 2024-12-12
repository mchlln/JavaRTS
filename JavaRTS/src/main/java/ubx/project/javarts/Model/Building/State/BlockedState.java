package ubx.project.javarts.Model.Building.State;

/**
 * class concerning the Design Pattern State
 */
public class BlockedState extends AbstractState {
    private final Automata automata;
    public BlockedState(Automata automata) {
        super(States.BLOCKED);
        this.automata = automata;
    }

    /**
     * Change the state of the automata to a valid state (RUNNING) when in BLOCKED
     */
    @Override
    public void running() {
        automata.setCurrentState(new RunningState(automata));
    }
}
