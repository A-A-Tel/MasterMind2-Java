package game;

import java.util.Arrays;

public class MasterMind {
    // Output the current board layout
    public void displayBoard(char[] playerCode, char[] evaluatedCode, int guessesLeft) {

        if (guessesLeft != -1) {
            System.out.println("\r\nGuesses: " + guessesLeft + "/" + Main.maxAttempts);
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
    public char[] evaluate(char[] playerCode) {

        int correctPositions = 0;
        char[] evaluation = new char[Main.codeLength];

        mainLoop:
        for (int i = 0; i < Main.codeLength; i++) {
            if (playerCode[i] == Main.secretCode[i]) {
                evaluation[i] = 'B';
                correctPositions++;
                continue;
            }
            for (char codeItem : Main.secretCode) {
                if (codeItem == playerCode[i]) {
                    evaluation[i] = 'W';
                    continue mainLoop;
                }
            }
            evaluation[i] = '-';
        }
        if (correctPositions == Main.codeLength) {
            displayBoard(playerCode, evaluation, -1);
        }
        return evaluation;
    }
}
