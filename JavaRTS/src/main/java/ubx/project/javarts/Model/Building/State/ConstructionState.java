package ubx.project.javarts.Model.Building.State;

public class ConstructionState extends AbstractState{
    private final Automata automata;

    public ConstructionState(Automata automata) {
        super(States.CONSTRUCTION);
        this.automata = automata;
    }

    @Override
    public void running() {
        this.automata.setCurrentState(new RunningState(automata));
    }
}
