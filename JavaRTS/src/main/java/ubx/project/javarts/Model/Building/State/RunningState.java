package ubx.project.javarts.Model.Building.State;

public class RunningState extends AbstractState {
    private final Automata automata;

    public RunningState(Automata automata) {
        super(States.RUNNING);
        this.automata = automata;
    }

    @Override
    public void boost() {
        automata.setCurrentState(new BoostState(automata));
    }

    @Override
    public void broken() {
        automata.setCurrentState(new BrokenState(automata));
    }
}
