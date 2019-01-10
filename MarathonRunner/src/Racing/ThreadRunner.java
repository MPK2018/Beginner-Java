/**
 * ThreadRunner.java
 * MarathonRunner
 * General
 *@Author Mugdha Phadke
 */
package Racing;

/**
 * @author mugdha Phadke
 *
 */

public class ThreadRunner extends Thread {
	private String runnerName;   // Runners name
	private double speed;  //Runners speed
	private int restPercentage; //Runners restPercentage
	private RunnersApp runnersApp; // reference to the runners app 
	boolean firstToFinish;  //The first thread to finish the race
	Object obj = new Object();  

	private static final int RESTTIME = 200;
	private static final int YIELDTIME = 100;
	private static final int TOTALRACEDISTANCE = 1000;

	ThreadRunner(String name, double speed, int restPercentage, RunnersApp app) {
		this.runnerName = name;
		this.speed = speed;
		this.restPercentage = restPercentage;
		this.runnersApp = app;
	}
	

	@Override
	public void run() {
		int distanceCovered = 0;

		try {
			// loops until the one of the runner reaches the  total race distance
			 
			while (distanceCovered < TOTALRACEDISTANCE || !runnersApp.GetWinnerFlag()) 
			{
				//find the random number between 1 and 100
				int randomNumber = (int) (Math.random() * 100 + 1);
				 
                //runner sleeps if the random number is less that or equal to restPercentage 
				if (randomNumber <= restPercentage) {
					Thread.sleep(RESTTIME);
					// System.out.println(name + " is sleeping : " + REST_TIME);
				} 
				else //Runners runs for the distance of speed
				{
					synchronized (obj ){
					distanceCovered += speed; // increment the distance by speed value
					//synchronize the following block for uninterrupted execution
					
					if (distanceCovered >= TOTALRACEDISTANCE) { //this runner reach final destination

						//set winner flag true and  winner's name and winners distance covered
						runnersApp.GetWinner().SetWinner(runnerName, distanceCovered);
						runnersApp.interruptThreads(runnerName);
						 
					} else  // Display the distance traveled if there is no winner 
					  if(!runnersApp.GetWinnerFlag()) 
						System.out.println(runnerName + " : " + distanceCovered);
					Thread.sleep(YIELDTIME);
					}
					
				}
				
			}
	
		} catch (InterruptedException e) {
			String concedeMesg = runnerName + ": ";
			concedeMesg +=  runnersApp.GetWinner().GetWinnerName()+ " you beat me fair and square ";
			concedeMesg += "by "+ (runnersApp.GetWinner().GetDistanceCovered()-distanceCovered);
			System.out.println(concedeMesg);
			
		}

	}
}
