package game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final byte codeLength = 4;
    public static final int maxAttempts = 10;

    public static final char[] secretCode = new char[codeLength];
    public static final char[] codeItems = {'R', 'G', 'B', 'Y', 'P', 'O'};

    // Output the current board layout
    public static void displayBoard(char[] playerCode, char[] evaluatedCode, int guessesLeft, boolean hasWon) {

        if (guessesLeft != -1) {
            System.out.println("Guesses: " + guessesLeft + "/" + maxAttempts);
        }
        if (playerCode != null) {
            System.out.println(Arrays.toString(playerCode));
        }
        if (evaluatedCode != null) {
            System.out.println(Arrays.toString(evaluatedCode));
        }
        // Kills the process if the game has concluded
        if (hasWon) {
            System.out.println("Congrats, You have cracked the code!");
            System.exit(0);
        }
    }

    // Checks if the current code is valid
    public static boolean isValid(char[] playerCode) {

        for (char playerCodeItem : playerCode) {
            boolean isPresent = false;
            for (char codeItem : codeItems) {
                if (playerCodeItem == codeItem) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                return false;
            }
        }
        return true;
    }

    // Evaluates the code based on MasterMind rules, also checks if the player has won
    public static char[] evaluate(char[] playerCode) {

        int correctPositions = 0;
        char[] evaluation = new char[codeLength];

        mainLoop:
        for (int i = 0; i < codeLength; i++) {
            if (playerCode[i] == secretCode[i]) {
                evaluation[i] = 'B';
                correctPositions++;
                continue;
            }
            for (char codeItem : secretCode) {
                if (codeItem == playerCode[i]) {
                    evaluation[i] = 'W';
                    continue mainLoop;
                }
            }
            evaluation[i] = '-';
        }
        if (correctPositions == codeLength) {
            displayBoard(playerCode, evaluation, -1, true);
        }
        return evaluation;
    }

    public static void main(String[] args) {

        boolean error = false;

        char[] playerCode = null;
        char[] evaluation = null;

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        // Generate the code
        for (int i = 0; i < codeLength; i++) {
            secretCode[i] = codeItems[r.nextInt(codeItems.length)];
        }

        // Main game loop
        for (int i = 0; i < maxAttempts; i++) {
            displayBoard(playerCode, evaluation, i + 1, false);
            if (error) {
                System.out.println("That was not a valid input! Please try again...");
            } else {
                System.out.println("Enter your code...");
            }
            System.out.println("Choose from: " + Arrays.toString(codeItems));

            char[] savedPlayerCode = playerCode;
            playerCode = new char[codeLength];
            for (int j = 0; j < codeLength; j++) {
                playerCode[j] = Character.toUpperCase(sc.next().charAt(0));
            }

            // Continues without costing an attempt if the input is faulty
            error = !isValid(playerCode);
            if (error) {
                i--;
                playerCode = savedPlayerCode;
                continue;
            }
            evaluation = evaluate(playerCode);
        }
        sc.close();
    }
}