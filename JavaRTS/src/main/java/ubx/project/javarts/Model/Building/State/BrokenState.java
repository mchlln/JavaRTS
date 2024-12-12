package ubx.project.javarts.Model.Building.State;

/**
 * class concerning the Design Pattern State
 */
public class BrokenState extends AbstractState {
    private final Automata automata;

    public BrokenState(Automata automata) {
        super(States.BROKEN);
        this.automata = automata;
    }

    /**
     * Change the state of the automata to a valid state (RUNNING) when in BROKEN
     */
    @Override
    public void running() {
        automata.setCurrentState(new RunningState(automata));
    }
}
