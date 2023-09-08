import java.util.ArrayList;

class Letter{
    private char character;
    private ArrayList<Letter> childes;
    private Letter parent;

    public Letter(char c){
        this.character = c;
        this.childes = new ArrayList<>();
        this.parent = null;
    }

    public void addChild(Letter child){
        if (!this.childes.contains(child)){
            this.childes.add(child);
        }
    }

    public Letter getParent(){
        return this.parent;
    }

    public char getCharacter() {
        return this.character;
    }

    public ArrayList<Letter> getNeighbours() {
        return this.childes;
    }

    public void setParent(Letter p){
        this.parent = p;
    }
}