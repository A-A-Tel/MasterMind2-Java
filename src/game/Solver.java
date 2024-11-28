package game;

import java.util.Arrays;

public class Solver {

    final MasterMind code = new MasterMind();

    public char[] previousInput = new char[code.codeLength];

    public char[] solve(char[] evaluation, int guessesLeft) {

        // Fills the array with the next color item batch
        char[] input = new char[code.codeLength];
        Arrays.fill(input, code.codeItems[guessesLeft]);

        // Fills in the previous correct inputs
        if (evaluation != null) {
            for (int i = 0; i < code.codeLength; i++) {
                if (evaluation[i] == 'B') {
                    input[i] = previousInput[i];
                }
            }
        }
        previousInput = input;
        return input;
    }
}
