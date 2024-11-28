package game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MasterMind {
    public final char[] codeItems = {'R', 'G', 'B', 'Y', 'P', 'O'};
    public final int maxAttempts = 10;
    public final byte codeLength = 4;

    // Generate the code
    public char[] generateCode() {
        Random r = new Random();

        char[] generatedCode = new char[codeLength];

        for (int i = 0; i < codeLength; i++) {
            generatedCode[i] = codeItems[r.nextInt(codeItems.length)];
        }
        return generatedCode;
    }

    // Output the current board layout
    public void displayBoard(char[] playerCode, char[] evaluatedCode, int guessesLeft) {

        if (guessesLeft != -1) {
            System.out.println("\r\nGuesses: " + guessesLeft + "/" + maxAttempts);
        } else {
            System.out.println();
        }
        if (playerCode != null) {
            System.out.println(Arrays.toString(playerCode));
        }
        if (evaluatedCode != null) {
            System.out.println(Arrays.toString(evaluatedCode));
        }
        // Kills the process if the game has concluded
        if (guessesLeft == -1) {
            System.out.println("Congrats, You have cracked the code!");
            System.exit(0);
        }
    }

    // Take user-input
    public char[] playerInput() {
        Scanner sc = new Scanner(System.in);

        char[] input = new char[codeLength];

        for (int i = 0; i < codeLength; i++) {
            input[i] = Character.toUpperCase(sc.next().charAt(0));
        }
        return input;
    }

    // Checks if the current code is valid
    public boolean isValid(char[] playerCode) {

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
    public char[] evaluate(char[] playerCode, char[] secretCode) {

        int correctPositions = 0;
        char[] evaluation = new char[codeLength];

        for (int i = 0; i < codeLength; i++) {
            if (playerCode[i] == secretCode[i]) {
                evaluation[i] = 'B';
                correctPositions++;
                continue;
            }
            evaluation[i] = '-';

            for (char codeItem : secretCode) {
                if (codeItem == playerCode[i]) {
                    evaluation[i] = 'W';
                    break;
                }
            }
        }
        if (correctPositions == codeLength) {
            displayBoard(playerCode, evaluation, -1);
        }
        return evaluation;
    }
}
