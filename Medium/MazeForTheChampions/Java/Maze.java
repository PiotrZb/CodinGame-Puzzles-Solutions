import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// Class representing a maze in the form of a graph and implementing methods to solve it
class Maze {
    private Field[][] layout;
    private Field entry, exit;
    private int height, width;

    // Transforming 2D array of chars to 2D array of Fields
    Maze(char[][] mazeLayout) {
        this.height = mazeLayout.length;
        this.width = mazeLayout[0].length;
        this.layout = new Field[this.height][this.width];

        char fieldType;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                fieldType = mazeLayout[i][j];
                if (fieldType == '.' || fieldType == '#'){
                    this.layout[i][j] = new Field(j, i, fieldType);
                }
                // Searching for entry and exit
                else{
                    switch (fieldType) {
                        case '<':
                            if (j == 0) {
                                this.exit = new Field(j, i, fieldType);
                            } else {
                                this.entry = new Field(j, i, fieldType);
                            }
                            break;
                        case '>':
                            if (j == 0) {
                                this.entry = new Field(j, i, fieldType);
                            } else {
                                this.exit = new Field(j, i, fieldType);
                            }
                            break;
                        case '^':
                            if (i == 0) {
                                this.exit = new Field(j, i, fieldType);
                            } else {
                                this.entry = new Field(j, i, fieldType);
                            }
                            break;
                        case 'v':
                            if (i == 0) {
                                this.entry = new Field(j, i, fieldType);
                            } else {
                                this.exit = new Field(j, i, fieldType);
                            }
                            break;
                    }
                }
            }
        }

        this.layout[this.exit.getPositionY()][this.exit.getPositionX()] = this.exit;
        this.layout[this.entry.getPositionY()][this.entry.getPositionX()] = this.entry;
    }

    // Method that returns solution of a maze (String type of hero, int total time needed to complete maze, String visualization of maze and traveled rout)
    public ArrayList<Object> solve(Hero hero) {
        ArrayList<Object> solution = new ArrayList<>();
        int timeBase = 0;

        switch (hero) {
            case WARRIOR:
                solution.add("WARRIOR");
                timeBase = 2;
                this.generateConnectionsForWarrior();
                break;
            case DWARF:
                solution.add("DWARF");
                timeBase = 3;
                this.generateConnectionsForDwarf();
                break;
            case ELF:
                solution.add("ELF");
                timeBase = 4;
                this.generateConnectionsForElf();
                break;
            case MAGE:
                solution.add("MAGE");
                timeBase = 5;
                this.generateConnectionsForMage();
                break;
        }

        ArrayList<Field> bestRoute = this.findBestRoute();
        solution.add(bestRoute.size() * timeBase);
        solution.add(this.printMaze(bestRoute));

        return solution;
    }

    // Method that generates visualization of a maze and traveled route
    private String printMaze(ArrayList<Field> route) {
        // Creating route visualization
        Field currentField, nextField;
        for (int i = 1; i < route.size() - 1; i++) {
            currentField = route.get(i);
            nextField = route.get(i + 1);

            if (currentField.getPositionY() > nextField.getPositionY()) {
                if (currentField.getPositionX() == nextField.getPositionX()) {
                    currentField.setCharRepresentation('^');
                } else {
                    currentField.setCharRepresentation('o');
                }
            } else if (currentField.getPositionY() < nextField.getPositionY()) {
                if (currentField.getPositionX() == nextField.getPositionX()) {
                    currentField.setCharRepresentation('v');
                } else {
                    currentField.setCharRepresentation('o');
                }
            } else if (currentField.getPositionX() > nextField.getPositionX()) {
                currentField.setCharRepresentation('<');
            } else {
                currentField.setCharRepresentation('>');
            }
        }

        // Printing maze
        String line, maze = "";

        for (int i = 0; i < this.height; i++) {
            line = "";
            for (Field f : this.layout[i]) {
                line += f.getCharRepresentation();
            }
            if (i < this.height - 1) {
                line += '\n';
            }
            maze += line;
        }

        return maze;
    }

    // Methods that create graphs for all types of hero
    private void generateConnectionsForWarrior() {
        Field field;
        ArrayList<Field> neighboursToCheck = new ArrayList<>();
        for (int i = 1; i < this.height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                field = this.layout[i][j];


                if (field.getCharRepresentation() == '.') {
                    neighboursToCheck.clear();
                    // Upper neighbour
                    neighboursToCheck.add(this.layout[i - 1][j]);
                    // Lower neighbour
                    neighboursToCheck.add(this.layout[i + 1][j]);
                    // Left neighbour
                    neighboursToCheck.add(this.layout[i][j - 1]);
                    // Right neighbour
                    neighboursToCheck.add(this.layout[i][j + 1]);
                    // Upper neighbour

                    for (Field neighbour : neighboursToCheck){
                        if (neighbour.getCharRepresentation() != '#' && !field.getAllNeighbours().contains(neighbour)) {
                            field.addNeighbour(neighbour, false);
                            neighbour.addNeighbour(field, false);
                        }
                    }
                }
            }
        }
    }

    private void generateConnectionsForDwarf() {
        // This method only extends graph for Warrior
        this.generateConnectionsForWarrior();

        Field field, neighbour1, neighbour2;
        for (int i = 1; i < this.height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                field = this.layout[i][j];
                if (field.getCharRepresentation() == '#') {
                    // Upper and lower neighbour
                    neighbour1 = this.layout[i - 1][j];
                    neighbour2 = this.layout[i + 1][j];
                    // Checking if wall is breakable
                    if (neighbour1.getCharRepresentation() == '.' && neighbour2.getCharRepresentation() == '.') {
                        neighbour1.addNeighbour(field, false);
                        neighbour2.addNeighbour(field, false);
                        field.addNeighbour(neighbour1, false);
                        field.addNeighbour(neighbour2, false);
                    }
                    // Left and right neighbour
                    neighbour1 = this.layout[i][j - 1];
                    neighbour2 = this.layout[i][j + 1];
                    // Checking if wall is breakable
                    if (neighbour1.getCharRepresentation() == '.' && neighbour2.getCharRepresentation() == '.') {
                        neighbour1.addNeighbour(field, false);
                        neighbour2.addNeighbour(field, false);
                        field.addNeighbour(neighbour1, false);
                        field.addNeighbour(neighbour2, false);
                    }
                }
            }
        }
    }

    private void generateConnectionsForElf() {
        // This method only extends graph for Warrior
        this.generateConnectionsForWarrior();

        Field field;
        ArrayList<Field> diagonalNeighboursToCheck = new ArrayList<>();
        for (int i = 1; i < this.height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                field = this.layout[i][j];

                // Diagonal neighbours
                if (field.getCharRepresentation() == '.') {
                    diagonalNeighboursToCheck.clear();
                    diagonalNeighboursToCheck.add(this.layout[i + 1][j + 1]);
                    diagonalNeighboursToCheck.add(this.layout[i - 1][j - 1]);
                    diagonalNeighboursToCheck.add(this.layout[i + 1][j - 1]);
                    diagonalNeighboursToCheck.add(this.layout[i - 1][j + 1]);

                    for (Field neighbour : diagonalNeighboursToCheck){
                        if (neighbour.getCharRepresentation() != '#' && !field.getAllNeighbours().contains(neighbour)) {
                            field.addNeighbour(neighbour, true);
                            neighbour.addNeighbour(field, true);
                        }
                    }
                }
            }
        }
    }

    private void generateConnectionsForMage() {
        int distance;
        Field field, neighbour;
        for (int i = 1; i < this.height - 1; i++) {
            for (int j = 1; j < this.width - 1; j++) {
                field = this.layout[i][j];
                if (field.getCharRepresentation() == '.') {
                    // All upper fields
                    distance = 1;
                    while (true) {
                        if (i - distance >= 0) {
                            neighbour = this.layout[i - distance][j];
                            if (neighbour.getCharRepresentation() != '#') {
                                if (!field.getAllNeighbours().contains(neighbour)) {
                                    field.addNeighbour(neighbour, false);
                                    neighbour.addNeighbour(field, false);
                                }
                                distance++;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }

                    // All lower fields
                    distance = 1;
                    while (true) {
                        if (i + distance < this.height) {
                            neighbour = this.layout[i + distance][j];
                            if (neighbour.getCharRepresentation() != '#') {
                                if (!field.getAllNeighbours().contains(neighbour)) {
                                    field.addNeighbour(neighbour, false);
                                    neighbour.addNeighbour(field, false);
                                }
                                distance++;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    // All fields on the right
                    distance = 1;
                    while (true) {
                        if (j + distance < this.width) {
                            neighbour = this.layout[i][j + distance];
                            if (neighbour.getCharRepresentation() != '#') {
                                if (!field.getAllNeighbours().contains(neighbour)) {
                                    field.addNeighbour(neighbour, false);
                                    neighbour.addNeighbour(field, false);
                                }
                                distance++;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    // All fields on the left
                    distance = 1;
                    while (true) {
                        if (j - distance >= 0) {
                            neighbour = this.layout[i][j - distance];
                            if (neighbour.getCharRepresentation() != '#') {
                                if (!field.getAllNeighbours().contains(neighbour)) {
                                    field.addNeighbour(neighbour, false);
                                    neighbour.addNeighbour(field, false);
                                }
                                distance++;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    // Implementation of BFS algorithm
    private ArrayList<Field> findBestRoute() {
        // BFS
        Queue<Field> queue = new LinkedList<>();
        this.entry.setVisited(true);
        queue.add(this.entry);
        Field neighbour, fieldNextToTheNeighbour, currentField, field;

        while (!(queue.isEmpty() || queue.contains(this.exit))) {
            currentField = queue.poll();

            // Wall has been destroyed - updating its neighbours
            if (currentField.getCharRepresentation() == '#') {
                // Upper neighbour
                if (currentField.getPositionY() - 2 > 0) {
                    neighbour = this.layout[currentField.getPositionY() - 1][currentField.getPositionX()];
                    fieldNextToTheNeighbour = this.layout[currentField.getPositionY() - 2][currentField.getPositionX()];
                    if (neighbour.getCharRepresentation() == '#' && fieldNextToTheNeighbour.getCharRepresentation() != '#') {
                        currentField.addNeighbour(neighbour, false);
                        neighbour.addNeighbour(currentField, false);
                        neighbour.addNeighbour(fieldNextToTheNeighbour, false);
                    }
                }
                // Lower neighbour
                if (currentField.getPositionY() + 2 < this.height - 1) {
                    neighbour = this.layout[currentField.getPositionY() + 1][currentField.getPositionX()];
                    fieldNextToTheNeighbour = this.layout[currentField.getPositionY() + 2][currentField.getPositionX()];
                    if (neighbour.getCharRepresentation() == '#' && fieldNextToTheNeighbour.getCharRepresentation() != '#') {
                        currentField.addNeighbour(neighbour, false);
                        neighbour.addNeighbour(currentField, false);
                        neighbour.addNeighbour(fieldNextToTheNeighbour, false);
                    }
                }
                // Left neighbour
                if (currentField.getPositionX() - 2 > 0) {
                    neighbour = this.layout[currentField.getPositionY()][currentField.getPositionX() - 1];
                    fieldNextToTheNeighbour = this.layout[currentField.getPositionY()][currentField.getPositionX() - 2];
                    if (neighbour.getCharRepresentation() == '#' && fieldNextToTheNeighbour.getCharRepresentation() != '#') {
                        currentField.addNeighbour(neighbour, false);
                        neighbour.addNeighbour(currentField, false);
                        neighbour.addNeighbour(fieldNextToTheNeighbour, false);
                    }
                }
                // Right neighbour
                if (currentField.getPositionX() + 2 < this.width - 1) {
                    neighbour = this.layout[currentField.getPositionY()][currentField.getPositionX() + 1];
                    fieldNextToTheNeighbour = this.layout[currentField.getPositionY()][currentField.getPositionX() + 2];
                    if (neighbour.getCharRepresentation() == '#' && fieldNextToTheNeighbour.getCharRepresentation() != '#') {
                        currentField.addNeighbour(neighbour, false);
                        neighbour.addNeighbour(currentField, false);
                        neighbour.addNeighbour(fieldNextToTheNeighbour, false);
                    }
                }
            }

            for (Field n : currentField.getAllNeighbours()) {
                if (!n.isVisited()) {
                    n.setVisited(true);
                    n.setPreviousField(currentField);
                    queue.add(n);
                }
            }
        }

        // Reconstruction of traveled route
        ArrayList<Field> bestRoute = new ArrayList<>();
        bestRoute.add(this.exit);
        while (!bestRoute.contains(this.entry)) {
            field = bestRoute.get(bestRoute.size() - 1);
            bestRoute.add(field.getPreviousField());
        }
        Collections.reverse(bestRoute);

        return bestRoute;
    }
}