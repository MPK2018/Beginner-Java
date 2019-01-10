/*
 * @Project MarathonRunner
 * DAOFactory.java
 * @author Mugdha Phadke
 * @Package Runner
 * This class maps the RunnersDAO interface to facilitate the user to use 4 different 
 * types of inputs and provides the option to add many more types on inputs
 * Currently implemented are database, xml file, text file and default runner 
 */
package Runner;

import java.sql.SQLException;
import java.util.Scanner;

import General.Validator;

public class DAOFactory {

	/*this method maps the ProductDAO interface
	 * to the appropriate data storage mechanism
	 * @param integer database type,  Scanner input
	 */
	 
	public static RunnersDAO getRunnerDAO(int strDatabase, Scanner input) throws SQLException {
		RunnersDAO rDAO = null;
		int count = 0; // keeps the count to display file not found error
		do {
			if (count++ > 0)
				System.out.println("File not found: Try again");
			switch (strDatabase) {
			case 1:
				rDAO = new RunnerDB(GetFileName(strDatabase, input)); // database
				break;
			case 2:
				rDAO = new RunnerXMLFile(GetFileName(strDatabase, input)); // xml file
				break;
			case 3:
				rDAO = new RunnerTextFile(GetFileName(strDatabase, input)); // text file
				break;
			case 4:
			default: // default runner
				rDAO = new defaultRunner();
			}
		} while (rDAO.GetRunners() == null); // Checks if the input exists if not then asks for input again
		return rDAO;
	}

	/* This function gives the choice to enter users own input file/databae
	 * @param Integer choice , Scanner input
	 */
	public static String GetFileName(int choice, Scanner input) {

		// Following three arrays hold the file type, extensions and default inputs
		String[] fileType = { "Database", "xml file", "text file" };
		String[] fileTypeExt = { "", "xml", "txt", "" };
		String[] defaultFileName = { "RunnersDB", "Resources\\runner.xml", "Resources\\runner.txt", "" };

		String fileName = "", ans = "";
		String prompt = String.format("Do you want to give your own %s  with %s extension?(Y/N)", fileType[choice - 1],
				fileTypeExt[choice - 1]);

		while (!ans.equalsIgnoreCase("y") && !ans.equalsIgnoreCase("n")) {

			ans = Validator.getRequiredString(input, prompt); // gets the input from validator
			if (ans.equalsIgnoreCase("y")) {
				while (true) {
					prompt = String.format("Please enter the file name with %s extension?\n"
							+ "if your file is in a directory Resources enter it as Resources\\filename) :", 
											fileTypeExt[choice - 1]);
					fileName = Validator.getRequiredString(input, prompt); //gets the input from Validator
					
					if (choice == 1) //if database then do not check or extension
						break;
					
					String[] stringToken = fileName.split("\\.");

					if (!fileName.contains(".")) {
						System.out.println("Please enter valid filename");
						continue;
					} else if (!stringToken[1].equals(fileTypeExt[choice - 1])) {
						prompt = String.format("Please enter the file name with %s extension?", 
								                fileTypeExt[choice]);
						continue;
					} else
						break;

				}
			} else if (ans.equalsIgnoreCase("n")) {
				fileName = defaultFileName[choice - 1];
			}
			
			if(fileName.contains("[\\]")) { //replace all \ to \\  
				String regex = "\\+";
				fileName.replaceAll(regex, "\\\\");
			}
					

		}
		System.out.println("Your File name is " + fileName);
		return fileName;
	}

}
