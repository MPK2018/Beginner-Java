/**
 * ValidateIo.java
 */

/**
 * @author {Mugdha Phadke}
 *
 */

import java.util.Scanner;

class ValidateIO {

	// Get a line
	static String getLine(Scanner sc, String prompt) {
		System.out.print(prompt);
		String s = sc.nextLine(); // read the whole line
		return s;
	}

	// Get a string with choice
	static String getString(Scanner sc, String prompt, int choice) {
		String ans = null;
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			ans = sc.next(); // read the first string on the line
			sc.nextLine(); // discard the rest of the lined
			if (choice == 1)
				if ((!ans.equalsIgnoreCase("w")) && (!ans.equalsIgnoreCase("d"))) {
					isValid = false;
					continue;
				} else
					isValid = true;

			if (choice == 2)
				if ((!ans.equalsIgnoreCase("c")) && (!ans.equalsIgnoreCase("s"))) {
					isValid = false;
					continue;
				} else
					isValid = true;
		}
		return ans.toUpperCase();
	}

	// get a string with only two valid options
	static String getString(Scanner sc, String prompt, String s1, String s2) {
		String ans = null;
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			ans = sc.next(); // read the first string on the line
			sc.nextLine(); // discard the rest of the lined
			if ((!ans.equalsIgnoreCase(s1)) && (!ans.equalsIgnoreCase(s2))) {
				isValid = false;
				continue;
			} else
				isValid = true;
		}
		return ans.toUpperCase();
	}

	// get an integer
	static int getInt(Scanner sc, String prompt) {
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

	// Get an Integer within range
	static int getInt(Scanner sc, String prompt, int min, int max) {
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

	// Get a double
	static double getDouble(Scanner sc, String prompt) {
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
			if (d < 0) {
				System.out.println("Please enter amount > 0\n");
				isValid = false;
			}
		}
		return d;
	}

	// get a double within range
	static double getDouble(Scanner sc, String prompt, double min, double max) {
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

}