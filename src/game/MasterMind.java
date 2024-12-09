package game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MasterMind {

    public final char[] codeItems = {'R', 'G', 'B', 'Y', 'P', 'O'}; // This needs to be public for the Solver class
    public final int maxAttempts = 10;
    public final byte codeLength = 4; // This needs to be public for the Solver class, otherwise it can be private.
    public char[] secretCode;

    private char[] playerCode;
    private char[] evaluation;

    // Generate the code
    public char[] generateCode() {
        Random r = new Random();

        char[] generatedCode = new char[codeLength];

        for (int i = 0; i < codeLength; i++) {
            generatedCode[i] = codeItems[r.nextInt(codeItems.length)];
        }
        return generatedCode;
    }

    public void printArray(char[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // Output the current board layout
    public boolean displayBoard(int guessesLeft) {

        if (guessesLeft != -1) {
            System.out.println("\r\nGuesses: " + guessesLeft + "/" + maxAttempts);
        } else {
            System.out.println();
        }

        if (evaluation != null) { // No need to check the other array, it will have an eval when it has a player code.
            printArray(playerCode);
            printArray(evaluation);
        }

        // Kills the process if the game has concluded
        if (guessesLeft == -1) {
            System.out.println("Congrats, You have cracked the code!");
            return true;
        }
        return false;
    }

    // Take user-input
    public void playerInput() {
        Scanner sc = new Scanner(System.in);

        char[] input = new char[codeLength];

        do {
            for (int i = 0; i < codeLength; i++) {
                input[i] = Character.toUpperCase(sc.next().charAt(0));
            }
        } while (isValid(input));

        playerCode = input;
        evaluate();
    }

    // Evaluates the code based on MasterMind rules, also checks if the player has won
    public void evaluate() {

        int correctPositions = 0;
        char[] evaluation = new char[codeLength];

        for (int i = 0; i < codeLength; i++) {
            if (playerCode[i] == secretCode[i]) {
                evaluation[i] = 'B';
                correctPositions++;

            } else {
                evaluation[i] = '-';

                for (char codeItem : secretCode) {
                    if (codeItem == playerCode[i]) {
                        evaluation[i] = 'W';
                        break;
                    }
                }
            }
        }
        this.evaluation = evaluation;

        if (correctPositions == codeLength) {
            displayBoard(-1);
        }
    }

    // Checks if the current code is valid
    private boolean isValid(char[] playerCode) {

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
}
