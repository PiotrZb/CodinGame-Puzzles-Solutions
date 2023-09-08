import java.util.ArrayList;

// Class that represents a single field in a maze
class Field {
    private char charRepresentation;
    private boolean visited;
    private int positionX, positionY;

    private ArrayList<Field> neighbours, diagonalNeighbours;

    private Field previousField;

    Field(int x, int y, char type) {
        this.charRepresentation = type;
        this.positionX = x;
        this.positionY = y;
        this.visited = false;
        this.neighbours = new ArrayList<>();
        this.diagonalNeighbours = new ArrayList<>();
        this.previousField = null;
    }

    public void addNeighbour(Field neighbour, boolean diagonal) {
        if (diagonal) {
            this.diagonalNeighbours.add(neighbour);
        } else {
            this.neighbours.add(neighbour);
        }
    }

    public char getCharRepresentation() {
        return this.charRepresentation;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public ArrayList<Field> getAllNeighbours() {
        ArrayList<Field> mergedNeighbours = new ArrayList<>();
        mergedNeighbours.addAll(this.neighbours);
        mergedNeighbours.addAll(this.diagonalNeighbours);
        return mergedNeighbours;
    }

    public Field getPreviousField() {
        return this.previousField;
    }

    public void setCharRepresentation(char type) {
        this.charRepresentation = type;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setPreviousField(Field previousField) {
        this.previousField = previousField;
    }
}