package game;

import java.util.Arrays;

public class Solver {

    public char[] previousInput = new char[Main.codeLength];

    public char[] solve(char[] evaluation, int guessesLeft) {

        // Fills the array with the next color item batch
        char[] input = new char[Main.codeLength];
        Arrays.fill(input, Main.codeItems[guessesLeft]);

        // Fills in the previous correct inputs
        if (evaluation != null) {
            for (int i = 0; i < Main.codeLength; i++) {
                if (evaluation[i] == 'B') {
                    input[i] = previousInput[i];
                }
            }
        }
        previousInput = input;
        return input;
    }
}
