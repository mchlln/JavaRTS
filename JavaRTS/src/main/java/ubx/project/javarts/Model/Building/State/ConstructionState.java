package ubx.project.javarts.Model.Building.State;

/**
 * class concerning the Design Pattern State
 */
public class ConstructionState extends AbstractState{
    private final Automata automata;

    public ConstructionState(Automata automata) {
        super(States.CONSTRUCTION);
        this.automata = automata;
    }

    /**
     * Change the state of the automata to a valid state (RUNNING) when in CONSTRUCTION
     */
    @Override
    public void running() {
        this.automata.setCurrentState(new RunningState(automata));
    }
}
