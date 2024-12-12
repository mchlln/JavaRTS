package ubx.project.javarts.Model.Building.State;

/**
 * class concerning the Design Pattern State
 */
public class BoostState extends AbstractState{
    private final Automata automata;

    public BoostState(Automata automata) {
        super(States.BOOSTED);
        this.automata = automata;
    }

    /**
     * Change the state of the automata to a valid state (BROKEN) when in BOOSTED
     */
    @Override
    public void broken() {
        automata.setCurrentState(new BrokenState(automata));
    }

    /**
     * Change the state of the automata to a valid state (RUNNING) when in BOOSTED
     */
    @Override
    public void running() {
        automata.setCurrentState(new RunningState(automata));
    }

    /**
     * Change the state of the automata to a valid state (BLOCKED) when in BOOSTED
     */
    @Override
    public void blocked() {
        automata.setCurrentState(new BlockedState(automata));
    }
}
