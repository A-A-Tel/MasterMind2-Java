package game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MasterMind {

    // Generate the code
    public char[] generateCode() {
        Random r = new Random();

        char[] generatedCode = new char[Main.codeLength];

        for (int i = 0; i < Main.codeLength; i++) {
            generatedCode[i] = Main.codeItems[r.nextInt(Main.codeLength)];
        }
        return generatedCode;
    }

    // Output the current board layout
    public void displayBoard(char[] playerCode, char[] evaluatedCode, int guessesLeft) {

        if (guessesLeft != -1) {
            System.out.println("\r\nGuesses: " + guessesLeft + "/" + Main.maxAttempts);
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

        char[] input = new char[Main.codeLength];

        for (int i = 0; i < Main.codeLength; i++) {
            input[i] = Character.toUpperCase(sc.next().charAt(0));
        }
        return input;
    }

    // Checks if the current code is valid
    public boolean isValid(char[] playerCode) {

        for (char playerCodeItem : playerCode) {
            boolean isPresent = false;
            for (char codeItem : Main.codeItems) {
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
        char[] evaluation = new char[Main.codeLength];

        for (int i = 0; i < Main.codeLength; i++) {
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
        if (correctPositions == Main.codeLength) {
            displayBoard(playerCode, evaluation, -1);
        }
        return evaluation;
    }
}
