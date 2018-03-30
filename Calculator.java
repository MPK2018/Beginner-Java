
/*
 * Calculator.java
 * 
 * @author Mugdha Phadke
 * @Midterm
 * Please compile together Calulator.java and TestException.java
 *
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class Calculator {
	// Defining the array to hold the description of choice for messaging purposes
	static String[][] operationString = { { "Addition of ", "and" }, { "Subtracting", "from" },
			{ "Multiplication of ", "and" }, { "Dividing", "by" }, { "Exit the Calculator", " " } };
	static String[] operationTag = { "Addition", "Subtraction", "Multiplication", "Division", "Exit" };

	// Constructor
	Calculator() {
	}
	// Added the function instead of carrying out the operations to practice the
	// class functionality
	// this will be easy in case I want to expand the functionality which needs
	// series of operations.

	// Addition Function
	public static float Add(float[] myInputs) {
		return (myInputs[0] + myInputs[1]);
	}

	// Subtraction Function
	public static float Subtract(float[] myInputs) {
		return (myInputs[0] - myInputs[1]);
	}

	// Multiplication Function
	public static float Product(float[] myInputs) {
		return (myInputs[0] * myInputs[1]);
	}

	// Division Function
	public static float Division(float[] myInputs) {
		return (myInputs[0] / myInputs[1]);
	}

	/*
	 * This function accepts the inputs and performs the operation
	 * 
	 * @param operation choice, scanner
	 * 
	 * @returns the float result
	 * 
	 */
	public static void CalculateResult(int choice, Scanner sc) throws MyOutOfRangeException {
		float[] myInputs = new float[2]; // Array of floats to hold the operands
		float result = 0;

		do {
			try {
				TestException.getTwoFloats(choice, sc, myInputs); // I have kept all the test exception functions in
																	// different class
				break;
			}
			// order of catch is important
			catch (NullPointerException e) {
				System.out.print("Message: " + e.getMessage());
			} catch (Exception e) {
				System.out.print("Catch all Exception ");
			}

		} while (true);

		switch (choice) {

		case 1:
			result = (Add(myInputs)); // calling the addition function I can do result = myInpus[o] + myInputs[1]
			break;
		case 2:
			result = (Subtract(myInputs)); // calling the subtraction function I can do result = myInpus[o] -
											// myInputs[1]
			break;
		case 3:
			result = (Product(myInputs)); // calling the product function I can do result = myInpus[o] * myInputs[1]
			break;
		case 4:
			result = (Division(myInputs)); // calling the division function I can do result = myInpus[o] / myInputs[1]
			break;
		case 5: // returning 0 in case of exit but the control will not come here
		default:
			result = (float) 0.0;
		}
		System.out.printf(" The result of %s %5.2f %s %5.2f is: %5.2f \n", operationString[choice - 1][0], myInputs[0],
				operationString[choice - 1][1], myInputs[1], result);

	}

	/*
	 * FUnction displays the menu and accepts the choice between 1 and 4
	 * 
	 * @ @param Input Scanner
	 * 
	 * @return Integer Choice
	 */
	public static int DisplayMainMenu(Scanner sc)
			throws NullPointerException, InputMismatchException, NumberFormatException, MyOutOfRangeException {

		int choice = 0;
		System.out.println("Wlecome to Mugdha Phadke's Calculator \n");
		System.out.println(" Please Enter 1 for Addition");
		System.out.println("        Enter 2 for Subtraction");
		System.out.println("        Enter 3 for Multiplication");
		System.out.println("        Enter 4 for Division");
		System.out.println("        Enter 5 to Exit the Calculator");

		// Calling the function to accept the choice within range from TestException
		// class
		do {
			try {

				choice = TestException.getInputWithinRange(sc);
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
					System.out.println("Your choice is: " + operationTag[choice - 1]);
			}
		} while (choice == 0);

		return choice;
	}

	/**
	 * @param args
	 * @throws MyOutOfRangeException
	 */
	public static void main(String[] args) throws MyOutOfRangeException {
		// TODO Auto-generated method stub

		int choice = 0;

		Scanner sc = new Scanner(System.in); // Opening the scanner

		// call display main menu until user enters option 5 to exit
		while (true) {
			choice = Calculator.DisplayMainMenu(sc);

			if (choice == 5) // User enters the choice to exit
			{
				System.out.println("Thank you for using my Calculaor! Goodbye");
				sc.close();
				return;
			}

			Calculator.CalculateResult(choice, sc); // Function accepts the operands and Calculates the results

			System.out.print("\nPlease enter to Continue ....");
			sc.nextLine(); // will discard anything that is enter other than choice
			sc.nextLine(); // will wait for user to press enter before proceeding
		}
	}

}