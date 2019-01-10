/**
 * RunnerDB.java
 *@ project MarathonRunner
 *@Author Mugdha Phadke
 *read the data from the database and return to the runnerDAO
 */
package Runner;

import java.util.*;
import java.sql.*;

class RunnerDB implements RunnersDAO {
	private static Connection connection = null;
	private ArrayList<Runner> runners = null;
	private String fileName;  // name of the database with path

	RunnerDB(String fileName) {
		this.fileName = fileName;
		runners = GetRunners();  //Array to hold Runners
	}

	//gets the runners from the database
	public ArrayList<Runner> GetRunners() {

		//if runners are already read don't read it again
		if (runners != null)
			return runners;
		runners = new ArrayList<>();  //assign the memory to an array
		// set the connection
		Statement statement;    
		connection = connect(fileName);
		
		if (connection == null) { //connection failed
			return null;
		} else
			try {
				statement = connection.createStatement();

				ResultSet rs = statement.executeQuery("SELECT * FROM RunnersStats"); //execute the query
				
				while (rs.next()) {
					String name = rs.getString("Name");     //store name
					int RunnersSpeed = rs.getInt("RunnersSpeed");  // store RunnersSpeed

					int RestPercentage = rs.getInt("RestPercentage");  //store RestOercentage

					runners.add(new Runner(name, RunnersSpeed, RestPercentage)); //create Runner and add to an array

				}
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		try {
			connection.close();
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		return runners;  //all went well return an array
	}

	
	//Connect to the database
	private static Connection connect(String fileName) {
		try {
			// keep relative path to this project
			String dbDirectory = "Resources";
			System.setProperty("derby.system.home", dbDirectory);

			// use the DriverManager to create a Connection object
			String dbUrl = "jdbc:derby:";
			dbUrl += fileName;  //actual database name entered by user or default name

			
			String username = "";
			String password = "";
			Connection connection = DriverManager.getConnection(dbUrl, username, password);

			return connection;

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("invalid Database");
			return null;
		}
	}

}