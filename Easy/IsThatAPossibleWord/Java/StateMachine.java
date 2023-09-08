import java.util.ArrayList;
import java.util.Arrays;

// class representing state machine
class StateMachine{
    private final ArrayList<String> input;
    private ArrayList<State> states, finalStates;
    private State firstState;

    // initializing states and creating transitions between them
    public StateMachine(String[] input, String[] statesList, String[] transitions, String startState, String[] endStates){
        this.states = new ArrayList<>();
        this.input = new ArrayList<>(Arrays.asList(input));
        this.finalStates = new ArrayList<>();

        for (String stateName : statesList){
            State state = new State(stateName);
            this.states.add(state);

            if (stateName.equals(startState)){
                this.firstState = state;
            }

            for (String s : endStates){
                if (stateName.equals(s)){
                    this.finalStates.add(state);
                }
            }
        }

        State state1, state2;
        for (String transition : transitions){
            String[] transitionData = transition.split(" ");
            state1 = this.states.stream()
                    .filter(s -> s.getName().equals(transitionData[0]))
                    .findFirst()
                    .get();

            state2 = this.states.stream()
                    .filter(s -> s.getName().equals(transitionData[2]))
                    .findFirst()
                    .get();
            state1.addTransition(state2, transitionData[1]);
        }
    }

    public boolean checkWord(String word){
        State currentState = this.firstState;
        boolean transitionFound;
        for (String condition : word.split("")){
            if (input.contains(condition)){
                transitionFound = false;
                for (Transition transition  : currentState.getTransitions()){
                    if (transition.getCondition().equals(condition)){
                        currentState = transition.getDestinationState();
                        transitionFound = true;
                        break;
                    }
                }
                if (!transitionFound){
                    return false;
                }
            }
            else{
                return false;
            }
        }

       for (State finalState : this.finalStates){
           if (finalState == currentState){
               return true;
           }
       }

       return false;
    }
}