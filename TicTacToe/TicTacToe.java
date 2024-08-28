import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class TicTacToe {

    // Global variables
    public Map<Integer, Character> board;
    public char currentPlayer;
    public boolean gameWon;
    
    // Constructor to initialise blank board ('-') and sets the starting player to 'X'
    public TicTacToe() {
        board = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            board.put(i, '-');
        }
        currentPlayer = 'X';
        gameWon = false;
    }

    // Method to run the game loop
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to 3x3 Tic-Tac-Toe!");
        System.out.println("The template board bellow shows the placement position identifiers");
        printBoard(true);

        for (int turn = 0; turn < 9; turn++) {
            boolean validMove = false;

            while (!validMove) {
                System.out.println("Player " + currentPlayer + ", enter your move (1-9): ");
                try {
                    int position = scanner.nextInt();
                    if (placeMove(position)) {
                        validMove = true;
                    } else { // If move is invalid print the reason
                        System.out.println("Invalid input. Enter a number between 1 and 9.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Enter a number between 1 and 9.");
                    scanner.next(); // Clear the invalid input
                }
            }
            // Reprint the board with new placements
            printBoard(false);

            // Check if the new input has resulted in a winnier
            Character winner = checkWinner();
            if (winner != null) {
                System.out.println("The winner is: " + winner);
                gameWon = true;
                break; // If a winnier has been identified, break from the method.
            }
        }

        if (!gameWon && isTie()) {
            System.out.println("It's a tie!");
        }
        scanner.close();
    }

    // Method to place a move on the board
    public boolean placeMove(int position) {
        if (gameWon || position < 1 || position > 9 || board.get(position) != '-') {
            return false; // Invalid move
        }
        board.put(position, currentPlayer);
        switchPlayer();
        // Check if the current move wins the game
        if (checkWinner() != null) {
            gameWon = true; // Update gameWon if the current move wins the game
        }
        return true;
    }

    // Method to check for a winner or return null if no winner
    public Character checkWinner() {
        // Check rows (horizontal)
        for (int horizontal = 1; horizontal <= 7; horizontal += 3) {
            if (board.get(horizontal) == board.get(horizontal + 1) &&
                board.get(horizontal) == board.get(horizontal + 2) &&
                board.get(horizontal) != '-') {
                return board.get(horizontal);
            }
        }
    
        // Check columns (vertical)
        for (int vertical = 1; vertical <= 3; vertical++) {
            if (board.get(vertical) == board.get(vertical + 3) &&
                board.get(vertical) == board.get(vertical + 6) &&
                board.get(vertical) != '-') {
                return board.get(vertical);
            }
        }
    
        // Check diagonals
        if (board.get(1) == board.get(5) && 
            board.get(1) == board.get(9) && 
            board.get(1) != '-') {
            return board.get(1);
        }
        if (board.get(3) == board.get(5) && 
            board.get(3) == board.get(7) && 
            board.get(3) != '-') {
            return board.get(3);
        }
    
        // No winner found
        return null;
    }
    
    // Method to switch the current player
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Method to check if the game is a tie
    public boolean isTie() {
        for (int i = 1; i <= 9; i++) {
            if (board.get(i) == '-') {
                return false;  // There's at least one empty spot, so not a tie
            }
        }
        return true; // No empty spots, so it's a tie
    }

    // Method to print the board
    public void printBoard(boolean isInitialPrint) {
        for (int i = 1; i <= 9; i++) {
            System.out.print(isInitialPrint ? i + " " : board.get(i) + " "); // If initial, print number i
            if (i % 3 == 0) {
                System.out.println();
            }
        }
    }
}
