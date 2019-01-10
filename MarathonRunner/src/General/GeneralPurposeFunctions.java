package General;

/** 
 * @Project Marathonrace.java
 * @filename GeneralPurposeFunctions.java
 * @Package General
 * @author {Mugdha Phadke}
 * This class holds all general Purpose functions
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class MyOutOfRangeException extends Exception {
	MyOutOfRangeException(String message) {
		super(message);
	}
}


 public class GeneralPurposeFunctions {

	
	 /*
	  * This function gives user the choice to select the database until user selects exit.
	  * @param System input (keyboard)
	  */
	private static Scanner input = new Scanner(System.in);

	/*
	 * This function displays the menu until user enters option to quit
	 */
	 static int DisplayMainMenu() {

		int choice = 0;
		System.out.println("Wlecome to Mugdha's Marathon Race  \n");
		System.out.println("Enter 1  Derby database");
		System.out.println("      2. XML file");
		System.out.println("      3. Text file");
		System.out.println("      4. Default two runners");
		System.out.println("      5  for Exit");
	
		do {
			try {
				// method get the valid integer between 1 and maxOption from ValidateIO class
				choice = Validator.getInputWithinRange(input, MarathonRace.MAXOPTION);
			}

			catch (NullPointerException e) {
				System.out.print("Message: " + e.getMessage());
			} catch (MyOutOfRangeException e) {
				System.out.print("Message: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("You have entered an invalid input. Try again.");
			}

			finally {

				if (choice != 0)
					System.out.println("Your choice is: " + choice);
				
			}
		} while (choice == 0); // continues until user enters valid option

		return choice;
	}

	
	
	/*
	 * returns the user choice to continue or exit
	 * @param String message to diaplay
	 * @string mesg to display the message
	 */
	 public static Boolean WantToContinue(String mesg) {
		String ans = null;
		while (true) {
			System.out.print(mesg);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				ans = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			if (ans.equalsIgnoreCase("y"))
				return true;
			else if (ans.equalsIgnoreCase("n"))
				return false;
			else {
				System.out.println("Please enter y or n :");
				continue;
			}
		}

	}

	 /*
	  * Closes the input and other applicable resources
	  * 
	  */
	static void cleanUp() {
		input.close();
	}

}
