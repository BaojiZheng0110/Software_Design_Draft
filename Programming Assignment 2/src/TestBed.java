// TestBed.java
// Programming Assignment 2
// Software Design
// Author: Alex Warren
// Date Last Modified: 19 February 2026

/*
This class is a test harness for WordGuesserLogic.java
 */

import java.util.Scanner;

public class TestBed {
    static void main(String[] args) {
        WordGuesserLogic game = new WordGuesserLogic();
        Scanner userIn = new Scanner(System.in);  // Get user input

        System.out.println("BEGIN TEST");
        System.out.println("The secret word has " + game.getSpaceNum() + " letters.");

        boolean won = false;
        while (!won) {
            System.out.println("\n Enter your guess:");
            String guess = userIn.nextLine().toUpperCase();  // Get user input

            // Input verification
            while (guess.length() != game.getSpaceNum()) {  // Guess is not the same length as answer
                // Inform user input is incorrect and request new input
                System.out.println("That word does not contain " + game.getSpaceNum() + " characters. Try again!");
                guess = userIn.nextLine().toUpperCase();
            }

            // Logic testing
            GuessResult result = game.checkGuess(guess);

            // Feedback testing
            System.out.println("Printing feedback...");
            if (result.getFeedback().isEmpty()) {
                System.out.println("No matching letters.");
            }
            else {
                for (String s : result.getFeedback()) {
                    System.out.println(s);
                }
            }

            won = result.getIsWin();
        }

        // If the loop is exited, that should mean the user successfully guessed the word
        System.out.println("Success!");

        userIn.close();  // Close Scanner
    }
}
