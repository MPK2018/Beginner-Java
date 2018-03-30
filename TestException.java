
/*TestException.java
 * 
 * @author Mugdha Phadke
 * Midterm
 * Uses Calulator.java and TestException.java
 *
 *This File Is 
*/
import java.util.InputMismatchException;
import java.util.Scanner;

//Exception handling examples.  

//Ultimeate: Write your own exception class
class MyOutOfRangeException extends Exception {
	MyOutOfRangeException(String message) {
		super(message);
	}
}

public class TestException {

	public static void getTwoFloats(int choice, Scanner sc, float[] myInputs) {
		do { // Loop until we have correct input
			System.out.print("Enter two floats separated by a space: ");
			try {
				myInputs[0] = sc.nextFloat();
				// waits for user input
				myInputs[1] = sc.nextFloat();
				// waits for user input
				// if you are here, the floats were good, you
				// are done, break out from loop

				if (choice == 4 && myInputs[1] == 0) {
					sc.nextLine();
					System.out.println("\nError! Cannot divide by zero. Try again.");
					continue;
				}
				break;
			} catch (final InputMismatchException e) {
				System.out.println("You have entered invalid floats. Try again.");
				sc.nextLine();
				// discard non-float input
				continue;
				// keep looping until you found right one
			}
		} while (true);

	}

	public static double getADouble(Scanner readInput) {
		double inputDouble = 0.0;
		boolean isValid = false;
		while (isValid == false) {
			System.out.print("Please enter a double: ");
			if (readInput.hasNextDouble()) {
				inputDouble = readInput.nextDouble();
				isValid = true;
			} else {
				System.out.println("Error! Invalid double. Try again.");
			}
			readInput.nextLine(); // discard any other data
		}
		return inputDouble;
	}

	public static int getAnInteger(Scanner readInput) {
		int inputInt;
		do { // Loop until we have correct input
			System.out.print("Enter an integer: ");
			try {
				inputInt = readInput.nextInt(); // waits for user input
				break; // if you are here, all is good
			} catch (final InputMismatchException e) {
				System.out.println("Wrong input. Try again.");
				readInput.nextLine(); // discard non-int input
				continue; // keep looping until you found right onee
			}
		} while (true);
		return inputInt;
	}

	// Version 4 static method properly written with exception
	public static int getInputWithinRange(Scanner in)
			throws NullPointerException, InputMismatchException, NumberFormatException, MyOutOfRangeException {
		int userChoice = 0;
		if (in == null) { // first check if the scanner reference is a null
			throw new NullPointerException("Null Scanner");
		}
		try { // now we are ready to parse integer
			System.out.print("Please enter a value between 1 to 5: ");
			userChoice = Integer.parseInt(in.next());
		} catch (InputMismatchException e) {
			in.nextLine();
			throw e; // pass the buck
		} catch (NumberFormatException e) {
			in.nextLine();
			throw e; // pass the buck
		}
		// we come here only when a number is successfully read
		if (userChoice < 1 || userChoice > 5) {
			in.nextLine();
			throw new MyOutOfRangeException("Only choice of 1 to 5 is allowed \n");
		}
		// if we are here that means all is good
		return userChoice;
	}

}
