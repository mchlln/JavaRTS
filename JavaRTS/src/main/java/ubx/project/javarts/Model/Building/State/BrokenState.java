package ubx.project.javarts.Model.Building.State;

public class BrokenState extends AbstractState {
    private final Automata automata;

    public BrokenState(Automata automata) {
        super(States.BROKEN);
        this.automata = automata;
    }

    @Override
    public void running() {
        automata.setCurrentState(new RunningState(automata));
    }
}
