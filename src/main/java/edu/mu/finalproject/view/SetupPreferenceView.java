package edu.mu.finalproject.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.util.GetIntegerInput;

public class SetupPreferenceView {
	/** 
	 * Displays @introStr with a * divider line below it
	 * @param introStr
	 */
	public void displayIntro(String introStr) {
		System.out.println();
		System.out.println(introStr);
		System.out.println("*******************************\n");
	}
	
	/**
	 * Displays @param question with a - divider line below it
	 * @param question
	 */
	public void displayQuestion(String question) {
		System.out.println();
		System.out.println(question);
		System.out.println("-------------------------------");
	}
	
	/**
	 * Prompts user if they'll like to setup their own preference or take a quiz & let quiz scores setup preference
	 */
	public void displaySetupIntro() {
		displayIntro("Let's setup your listening preference...");
		displayQuestion("Determine how you'll setup your listening preference:");
		System.out.println("0 - Take a quiz to determine my preference");
		System.out.println("1 - Choose my own preference");
	}
	
	/**
	 * Prompts for user's answer from displaySetupIntro(). Prompts until their answer is 0 or 1
	 * @return
	 */
	public int getSetupAnswer() {
		System.out.print("\nMUST enter a either 0 or 1. ");
		int answer = GetIntegerInput.getInputInteger(new Scanner(System.in));
		while(answer != 1 && answer != 0) {
			System.out.println("Answer must be 0 or 1!!!\n");
			answer = getSetupAnswer();
		}
		return answer;
	}
	
	/**
	 * Displays the introduction to the quiz option of setting up preferences
	 */
	public void displayQuizIntro() {
		displayIntro("Here's a multiple choice quiz to help determine your preference as a listener!");
	}
	
	/**
	 * Displays each choice in @choices on seperate lines with numerical labels (1-based indexing)
	 * @param choices
	 */
	public void displayChoices(ArrayList<String> choices) {
		int choiceNum = 1;
		for (String choice : choices) {
			System.out.println(choiceNum + " - " + choice);
			++choiceNum;
		}
	}
	
	/** 
	 * Prompts user to choose from 1~ @param maxChoices. Prompts until user's answer is in range 1~ @param maxChoices
	 * @param maxChoices
	 * @return
	 */
	public int getInputAnswer(int maxChoices) {
		System.out.print("\nMUST enter an answer number between (1~" + maxChoices + "). ");
		int answer = GetIntegerInput.getInputInteger(new Scanner(System.in));
		while(answer < 1 || answer > 6) {
			System.out.println("Answer with a number between 1~" + maxChoices + "!!!\n");
			answer = getInputAnswer(maxChoices);
		}
		return answer;
	}
	
	/**
	 * Displays error message if error occurs in quiz setup 
	 */
	public void displayQuizError() {
		System.out.println("\nSorry, there's an error in displaying the quiz right now.");
	}
	
	/**
	 * Displays preference with * divider line below it
	 * @param preference
	 */
	public void displayPreference(String preference) {
		displayIntro("\nYour preference is...");
		System.out.println("You're a(n) " + preference + " lover!");
		displayIntro("");
	}
}
 