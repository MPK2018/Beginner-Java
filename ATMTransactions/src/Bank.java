import java.util.ArrayList;

/**
 * Bank.java
 * @author {Mugdha Phadke}
 *This is the bank class whch holds the list of users and their accounts
 */
public class Bank {

	private String bankName = "Java Comp"; // Bank Name hard coded for now
	private ArrayList<UserAccount> userList; // List to hold the user accounts

	// constructor creates the user list later I can dynamically set bank name
	Bank() {
		userList = new ArrayList<>();
	}

	/* Add UserAccount to the user list with UserId and the Username
	 * it creates the UserAccount for the user and checking adn savings account 
	 * Add the user to the user list and returns the newly created user 
	 * @paraam user id and user name
	 */

	UserAccount AddUser(int userId, String userName) {
		UserAccount userAccount = new UserAccount(userId, userName);

		userList.add(userAccount);
		return userList.get(userList.size() - 1); // return newly added user for further processing
	}

	// getter and setter methods

	String getName() {
		return this.bankName;
	}

	// search and retrieve the userAccount from the user list for a userId
	// if user Account does not exists return null
	//@param user id
	UserAccount getUserRecord(int userId) {

		for (UserAccount element : this.userList) {
			if (element.getUserId() == userId)
				return element;
		}
		return null; //no user found
	}

}

