import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {

    private static Scanner scanner;
    private static int numberOfLines, playerPosition;
    private static String[] board;
    private static List<int[]> distances;

    private static void shoot() {
        System.out.println("SHOOT");
    }

    private static void waitTurn() {
        System.out.println("WAIT");
    }

    private static void findLauncherPosition() {
        playerPosition = board[numberOfLines - 1].indexOf('^');
    }

    // reading board from input
    private static void readData() {
        scanner = new Scanner(System.in);
        numberOfLines = scanner.nextInt();
        board = new String[numberOfLines];

        for (int i = 0; i < numberOfLines; i++) {
            String line = scanner.next();
            board[i] = line;
        }
    }

    // finding all aircraft and their distance to launcher in X and Y axes
    private static void findDistances() {
        distances = new ArrayList<int[]>();

        for (int i = 0; i < board.length; i++) {
            String line = board[i];

            for (int z = 0; z < line.length(); z++) {
                if (line.charAt(z) == '>') {
                    int distanceX = Math.abs(playerPosition - z);
                    int distanceY = Math.abs(i - (board.length));
                    distances.add(new int[]{distanceX, distanceY});
                } else if (line.charAt(z) == '<') {
                    int distanceX = Math.abs(playerPosition - z);
                    int distanceY = Math.abs(i - (board.length));
                    distances.add(new int[]{distanceX, distanceY});
                }
            }
        }
    }

    // printing instructions to standard output
    private static void printAnswer() {
        while (!distances.isEmpty()) {
            boolean shoot = false;

            for (int[] pair : distances) {
                if (pair[0] == pair[1]) {
                    shoot = true;
                    distances.remove(pair);
                    break;
                }
            }

            // instructions
            if (shoot) {
                shoot();
            } else {
                waitTurn();
            }

            // movement simulation
            for (int[] pair : distances) {
                pair[0]--;
            }
        }
    }

    public static void main(String[] args) {
        readData();
        findLauncherPosition();
        findDistances();
        printAnswer();
    }
}