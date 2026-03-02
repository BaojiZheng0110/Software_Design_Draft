// GuessResult.java
// Programming Assignment 2
// Software Design
// Author: Alex Warren
// Date Last Modified: 19 February 2026

import java.util.ArrayList;

/*
This is a data transfer object.
Its only job is to carry data from WordGuesserLogic.java to WordGuesserGUI.java
 */
public class GuessResult {
    private final ArrayList<String> feedback;
    private final boolean isWin;
    private final String answer;
    private final String guess;

    // Constructor
    public GuessResult(ArrayList<String> feedback, boolean isWin, String answer, String guess) {
        this.feedback = feedback;
        this.isWin = isWin;
        this.answer = answer;
        this.guess = guess;
    }

    // Getter methods
    public ArrayList<String> getFeedback() {
        return feedback;
    }
    public boolean getIsWin() {
        return isWin;
    }
    public String getAnswer() {
        return answer;
    }
    public String getGuess() {
        return guess;
    }
}
