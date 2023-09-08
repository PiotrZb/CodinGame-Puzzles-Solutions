import java.util.Scanner;

class Solution {

    private final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String rotor1, rotor2, rotor3, answer = "", operation, message;
    private static int shiftNumber;

    // reading data from input
    private static void readData() {
        Scanner scanner = new Scanner(System.in);
        operation = scanner.nextLine();
        shiftNumber = scanner.nextInt();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        rotor1 = scanner.nextLine();
        rotor2 = scanner.nextLine();
        rotor3 = scanner.nextLine();
        message = scanner.nextLine();
        scanner.close();
    }

    // encryption
    private static void encode() {
        for (int i = 0; i < message.length(); i++) {
            // cesar shift
            int index = alphabet.indexOf(message.charAt(i)) + shiftNumber + i;

            if (index >= alphabet.length()) {
                index = index % alphabet.length();
            }

            // rotor 1
            System.err.println(alphabet.length() - 1);
            System.err.println(index);
            char c = rotor1.charAt(index);
            index = alphabet.indexOf(c);

            // rotor 2
            c = rotor2.charAt(index);
            index = alphabet.indexOf(c);

            // rotor 3
            c = rotor3.charAt(index);
            answer += c;
        }
    }

    // decryption
    private static void decode() {
        for (int i = 0; i < message.length(); i++) {
            // rotor 3
            int index = rotor3.indexOf(message.charAt(i));
            char c = alphabet.charAt(index);

            // rotor 2
            index = rotor2.indexOf(c);
            c = alphabet.charAt(index);

            // rotor 1
            index = rotor1.indexOf(c);
            c = alphabet.charAt(index);

            // cesar shift
            index = alphabet.indexOf(c) - ((shiftNumber + i) % alphabet.length());

            if (index < 0) {
                index = alphabet.length() + index;
            }

            answer += alphabet.charAt(index);
        }
    }

    private static void generateAnswer() {
        if (operation.equals("ENCODE")) {
            encode();
        } else {
            decode();
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    public static void main(String[] args) {
        readData();
        generateAnswer();
        printAnswer();
    }
}