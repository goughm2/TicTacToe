import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicTacToeTest {

    private TicTacToe game;

    @Before
    public void setUp() {
        game = new TicTacToe();  // Create a new game before each test
    }

    @Test
    public void testInitialBoardIsEmpty() {
        for (int i = 1; i <= 9; i++) {
            assertEquals("Board should be initialized with '-'", '-', (char) game.board.get(i));
        }
    }

    @Test
    public void testPlaceMoveValid() {
        assertTrue(game.placeMove(1));
        assertEquals("First move should be X on position 1", 'X', (char) game.board.get(1));
    }

    @Test
    public void testPlaceMoveInvalidPosition() {
        assertFalse("Move on position 10 should be invalid", game.placeMove(10));
        assertFalse("Move on position 0 should be invalid", game.placeMove(0));
    }

    @Test
    public void testPlaceMoveOccupiedPosition() {
        game.placeMove(1);
        assertFalse("Move on an occupied position should be invalid", game.placeMove(1));
    }

    @Test
    public void testSwitchPlayer() {
        game.switchPlayer();
        assertEquals("Player should switch from X to O", 'O', game.currentPlayer);
        game.switchPlayer();
        assertEquals("Player should switch from O back to X", 'X', game.currentPlayer);
    }

    @Test
    public void testCheckWinnerHorizontal() {
        game.placeMove(1); // X
        game.placeMove(4); // O
        game.placeMove(2); // X
        game.placeMove(5); // O
        game.placeMove(3); // X
        assertEquals("X should win with a horizontal line on top row", 'X', (char) game.checkWinner());
    }

    @Test
    public void testCheckWinnerVertical() {
        game.placeMove(1); // X
        game.placeMove(2); // O
        game.placeMove(4); // X
        game.placeMove(3); // O
        game.placeMove(7); // X
        assertEquals("X should win with a vertical line in the first column", 'X', (char) game.checkWinner());
    }

    @Test
    public void testCheckWinnerDiagonal() {
        game.placeMove(1); // X
        game.placeMove(2); // O
        game.placeMove(5); // X
        game.placeMove(3); // O
        game.placeMove(9); // X
        assertEquals("X should win with a diagonal from top-left to bottom-right", 'X', (char) game.checkWinner());
    }

    @Test
    public void testCheckTie() {
        // Fill the board without a winner
        game.placeMove(1); // X    
        game.placeMove(2); // O      
        game.placeMove(3); // X      
        game.placeMove(5); // O
        game.placeMove(4); // X
        game.placeMove(6); // O
        game.placeMove(8); // X
        game.placeMove(7); // O
        game.placeMove(9); // X
    
        // Verify there is no winner
        Character winner = game.checkWinner();
        assertNull("There should be no winner", winner);
        // Verify that the game is a tie
        assertTrue("The game should be a tie", game.isTie());
    }

    @Test
    public void testGameEndsWhenThereIsAWinner() {
        game.placeMove(1); // X
        game.placeMove(4); // O
        game.placeMove(2); // X
        game.placeMove(5); // O
        game.placeMove(3); // X
        
        // Check that X is the winner
        assertEquals("X should be the winner", 'X', (char) game.checkWinner());
        // Attempt to place another move after the game is won
        assertFalse("No more moves should be allowed after the game is won", game.placeMove(6));
    }
}
