package edu.mu.finalproject.view;

import java.util.Scanner;

public class SetupPreferenceView {
	public void displayQuestion(String question) {
		System.out.println(question);
	}
	public void displayChoices(String[] choices) {
		int choiceNum = 1;
		for (String choice : choices) {
			System.out.println(choiceNum + " - " + choice);
			++choiceNum;
		}
	}
	public int getInputAnswer() {
		System.out.print("Enter choice's number: ");
		Scanner scanner = new Scanner(System.in);
		int answer = scanner.nextInt();
		return answer;
	}
}
