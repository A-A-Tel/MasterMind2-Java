package game;

public class Main {


    public static void main(String[] args) {

        final MasterMind code = new MasterMind();
//        final Solver solver = new Solver();

        boolean hasWon = false;

        // Code generation
        code.secretCode = code.generateCode();

        // Main game loop
        for (int i = 0; i < code.maxAttempts; i++) {
            if (code.displayBoard(i + 1)) {
                hasWon = true;
                break; //// This way I let the program exit normally
            }

            // Takes the (user)input
            code.playerInput(); // Userinput

//            playerCode = code.generateCode(); // Random bruteforce
//            playerCode = solver.solve(evaluation, i); // Calculated solver

            // When you are using a bot, it is recommended to uncomment this code below
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
        if (hasWon) {
            System.out.println("\r\nToo bad, the code was:");
            code.printArray(code.secretCode);
        }
    }
}