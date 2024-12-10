package ubx.project.javarts.Model.Building.State;

public class BoostState extends AbstractState{
    private final Automata automata;

    public BoostState(Automata automata) {
        super(States.BOOSTED);
        this.automata = automata;
    }

    @Override
    public void broken() {
        automata.setCurrentState(new BrokenState(automata));
    }

    @Override
    public void running() {
        automata.setCurrentState(new RunningState(automata));
    }
}
