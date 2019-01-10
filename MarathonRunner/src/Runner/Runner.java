package Runner;

/**
 * Runners.java MarathonRunner
 * 
 * @author Mugdha Phadke
 */

public class Runner {

	private String name; // name of the runner
	private int speed; // speed of the runner
	private int restPercentage; // rest percentage of the runner

	Runner() {
		this("", 0, 0); // default construcot
	}

	public Runner(String name, int speed, int restPercentage) { // Constructor
		this.name = name;
		this.speed = speed;
		this.restPercentage = restPercentage;
	}

	// Setter Functions
	void SetName(String name) {
		this.name = name;
	}

	void SetSpeed(int speed) {
		this.speed = speed;
	}

	void SetRestPercentage(int restPercentage) {
		this.restPercentage = restPercentage;
	}

	// Getter Functions
	public String GetName() {
		return this.name;
	}

	public int GetSpeed() {
		return this.speed;
	}

	public int GetRestPercentage() {
		return this.restPercentage;
	}

	//To String to print the Runner Details
	public String toString() {
		String runner = String.format("Name : %-15s", name);
		runner += String.format("Speed : %3d  ", speed);
		runner += String.format("Rest Percentage : %3d", restPercentage);
		return runner;
	}
}
