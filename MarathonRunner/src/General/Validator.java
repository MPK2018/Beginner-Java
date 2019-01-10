/*
 * @Project Marathonrace.java
 * @filename Validator.java 
 * @Package General
 * @author {Mugdha Phadke}
 * 
 */

package General;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * This class handles all the input requests for different types on inputs
 */

	public class Validator {
	/*
	 * Get a line
	 * 
	 * @param Scanner and Prompt to display
	 */
	public static String getLine(Scanner sc, String prompt) {
		System.out.print(prompt);
		String s = sc.nextLine(); // read the whole line
		return s;
	}

	/*
	 * Get a a String 
	 * 
	 * @param Scanner and Prompt to display
	 */
	public static String getString(Scanner sc, String prompt) {
		System.out.print(prompt);
		String s = sc.nextLine(); // read the first string on the line
		// sc.nextLine(); // discard the rest of the line
		return s;
	}

	/*
	 * Get an Integer
	 * 
	 * @param Scanner and Integer
	 */
	public static int getInt(Scanner sc, String prompt) {
		boolean isValid = false;
		int i = 0;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return i;
	}

	/*
	 * Get an Integer
	 * 
	 * @param Scanner and Integer
	 */
	public static int getInt(Scanner sc, String prompt, int min, int max) {
		int i = 0;
		boolean isValid = false;
		while (isValid == false) {
			i = getInt(sc, prompt);
			if (i <= min)
				System.out.println("Error! Number must be greater than " + min);
			else if (i >= max)
				System.out.println("Error! Number must be less than " + max);
			else
				isValid = true;
		}
		return i;
	}

	/*
	 * Get a Double
	 * @param Scanner and Double
	 */
	public static double getDouble(Scanner sc, String prompt) {
		boolean isValid = false;
		double d = 0;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextDouble()) {
				d = sc.nextDouble();
				isValid = true;
			} else {
				System.out.println("Error! Invalid decimal value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return d;
	}

	/*
	 * Get a Double
	 * @param Scanner and Double
	 */
	public static double getDouble(Scanner sc, String prompt, double min, double max) {
		double d = 0;
		boolean isValid = false;
		while (isValid == false) {
			d = getDouble(sc, prompt);
			if (d <= min)
				System.out.println("Error! Number must be greater than " + min);
			else if (d >= max)
				System.out.println("Error! Number must be less than " + max);
			else
				isValid = true;
		}
		return d;
	}

	/*
	 * Get an integer within the 1 and the MAXOPTION in Marathon race
	 * @param Scanner and Integer
	 */
	public static int getInputWithinRange(Scanner input, int maxoption) throws NullPointerException,
			InputMismatchException, NumberFormatException, MyOutOfRangeException, IOException {
		int userChoice = 0;
		if (input == null) { // first check if the scanner reference is a null
			throw new NullPointerException("Null Scanner");
		}
		try { // now we are ready to parse integer
			System.out.print("Please enter a value between 1 to " + maxoption + ": ");
			userChoice = Integer.parseInt(input.next());
		} catch (InputMismatchException e) {
			// in.read();
			throw e; // pass the buck
		} catch (NumberFormatException e) {
			// in.readLine();
			throw e; // pass the buck
		}
		// we come here only when a number is successfully read
		if (userChoice < 1 || userChoice > MarathonRace.MAXOPTION) {
			// in.readLine();
			String errorMesg = String.format("Only choice of 1 to %d5 is allowed \n", MarathonRace.MAXOPTION);
			throw new MyOutOfRangeException(errorMesg);
		}
		// if we are here that means all is good
		return userChoice;
	}

	/*
	 * Get a required String which is not a space or null 
	 * 
	 * @param Scanner and Prompt to display
	 */
	public static String getRequiredString(Scanner input, String prompt) {

		String inputString = "";
		boolean isValid = false;
		while (isValid == false) {

			System.out.print(prompt);
			if (input.hasNext()) {
				inputString = input.next();
			}

			if (!inputString.isEmpty()) {
				isValid = true;
				break;
			} else {
				System.out.println("Please enter valid String");
			}
		}
		// input.nextLine();
		return inputString;
	}

}
