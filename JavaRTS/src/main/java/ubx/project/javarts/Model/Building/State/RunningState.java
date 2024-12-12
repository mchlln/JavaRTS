package ubx.project.javarts.Model.Building.State;

/**
 * class concerning the Design Pattern State
 */
public class RunningState extends AbstractState {
    private final Automata automata;

    public RunningState(Automata automata) {
        super(States.RUNNING);
        this.automata = automata;
    }

    /**
     * Change the state of the automata to a valid state (BOOST) when in RUNNING
     */
    @Override
    public void boost() {
        automata.setCurrentState(new BoostState(automata));
    }

    /**
     * Change the state of the automata to a valid state(BROKEN) when in RUNNING
     */
    @Override
    public void broken() {
        automata.setCurrentState(new BrokenState(automata));
    }

    /**
     * Change the state of the automata to a valid state when in RUNNING
     */
    @Override
    public void blocked() {
        automata.setCurrentState(new BlockedState(automata));
    }
}
