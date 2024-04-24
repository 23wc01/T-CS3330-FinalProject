package edu.mu.finalproject.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.mu.finalproject.util.GetIntegerInput;

public class SetupPreferenceView {
	
	public void displaySetupIntro() {
		System.out.println();
		System.out.println("Here's a multiple choice quiz to help determine your preference as a listener!");
		System.out.println("*******************************");
	}
	
	public void displayQuestion(String question) {
		System.out.println();
		System.out.println(question);
		System.out.println("-------------------------------");
	}
	
	public void displayChoices(ArrayList<String> choices) {
		int choiceNum = 1;
		for (String choice : choices) {
			System.out.println(choiceNum + " - " + choice);
			++choiceNum;
		}
	}
	
	public int getInputAnswer() {
		System.out.print("\nInput MUST be between (1~6). ");
		int answer = GetIntegerInput.getInputInteger(new Scanner(System.in));
		while(answer < 1 || answer > 6) {
			System.out.println("Answer with a number between 1~6!!!\n");
			answer = getInputAnswer();
		}
		return answer;
	}
	
	public void displayPreference(String preference) {
		System.out.println("\nResults:");
		System.out.println("You're a(n) " + preference + " lover!");
		System.out.println("*******************************");
		System.out.println();
	}
}
 