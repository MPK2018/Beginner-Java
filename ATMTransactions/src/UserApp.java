/**
 * UserApp.java
 * @author {Mugdha Phadke}
 *
 *This class is the driver class 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		String transactionType, accountType, prompt = null, message = "Do you want to continue(y/n)? : ";
		double amount = 0.0;

		Bank bank = new Bank(); // create bank

		/* ideally you will ask for the userId before going ahead but here we only have
		  one user. Instead I am initializing user id to 1 and user name to "Bineet Sharma"
		  userId= ValidateIO.getInt(input, "Enter Your User ID");
		*/
		
		int userID = 1;
		String userName = "Bineet Sharma";
		UserAccount userAccount = bank.getUserRecord(userID); // get the user record for existing user
		if (userAccount == null) { // if the user does not exist add the user
			userAccount = bank.AddUser(userID, userName); // Add the user to the bank
		}

		prompt = String.format("Welcome %s to the %s Account application \n", userAccount.getName(), bank.getName());
		prompt += "Starting Balances \n";
		
		userAccount.PrintBalances(prompt);

		do {
			System.out.println("Enter the transactions for the month");
			prompt = "Withdrawal or deposit? (w/d): ";
			transactionType = ValidateIO.getString(input, prompt, "W", "D");

			prompt = "Checking or savings? (c/s): ";
			accountType = ValidateIO.getString(input, prompt, "C", "S");
			prompt = "Please Enter the Amount?: ";
			amount = ValidateIO.getDouble(input, prompt);

			// this performs the transaction processing
			userAccount.ProcessTransaction(accountType, transactionType, amount);

		} while (WantToContinue(message) == true);

		userAccount.EndOfSession(); // process the end of the transaction process and print final balances
		input.close();
	}

	/*
	 * returns the user choice to continue or exit
	 * 
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
}
