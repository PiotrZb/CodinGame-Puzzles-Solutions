import java.util.Scanner;

class Solution {
    private static int width, height;
    private static String[] board;
    private static int[][] finalBoard;

    // reading data from input
    private static void readData() {
        Scanner scanner = new Scanner(System.in);

        width = scanner.nextInt();
        height = scanner.nextInt();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        board = new String[height];
        for (int i = 0; i < height; i++) {
            board[i] = scanner.nextLine();
        }

        scanner.close();
    }

    // finding final board
    private static void findFinalBoard() {
        // final board initialization
        finalBoard = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                finalBoard[i][j] = 0;
            }
        }

        // incrementing values of fields around mines
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i].charAt(j) == 'x') {
                    boolean topBorder = (i == 0);
                    boolean bottomBorder = (i == height - 1);
                    boolean leftBorder = (j == 0);
                    boolean rightBorder = (j == width - 1);

                    if (!topBorder) {
                        finalBoard[i - 1][j]++;

                        if (!leftBorder) {
                            finalBoard[i - 1][j - 1]++;
                        }

                        if (!rightBorder) {
                            finalBoard[i - 1][j + 1]++;
                        }
                    }

                    if (!bottomBorder) {
                        finalBoard[i + 1][j]++;

                        if (!leftBorder) {
                            finalBoard[i + 1][j - 1]++;
                        }

                        if (!rightBorder) {
                            finalBoard[i + 1][j + 1]++;
                        }
                    }

                    if (!leftBorder) {
                        finalBoard[i][j - 1]++;
                    }

                    if (!rightBorder) {
                        finalBoard[i][j + 1]++;
                    }
                }
            }
        }
    }

    // printing answer on standard output
    private static void printAnswer() {
        for (int i = 0; i < height; i++) {
            String line = "";
            for (int j = 0; j < width; j++) {
                int value = finalBoard[i][j];
                if (board[i].charAt(j) == 'x') {
                    line += ".";
                } else if (value != 0) {
                    line += String.valueOf(value);
                } else {
                    line += ".";
                }
            }

            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        readData();
        findFinalBoard();
        printAnswer();
    }
}