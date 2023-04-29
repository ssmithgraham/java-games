/* Sophie Smithgraham
 *2021
 * Project 4: Malicious Hangman
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class MaliciousHangman {
	private HashMap<Integer, ArrayList<String>> words = new HashMap<>();
	private ArrayList<String> removedWords = new ArrayList<>();
	private HashSet<Character> usedLetters = new HashSet<>();
	private String answer;
	
	public void readFile() {

		try {
			Scanner fileReader = new Scanner(new FileInputStream("Dictionary.txt"));

			while (fileReader.hasNextLine()) {
				String word = fileReader.nextLine();
				if (words.containsKey(word.length()) == true) { // if bucket already exists
					words.get(word.length()).add(word); // add the word to the bucket

				} else {
					ArrayList<String> wordGroup = new ArrayList<>(); // otherwise make new bucket
					wordGroup.add(word); // add word to bucket
					words.put(word.length(), wordGroup); // add bucket to map
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void removeLetter(char guess) {
		usedLetters.add(guess); // Marks letters as used
	}

	public void resetGame(int key) {
		for (int i = 0; i < removedWords.size(); i++) { // resets answer, removedWords, and usedLetters
			words.get(key).add(removedWords.get(i));// adds removed words back into list
		}
		answer = null;
		removedWords.clear();
		usedLetters.clear();
	}

	public HashSet<Character> usedLetters() {
		return usedLetters;
	}

	public String getAnswer(int key) {
		if (answer == null) {
			int index = (int) ((Math.random() * words.get(key).size()));

			answer = words.get(key).get(index);

		}
		return answer;
	}

	public boolean checkLetters(int key) {
		if (answer == null) {
			return false;
		}
		// if usedLetters contains answerLetters, the word has been solved; return true
		char[] answerArray = answer.toCharArray();
		HashSet<Character> answerLetters = new HashSet<>();
		for (int i = 0; i < answer.length(); i++) {
			answerLetters.add(answerArray[i]);
		}

		if (usedLetters.containsAll(answerLetters)) {
			return true;
		} else {
			return false;
		}
	}

	public void printState(int g, int gc, int key) { // Prints the state of the game

		if (answer != null) {
			char[] answerLetters = answer.toCharArray();
			char[] correctLetters = new char[key];
			for (int i = 0; i < answer.length(); i++) {
				if (usedLetters.contains(answerLetters[i])) {
					correctLetters[i] = answerLetters[i];
				}
			}
			for (int i = 0; i < key; i++) {
				if (correctLetters[i] == 0) {
					System.out.print("_ ");
				} else {
					System.out.print(correctLetters[i]);
				}

			}
		} else {
			for (int i = 0; i < key; i++) {
				System.out.print("_ ");
			}
		}

		System.out.print(" | Guesses left: " + (g - gc));
		System.out.println(" | Used letters: " + usedLetters());
		System.out.println("Choose a letter:");
	}

	public boolean guessLetter(char guess, int key) {
		//Checks if user's guessed letter matches with words in the list and, if able, removes them
		//Returns true if user guessed correct, returns false if user guess was incorrect
		
		removeLetter(guess); // adds letter to usedLetters
		Iterator<String> it = words.get(key).iterator();
		int toRem = 0;

		if (answer == null) { //If computer has not picked a word as the answer
			while (it.hasNext()) { // iterate through certain bucket
				String a = it.next().toLowerCase();
				for (int i = 0; i < a.length(); i++) {// iterate through letters in each word
					if (a.charAt(i) == guess) { // checking if guessed letter matches with letters in list
						toRem++;
						break;
					}
				}
			}
		
			// Checks if removing is valid; if negative, there are no words remaining to
			// remove and computer must pick a random word to as the answer
			if (words.get(key).size() - toRem <= 0) {
				
				int index = (int) ((Math.random() * words.get(key).size()));

				answer = words.get(key).get(index);
				return true;
			} else { //Otherwise, iterates through words and removes them from list
				Iterator<String> itAgain = words.get(key).iterator();
				while (itAgain.hasNext()) { 
					String a = itAgain.next().toLowerCase();
					for (int i = 0; i < a.length(); i++) {
						if (a.charAt(i) == guess) {
							itAgain.remove();
							removedWords.add(a);
							break;
						}
					}
				}
				return false; 
			}

		} else { //If computer has chosen a word, play game normally
			for (int i = 0; i < key; i++) {
				if (answer.charAt(i) == guess) {
					return true;
				}
			}
			return false;

		}

	}

}
