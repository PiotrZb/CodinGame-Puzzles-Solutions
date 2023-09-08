import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Graph{
    private ArrayList<Letter> listOfA, solutionString;
    private Letter[][] layout;
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Graph(char[][] l){
        int positionInAlphabet, size = l.length;
        this.solutionString = new ArrayList<>();
        this.listOfA = new ArrayList<>();
        layout = new Letter[size][size];
        Letter letter, neighbour;

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                letter = new Letter(l[i][j]);
                this.layout[i][j] = letter;

                // finding 'a' letters
                if (letter.getCharacter() == 'a'){
                    this.listOfA.add(letter);
                }

                // adding connections
                positionInAlphabet = this.alphabet.indexOf(letter.getCharacter());
                // upper neighbour
                if (i - 1 >= 0){
                    neighbour = this.layout[i - 1][j];
                    if (this.alphabet.indexOf(neighbour.getCharacter()) == positionInAlphabet + 1){
                        letter.addChild(neighbour);
                    } else if (this.alphabet.indexOf(neighbour.getCharacter()) == positionInAlphabet - 1) {
                        neighbour.addChild(letter);
                    }
                }
                // left neighbour
                if (j - 1 >= 0){
                    neighbour = this.layout[i][j - 1];
                    if (this.alphabet.indexOf(neighbour.getCharacter()) == positionInAlphabet + 1){
                        letter.addChild(neighbour);
                    } else if (this.alphabet.indexOf(neighbour.getCharacter()) == positionInAlphabet - 1) {
                        neighbour.addChild(letter);
                    }
                }
            }
        }
    }

    // implementation of BFS algorithm
    public void solve(){
        Queue<Letter> queue = new LinkedList<>();
        Letter currentLetter = null;
        boolean solved = false;
        
        for (Letter a : listOfA){
            this.reset();
            queue.add(a);

            while (!queue.isEmpty()){
                currentLetter = queue.poll();
                if (currentLetter.getCharacter() == 'z'){
                    solved = true;
                    break;
                }
                for (Letter neighbour : currentLetter.getNeighbours()){
                    neighbour.setParent(currentLetter);
                    queue.add(neighbour);
                }
            }

            if (solved){
                break;
            }
        }

        // path reconstruction
        while(currentLetter != null){
            solutionString.add(currentLetter);
            currentLetter = currentLetter.getParent();
        }
    }

    private void reset(){
        for (Letter[] line : this.layout){
            for (Letter letter : line){
                letter.setParent(null);
            }
        }
    }

    public void printAnswer(){
        String s;
        for (Letter[] line : this.layout){
            s = "";
            for (Letter letter : line){
                if (this.solutionString.contains(letter)){
                    s += letter.getCharacter();
                }else{
                    s += '-';
                }
            }
            if (line != this.layout[this.layout.length - 1]){
                System.out.println(s);
            }else{
                System.out.print(s);
            }
        }
    }
}