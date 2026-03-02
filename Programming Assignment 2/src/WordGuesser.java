// WordGuesser.java
// Software Design
// Programming Assignment 2
// Last Updated 17 February 2026

import java.util.Scanner;  // Import Scanner for user input
import java.util.Random;  // Used for RNG in getWord()
import java.util.ArrayList; // For collecting match details

/*
This program is a word guessing game.
The program selects a word at random and asks the user to guess.
The user is allowed unlimited guesses. Each guess will end with an output showing how many letters were correct and how many letters are in the right spot.
This program supports multiple word lengths.
 */

public class WordGuesser {
    public static void main(String[] args) {
        // Variables and objects
        String answer = getWord().toUpperCase();  // The answer word  
        int correctPlacement = -1;  // How many letters are in the right place, our loop counter
        int spaceNum = answer.length();  // How long the answer word is
        Scanner userIn = new Scanner(System.in);  // User input object

        while (correctPlacement != spaceNum) {
            System.out.println("Enter a " + spaceNum + " letter word: ");
            String guess = userIn.nextLine().toUpperCase();  // Get user input

            // Input verification
            while (guess.length() != spaceNum) {  // Guess is not the same length as answer
                // Inform user input is incorrect and request new input
                System.out.println("That word does not contain " + spaceNum + " characters. Try again!");
                guess = userIn.nextLine().toUpperCase();
            }

            // These reset at every guess
            correctPlacement = 0;
            boolean[] guessUsed = new boolean[spaceNum];  // Mark off characters in guess to avoid double counting
            boolean[] answerUsed = new boolean[spaceNum];  // Mark off characters in answer to avoid double counting

            // Collections to hold descriptive match information
            ArrayList<String> exactMatches = new ArrayList<>();  // Right letter, right spot
            ArrayList<String> presentMatches = new ArrayList<>();  // Right letter, wrong spot

            // Find correct letters in correct spots
            for (int i = 0; i < spaceNum; i++) {  // Iterate over guess, first pass
                char c = guess.charAt(i);

                if (c == answer.charAt(i)) {
                    correctPlacement++;  // Update loop counter
                    guessUsed[i] = true;
                    answerUsed[i] = true;
                    exactMatches.add(c + " at position " + (i + 1) + ": correct");
                }
            }

            // Find correct letters in wrong spots (record the actual answer position too)
            for (int i = 0; i < spaceNum; i++) {  // Iterate over guess, second pass
                if (!guessUsed[i]) {  // Guess has not been used
                    for (int n = 0; n < spaceNum; n++) {  // Iterate over answer
                        if (!answerUsed[n] && guess.charAt(i) == answer.charAt(n)) {  // Unused answer letter matches current guess letter
                            answerUsed[n] = true;
                            guessUsed[i] = true;
                            presentMatches.add(guess.charAt(i) + " is a correct letter but at position " + (i + 1) + " is incorrect");
                            break;
                        }
                    }
                }
            }

            // Output descriptive results
            giveOutput(exactMatches, presentMatches);
        }

        // If the loop is exited, that should mean the user successfully guessed the word
        System.out.println("Success!");

        userIn.close();  // Close Scanner
    }

    // Method for giving descriptive output to user
    static void giveOutput(ArrayList<String> exactMatches, ArrayList<String> presentMatches) {
        if (exactMatches.isEmpty() && presentMatches.isEmpty()) {
            System.out.println("No matching letters.");
        } else {
            if (!exactMatches.isEmpty()) {
                for (String s : exactMatches) {
                    System.out.println(s);
                }
            }
            if (!presentMatches.isEmpty()) {
                for (String s : presentMatches) {
                    System.out.println(s);
                }
            }
        }
    }

    // Method to get the answer word
    static String getWord() {
        String[] wordLib = {"ALIKE", "APPLE", "BERRY", "WATER", "PHONE", "SWEET", "POWER", "WEIGH", "GRACE", "PLACE"};
        Random rand = new Random();  // RNG to select from wordLib
        return wordLib[rand.nextInt(wordLib.length)];  // Return a random word from wordLib
    }
}
