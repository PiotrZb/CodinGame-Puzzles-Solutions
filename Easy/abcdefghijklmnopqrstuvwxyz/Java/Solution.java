import java.util.Scanner;

class Solution {

    // data reading from standard input
    public static char[][] readData(){
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        char[][] inputString = new char[size][size];
        for (int i = 0; i < size; i++) {
            inputString[i] = scanner.next().toCharArray();
        }
        return inputString;
    }
    
    public static void main(String[] args) {
        char[][] inputString = readData();
        Graph graph = new Graph(inputString);
        graph.solve();
        graph.printAnswer();
    }
}