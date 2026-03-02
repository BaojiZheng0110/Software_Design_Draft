// WordGuesserLogic.java
// Programming Assignment 2
// Software Design
// Author: Alex Warren
// Date Last Modified: 19 February 2026

import java.io.*;  // File handling, reading, and exception handling
import java.util.Random;  // Used for RNG in getWord()
import java.util.ArrayList; // For collecting match details


public class WordGuesserLogic {
    // Instance variables
    private final String answer;
    private final int spaceNum;

    // Class constructor
    public WordGuesserLogic() {
        this.answer = getWord().toUpperCase();
        this.spaceNum = answer.length();
    }

    // Game logic method
    public GuessResult checkGuess (String guess) {
        guess = guess.toUpperCase();  // This prevents case-sensitivity issues
        int correctPlacement = 0;  // Number of letters in the right spot
        boolean[] guessUsed = new boolean[spaceNum];  // Mark off characters in guess to avoid double counting
        boolean[] answerUsed = new boolean[spaceNum];  // Mark off characters in answer to avoid double counting

        // One collection to hold feedback for the user
        ArrayList<String> feedback = new ArrayList<>();

        // First pass: Find correct letters in correct spots
        for (int i = 0; i < spaceNum; i++) {  // Iterate over guess
            if (guess.charAt(i) == answer.charAt(i)) {
                correctPlacement++;  // Update loop counter
                guessUsed[i] = true;
                answerUsed[i] = true;
                feedback.add(guess.charAt(i) + " at position " + (i + 1) + ": correct");
            }
        }

        // Second pass: Correct letters in wrong spots
        for (int i = 0; i < spaceNum; i++) {  // Iterate over guess
            if (!guessUsed[i]) {  // Guess has not been used
                for (int j = 0; j < spaceNum; j++) {  // Iterate over answer
                    if (!answerUsed[j] && guess.charAt(i) == answer.charAt(j)) {  // Unused answer detected
                        answerUsed[j] = true;
                        guessUsed[i] = true;
                        feedback.add(guess.charAt(i) + " is a correct letter but at position " + (i + 1) + " is incorrect");
                    }
                }
            }
        }

        boolean isWin = (correctPlacement == spaceNum);

        return new GuessResult(feedback, isWin, answer, guess);
    }

    // Getter methods
    public int getSpaceNum() {
        return spaceNum;
    }

    // Method to get the answer word
    static String getWord() {
        // Attempt to open the WordFile to pull a random word from
        try (BufferedReader reader = new BufferedReader(new FileReader("WordFile.txt"))) {
            String currentLine = null;  // To store the random line
            String line;
            int lineCount = 0;  // Tracks the current line number
            Random random = new Random();  // RNG for the random word

            while ((line = reader.readLine()) != null) {
                lineCount++;  // Increment the line count
                int randomIndex = random.nextInt(lineCount);
                // If random index is 0, replace stored line with current line
                if (randomIndex == 0) {
                    currentLine = line;
                }
            }
            return currentLine;  // Null if file is empty
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
