*** *** *** *** *** *** *** ***
## Deutsche Tic-tac-Toe Game ##
*** *** *** *** *** *** *** *** 

## Features

- Two-player mode (Only): Players X and O take turns to place moves.
- Winner detection: The game checks for a winning combination after each move.
- Tie detection: The game checks for a tie if all positions are filled with no winner.
- Invalid move handling: The game handles invalid inputs and prevents moves in already occupied positions or after the game is won.
- Valid inputs: Input numbers between 1-9 into the console window.

## Requirements

- Java 8 or higher
- A terminal or command prompt
- I use Visual studio to run the program and tests, but any ide or text editor will work fine.
- Junit 4 and hamcrest for tests

## Project Structure

- TicTacToe.java: The class implementing the game logic.
- RunGame.java: Contains the main() method to run the game.
- TicTacToeTest.java: JUnit test cases for the game.