import java.util.ArrayList;

// class representing single state
class State{
    private final String name;
    private ArrayList<Transition> transitions;
    public State(String n){
        this.name = n;
        this.transitions = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public void addTransition(State destination, String condition){
        if (destination != null && condition != null){
            this.transitions.add(new Transition(destination, condition));
        }
    }

    public ArrayList<Transition> getTransitions() {
        return this.transitions;
    }
}