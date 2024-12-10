package ubx.project.javarts.Model.Building.State;

public class BlockedState extends AbstractState {
    private final Automata automata;
    public BlockedState(Automata automata) {
        super(States.BLOCKED);
        this.automata = automata;
    }

    @Override
    public void running() {
        automata.setCurrentState(new RunningState(automata));
    }
}
