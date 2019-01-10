package General;

import java.sql.SQLException;
import java.util.Scanner;

import Racing.RunnersApp;
import Runner.DAOFactory;
import Runner.Runner;
import Runner.RunnersDAO;

/**
 * Marathonrace.java
 * @author {Mugdha Phadke}
 *
 */

//This is the main class which starts the Race
public class MarathonRace {
	public static Scanner input = new Scanner(System.in);
	public final static int MAXOPTION = 5; // No Of Options available for user
	public static String fileName;     //The database source name

	public static int choice = 0; // choice to run the user selected exercise

	// private static ArrayList<Runner> runners = null;
	private static RunnersDAO RunnersDAO = null; //Holds the source database

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		while (true) {
			choice = GeneralPurposeFunctions.DisplayMainMenu(); // function to call Display Menu
			if (choice == MAXOPTION) {
				System.out.println("Thank you for running My Marathon " + choice);
				System.out.println("Good Buy!!! " + choice);
				break;
			}

			RunnersDAO = DAOFactory.getRunnerDAO(choice, input); //Gets the Database Source
			System.out.println("Contestants Stat :");    //Prints the contestant statistics
			for (Runner element : RunnersDAO.GetRunners())
				System.out.println(element.toString());

			RunnersApp runnersApp = new RunnersApp(RunnersDAO);  //Sets the app to run the race

			runnersApp.SetRunnerThreads();   //sets the threads for each contestant

		}

		input.close(); //clean up

	}

}
