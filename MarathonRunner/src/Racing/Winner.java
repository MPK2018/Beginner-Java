package Racing;

public class Winner {
	
	//volatile static Boolean winnerFlag;
	volatile static String winnerName;
	volatile static int distanceCovered;
	
	Winner()
	{
		//winnerFlag = false;
		winnerName = "";
		distanceCovered = 0;
	}
	
	void resetWinner() {
		winnerName = "";
		distanceCovered = 0;
	}
	synchronized boolean SetWinner(String name, int distance) {
		
		if(RunnersApp.winnerFlag == true)
			return false;
		else {
			RunnersApp.winnerFlag = true;
			winnerName = name;
			distanceCovered = distance;
			System.out.println(name + " : " + distanceCovered);
			System.out.println("The race is over! The " + name + " is the winner. \n");
		}
		return true;
	}
	
	synchronized String GetWinnerName() {
		return winnerName;
	}
	synchronized int GetDistanceCovered() {
		return distanceCovered;
	}

}
