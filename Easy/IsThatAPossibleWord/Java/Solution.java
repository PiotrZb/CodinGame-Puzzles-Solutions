import java.util.Scanner;

class Solution {
    private static StateMachine stateMachine;
    private static String[] wordsToCheck;

    // reading data from standard input
    private static void readData(){
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        String[] statesList = scanner.nextLine().split(" ");

        int numberOfTransitions = scanner.nextInt();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        String[] transitions = new String[numberOfTransitions];
        for (int i = 0; i < numberOfTransitions; i++) {
            transitions[i] = scanner.nextLine();
        }

        String startState = scanner.nextLine();
        String[] endStates = scanner.nextLine().split(" ");
        int numberOfWords = scanner.nextInt();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        wordsToCheck = new String[numberOfWords];
        for (int i = 0; i < numberOfWords; i++) {
            wordsToCheck[i] = scanner.nextLine();
        }

        stateMachine = new StateMachine(input, statesList, transitions, startState, endStates);
    }

    public static void main(String[] args) {
        readData();
        for (String word : wordsToCheck){
            System.out.println(stateMachine.checkWord(word));
        }
    }
}