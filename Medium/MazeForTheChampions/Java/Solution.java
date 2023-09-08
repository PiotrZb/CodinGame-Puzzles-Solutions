import java.util.Scanner;
import java.util.ArrayList;

class Solution {

    // Reading data from standard input
    public static char[][] readMazeLayout() {
        Scanner standardInputScanner = new Scanner(System.in);
        int width = standardInputScanner.nextInt();
        int height = standardInputScanner.nextInt();
        char[][] layout = new char[height][width];
        String line;
        for (int i = 0; i < height; i++) {
            line = standardInputScanner.next();
            layout[i] = line.toCharArray();
        }
        return layout;
    }

    public static void main(String[] args) {
        char[][] mazeLayout = readMazeLayout();
        ArrayList<Object> bestSolution = null;

        // Searching for best solution
        for (Hero hero : Hero.values()) {
            Maze maze = new Maze(mazeLayout);
            if (bestSolution == null) {
                bestSolution = maze.solve(hero);
            } else {
                ArrayList<Object> solution = maze.solve(hero);
                if ((int) solution.get(1) < (int) bestSolution.get(1)) {
                    bestSolution = solution;
                }
            }
        }

        // Printing answer on standard output
        System.out.println(bestSolution.get(0) + " " + bestSolution.get(1));
        System.out.println(bestSolution.get(2));
    }
}