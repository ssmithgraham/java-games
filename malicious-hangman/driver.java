/* Sophie Smithgraham
 * 2021
 * Project 4: Malicious Hangman
 */

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		MaliciousHangman newGame = new MaliciousHangman();
		newGame.readFile();

		System.out.println("How many letters?");
		Scanner userInput = new Scanner(System.in);
		int key = userInput.nextInt();
		System.out.println("How many guesses?");
		int guesses = userInput.nextInt();
		int guessCount = 0;

		while (true) {
			newGame.checkLetters(key);
			if (newGame.checkLetters(key)) { //Winning state
				System.out.println("You win! Word was " + newGame.getAnswer(key));
				System.out.println("Type '1' to play again");
				int retry = userInput.nextInt();
			
				if (retry == 1) {
					newGame.resetGame(key);

					System.out.println("How many letters?");
					key = userInput.nextInt();
					System.out.println("How many guesses?");
					guesses = userInput.nextInt();
					guessCount = 0;
				} else {
					System.exit(0);
				}

			}
			if (guessCount >= guesses) { //Losing state
				System.out.println("No guesses left! Game over! Word was " + newGame.getAnswer(key));
				System.out.println("Type '1' to play again");
				int retry = userInput.nextInt();
			
				if (retry == 1) {
					newGame.resetGame(key);

					System.out.println("How many letters?");
					key = userInput.nextInt();
					System.out.println("How many guesses?");
					guesses = userInput.nextInt();
					guessCount = 0;
				} else {
					System.exit(0);
				}
			}

			newGame.printState(guesses, guessCount, key); //Prints state of the game
			char letter = userInput.next().charAt(0);

			if (newGame.usedLetters().contains(letter)) { //Checks if user's letter was already used
				System.out.println("Letter already used, pick a different letter");
			} else {
				guessCount++;
				if (newGame.guessLetter(letter, key)) { //Checks if user's guess was correct
					System.out.println("Contains " + letter + "!");
				} else {
					System.out.println("Does not contain " + letter);
				}
				System.out.println("====================================");

			}

		}

	}

}
