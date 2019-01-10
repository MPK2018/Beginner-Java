/*
 * @filename RunnerXMLFile.java
 * @ author Mugdha Phadke
 * This class reads runners from the xml file and freturns the array to runnersDAO
 */
package Runner;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import javax.xml.stream.*; // StAX API

class RunnerXMLFile implements RunnersDAO {
	private Path runnersPath = null;
	private ArrayList<Runner> runners = null; // Array hold the Runners in an array

	public RunnerXMLFile(String fileName) {
		runnersPath = Paths.get(fileName);
		runners = this.GetRunners(); // reads the runners from file and fills the runners array
	}

	public ArrayList<Runner> GetRunners() {
		// if the XML file has already been read, don't read it again
		if (runners != null)
			return runners;

		runners = new ArrayList<>(); // assign the memory for the array

		if (Files.exists(runnersPath)) // prevent the FileNotFoundException
		{
			// create the XMLInputFactory object
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// XmlStreamReaderVariable.next();
			try {
				// create a XMLStreamReader object
				FileReader fileReader = new FileReader(runnersPath.toFile());
				XMLStreamReader reader = inputFactory.createXMLStreamReader(fileReader);

				// variables to store the data read from file
				int speed = 0;
				String Name = "";
				int restPercentage = 0;

				// read the Runners from the file

				while (reader.hasNext()) {

					int eventType = reader.getEventType();
					switch (eventType) {
					case XMLStreamConstants.START_ELEMENT:
						String elementName = reader.getLocalName();
						if (elementName.equals("RunnersMoveIncrement"))
							speed = Integer.parseInt(reader.getElementText()); // store the data in speed
						else if (elementName.equals("Runner"))
							Name = reader.getAttributeValue(0); // store the data in
						else if (elementName.equals("RestPercentage")) {
							restPercentage = Integer.parseInt(reader.getElementText()); // store the data in
							runners.add(new Runner(Name, speed, restPercentage)); // create and add the runner in an
																					// array
						}
						break;
					case XMLStreamConstants.END_ELEMENT:
						elementName = reader.getLocalName();

						break;
					default:
						break;
					}
					reader.next();
				}
			} catch (IOException | XMLStreamException e) {
				// System.out.println(e);
				return null;
			}
		} else // if file does nor exist return null

			return null;

		return runners; // this means all went well return an array
	}

}