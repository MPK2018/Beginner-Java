/**
 * defaultRunner.java
 * 
 *@Author Mugdha Phadke
 *This class reads the default runners list 
 */
package Runner;

import java.util.ArrayList;



class defaultRunner implements RunnersDAO {
	ArrayList<Runner> runners = new ArrayList<>();
	
	//Constructor
	defaultRunner(){
		runners.add(new Runner("Hair", 100, 90)); //create and add runner to an array
		runners.add(new Runner("Tortoise", 10, 0));//create and add runner to an array
	}
	public ArrayList<Runner> GetRunners() {
		// TODO Auto-generated method stub
		return runners;
	}
	
	
}
