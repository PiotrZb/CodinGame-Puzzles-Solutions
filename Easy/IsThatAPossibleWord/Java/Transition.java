// class representing single transition to another state
class Transition{
    private final State destinationState;
    private final String condition;
    
    public Transition(State destination, String condition){
        this.condition = condition;
        this.destinationState = destination;
    }

    public State getDestinationState(){
        return this.destinationState;
    }

    public String getCondition(){
        return this.condition;
    }
}