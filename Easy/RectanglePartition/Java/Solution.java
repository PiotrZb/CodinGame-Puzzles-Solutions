import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {

    private static Scanner scanner;
    private static int w, h, countX, countY, answer;
    private static int[] arrayX, arrayY;
    private static List<Integer> lengthsX, lengthsY;

    // reading data from input
    private static void readData() {
        scanner = new Scanner(System.in);
        w = scanner.nextInt();
        h = scanner.nextInt();
        countX = scanner.nextInt();
        countY = scanner.nextInt();

        arrayX = new int[countX + 2];
        arrayX[0] = 0;
        arrayX[countX + 1] = w;

        for (int i = 1; i <= countX; i++) {
            arrayX[i] = scanner.nextInt();
        }

        arrayY = new int[countY + 2];
        arrayY[0] = 0;
        arrayY[countY + 1] = h;

        for (int i = 1; i <= countY; i++) {
            arrayY[i] = scanner.nextInt();
        }
    }

    // finding all possible lengths in X and Y direction
    private static void findLengths() {
        lengthsX = new ArrayList<Integer>();
        lengthsY = new ArrayList<Integer>();

        for (int i = 0; i < arrayX.length; i++) {
            for (int z = arrayX.length - 1; z > i; z--) {
                int length = arrayX[z] - arrayX[i];
                lengthsX.add(length);
            }
        }

        for (int i = 0; i < arrayY.length; i++) {
            for (int z = arrayY.length - 1; z > i; z--) {
                int length = arrayY[z] - arrayY[i];
                lengthsY.add(length);
            }
        }
    }

    // counting all possible squares
    public static void countSquares() {
        answer = 0;

        for (int lenX : lengthsX) {
            for (int lenY : lengthsY) {
                if (lenY == lenX) {
                    answer++;
                }
            }
        }
    }

    // answer
    public static void printAnswer() {
        System.out.println(answer);
    }

    public static void main(String[] args) {
        readData();
        findLengths();
        countSquares();
        printAnswer();
    }
}