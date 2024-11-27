package game;

import java.util.Arrays;

public class Main {

    public static final byte codeLength = 4;
    public static final int maxAttempts = 10;

    public static final char[] codeItems = {'R', 'G', 'B', 'Y', 'P', 'O'};

    static final MasterMind code = new MasterMind();
    static final Solver solver = new Solver();

    public static void main(String[] args) {

        // Var/Object setup
        boolean error = false;

        char[] playerCode = null;
        char[] evaluation = null;

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

            // Saves the previous input in case it is invalid
            char[] savedPlayerCode = playerCode;

            // Takes the (user)input
            playerCode = code.playerInput(); // Userinput
//            playerCode = code.generateCode(); // Random bruteforce
//            playerCode = solver.solve(evaluation, i); // Calculated solver

            // Continues without costing an attempt if the input is faulty
            error = !code.isValid(playerCode);
            if (error) {

                i--;
                playerCode = savedPlayerCode;
                continue;
            }
            evaluation = code.evaluate(playerCode, secretCode);
            
            // When you are using a bot, it is recommended to uncomment this code below
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
        System.out.println("\r\nToo bad, the code was: \r\n" + Arrays.toString(secretCode));
    }
}