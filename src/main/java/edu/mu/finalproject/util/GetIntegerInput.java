package edu.mu.finalproject.util;

import java.util.Scanner;

/**
 * This class provides a method for getting an integer input from the console
 * 
 * Used as a helper method for whenever an integer needs to be inputed
 * 
 * Used in class EventView
 * @author etwil
 */
public class GetIntegerInput {
	
	public static int getInputInteger(Scanner scanner) {
        // Prompt the user for input until an integer is entered
        while (true) {
            System.out.print("Enter an integer: ");

            // Check if the next input is an integer
            if (scanner.hasNextInt()) {
                int inputNumber = scanner.nextInt();
                return inputNumber; // Return the integer if it's valid
            } 
            else {
                // If the input is not an integer, prompt the user again
                String input = scanner.next();
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

}
