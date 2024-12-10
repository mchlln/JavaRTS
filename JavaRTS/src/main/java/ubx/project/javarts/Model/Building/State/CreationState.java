package ubx.project.javarts.Model.Building.State;

public class CreationState extends AbstractState{
    private final Automata automata;

    public CreationState(Automata automata) {
        super(States.CREATION);
        this.automata = automata;
    }

    @Override
    public void running() {
        this.automata.setCurrentState(new RunningState(automata));
    }
}
