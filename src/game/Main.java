package game;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static final byte codeLength = 4;
    public static final int maxAttempts = 10;

    public static final char[] codeItems = {'R', 'G', 'B', 'Y', 'P', 'O'};

    static final MasterMind code = new MasterMind();

    public static void main(String[] args) {

        // Var/Object setup
        boolean error = false;

        char[] playerCode = null;
        char[] evaluation = null;

        Scanner sc = new Scanner(System.in);

        // Code generation
        char[] secretCode = code.generateCode();

        // Main game loop
        for (int i = 0; i < maxAttempts; i++) {
            code.displayBoard(playerCode, evaluation, i + 1);
            if (error) {
                System.out.println("That was not a valid input! Please try again...");
            } else {
                System.out.println("Enter your code, use spaces between the inputs...");
            }
            System.out.println("Choose from: " + Arrays.toString(codeItems));

            char[] savedPlayerCode = playerCode;
            playerCode = new char[codeLength];
            for (int j = 0; j < codeLength; j++) {
                playerCode[j] = Character.toUpperCase(sc.next().charAt(0));
            }

            // Continues without costing an attempt if the input is faulty
            error = !code.isValid(playerCode);
            if (error) {
                i--;
                playerCode = savedPlayerCode;
                continue;
            }
            evaluation = code.evaluate(playerCode, secretCode);
        }
        System.out.println("\r\nToo bad, the code was: \r\n" + Arrays.toString(secretCode));
        sc.close();
    }
}