import java.util.ArrayList;

/**
 * UserAccount.java
 * @author {Mugdha Phadke}
 * This class holds the user details and the accounts for the user
 */
public class UserAccount {

	private int userId; // created at the time of creation by incrementing accountCount
	private String userName;
	private ArrayList<Account> accountsList; // list to holds all account for this user
	private static int accountCount = 1; // static count to assign userid

	/*Constructor 
	 * @param user id and user name
	 * creats the user account and savngs and checking account for the user 
	 * adds the accounts to the account list
	 */
	UserAccount(int userId, String userName) {
		this.userId = userId; // UserID
		this.userName = userName; // User Name
		accountsList = new ArrayList<>(); // List to hold accounts for this user

		// adding the checking and savings account to the list of Accounts
		accountsList.add(new CheckingAccount(accountCount++, 1.00)); // hard coding transaction fee for checking account
		accountsList.add(new SavingsAccount(accountCount++, 1)); // hard coding interest savings account

	}

	// setter and getter functions
	String getName() {
		return this.userName;
	}

	int getUserId() {
		return this.userId;
	}

	// returns the list of accounts for the user
	ArrayList<Account> getAccounts() {
		return this.accountsList;
	}

	/*
	 * processes the transaction
	 * 
	 * @param account type(c/s) transaction type(w/d) and the amount
	 * for withdrawals check if there are enough funds 
	 * for checking account it checks it has enough funds for withdrawal and transaction fee
	 */
	void ProcessTransaction(String accountType, String transactionType, double amount) {
		Account account;

		if (accountType.equalsIgnoreCase("C")) // gets the correct account depending of transaction
			account = this.accountsList.get(0);
		else
			account = this.accountsList.get(1);

		if (transactionType.equalsIgnoreCase("W")) { // checks for available funds and displays of ther are not enough
														// funds
			if (!account.Withdrawal(amount))
				account.PrintBalance("You have insufficient funds. Your available funds are ");

		} else if (transactionType.equalsIgnoreCase("D")) // perfrorms deposit transactions
			account.Deposits(amount);

		// this is implemented but beyond the scope of project
		// this.trans.add (new Transactions(account.getAccountNO(), transactionType,
		// amount));

	}

	// Prints the balances for all accounts
	void PrintBalances(String prompt) {
		System.out.println(prompt);
		for (Account element : this.accountsList) {
			if (element.getType() == "C")
				prompt = ("Checking :");
			else
				prompt = ("Savings :");

			element.PrintBalance(prompt);
		}

	}

	// this function calculates the fees and interest and prints the closing balances 
	void EndOfSession() {
		/*
		 * this part is implemented but out of the scope of this project so commented
		 * for(Transactions element: trans) { element.printTransaction(); }
		 */

		System.out.println("\nMonthly Payments and Fees\n");

		// Print fees for each account for this user
		for (Account element : this.accountsList)
			element.PrintFormattedFees();

		this.PrintBalances("\n FinalBalances\n"); // print closing balances

	}

}
