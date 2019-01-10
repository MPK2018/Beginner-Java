/**
 * RunnersApp.java
 * MarathonRunner
 * General
 *@Author Mugdha Phadke
 */
package Racing;

import java.util.ArrayList;

import General.GeneralPurposeFunctions;
import Runner.Runner;
import Runner.RunnersDAO;

public class RunnersApp {
	private RunnersDAO runnersDAO; // runnersDAO
	private ArrayList<Runner> runners = null; // ArrayList of Runners
	private ArrayList<Thread> threads = null; // Array to hold threads
	private Winner winner;
	volatile static boolean winnerFlag;
	
	public RunnersApp(RunnersDAO runnersDAO) {
		this.runnersDAO = runnersDAO;
		winner = new Winner();
	}

	// this method initializes the winner flag and name before running the race
	void init() {
		winnerFlag = false; // Reset the boolean variable (executed) before simulating new race.
		winner.resetWinner();
	}
	
	synchronized Winner GetWinner() {
		return winner;
	}
	
	boolean GetWinnerFlag() {
		return winnerFlag;
	}

	/**
	 * This method accesses the runners in the file and spawns separate thread for
	 * each runner.
	 * This class holds the winner which is common for all the threads
	 */
	 public void SetRunnerThreads() {
		 
		// Get all the runners from the data source
	
     	boolean ans = true;    // ans is used to control the while loop
		while (ans) {
			threads = new ArrayList<>(); // allocate the memory for threads array
			ThreadRunner thread = null; // reference to hold the thread

			init(); // initialize the flags
			System.out.println("Get ...Sets Go....!");

			// start thread for each runner
			runners = this.runnersDAO.GetRunners(); // get the runners fron runnersDAO

			// Create thread for each runner
			for (Runner element : runners) {
				// get the details of each runner
				String name = element.GetName();
				int speed = element.GetSpeed();
				int restPercentage = element.GetRestPercentage();

				// Create thread for current Runner
				thread = new ThreadRunner(name, speed, restPercentage, this);
				thread.setName(name);  //set the name for the thread
				thread.start();       //start the thread
				threads.add(thread); // add thread in threads array
			}
           
			WaitForThreadsToJoin(); // main thread waits until other threads complete

			System.out.println();

		 	// ask user if he wants to run another race or go back
			ans = GeneralPurposeFunctions.WantToContinue("Want to try again?(y/n)");
		}
	}

	
	/**
	 * This method interrupts the other threads once the winner thread is found
	 * 
	 * @param threadName
	 *            winner thread name
	 */
	 void interruptThreads(String threadName) {
		 
       	for (Thread element : threads) {
       		
			if (!threadName.equals(element.getName())) // send interrupt to all the remaining threads
				element.interrupt();
		}
	}

	/**
	 * Main thread waits until all threads finish execution
	 */
	void WaitForThreadsToJoin() {
		for (int i = 0; i < threads.size(); i++) {
			try {
				threads.get(i).join();

			} catch (InterruptedException e) {
				System.out.println("hey somethign went wrong");
				break;
			}
		}
	}

}
