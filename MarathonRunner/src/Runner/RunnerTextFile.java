/**
 * RunnerTextFile.java
 * @author {Mugdha Phadke}
 *
 *This class reads from the text file and loads the RunnesDAO
 */

package Runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class RunnerTextFile implements RunnersDAO {
	private ArrayList<Runner> Runners = null; // array of runners to hold the runners
	private Path RunnersPath = null;
	private File RunnersFile = null;
	private final String FIELD_SEP = "\t"; // field separator regex

	RunnerTextFile(String fileName) {

		RunnersPath = Paths.get(fileName);
		RunnersFile = RunnersPath.toFile();
		Runners = this.GetRunners(); // get the runners fron the file
	}

	// Read the input file, fill the runners array and return to fill the runnersDAO
	public ArrayList<Runner> GetRunners() {
		// if the Runners file has already been read, don't read it again
		if (Runners != null)
			return Runners;

		Runners = new ArrayList<>(); // Allocate memory for the array

		if (Files.exists(RunnersPath)) // prevent the FileNotFoundException
		{
			try (BufferedReader in = // to read in the file
					new BufferedReader(new FileReader(RunnersFile))) {
				// read all Runners stored in the file
				// into the array list
				String line = in.readLine();
				while (line != null) {
					String[] stringToken = line.split(FIELD_SEP); // split line in fields with tab delimiter
					Runners.add(new Runner(stringToken[0], // should contain name
							Integer.parseInt(stringToken[1]), // Should contain speed
							Integer.parseInt(stringToken[2]))); // should contain restPercentage
					line = in.readLine();
				}
			} catch (IOException e) {
				// System.out.println(e);
				return null;
			}
		} else // if file not found then return null
			return null;
		return Runners; // return the array if all goes well
	}

}
