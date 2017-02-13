package cellsociety_team13;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import simulation.Fire.Fire;
import simulation.GameOfLife.GameOfLife;
import simulation.PredatorPrey.PredatorPrey;
import simulation.Segregation.Segregation;
import simulation.SlimeMolds.SlimeMolds;

/**
 * This class receives an xml file and reads it, and by reading it means that it takes the needed information and 
 * creates an entire simulation (back end) called as manager.
 * @author Andres Lebbos (afl13)
 */
public class XMLReader {

	/**
	 * A Document Builder that allows reading the xml file.
	 */
	private DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();

	/**
	 * The xml file that is going to be read.
	 */
	private File file;

	/**
	 * The society created from reading the file.
	 */
	private Society society;

	/**
	 * The manager created from reading the file
	 */
	private Manager manager;

	/**
	 * The line (as an element) from the file that is currently being read.
	 */
	private Element currentElement;

	/**
	 * Constructs a class that has the objective of reading an XML file. 
	 * It receives the file it is going to read.
	 * @param xmlFile the file the XML is going to read
	 * @throws XMLException 
	 */
	public XMLReader(File xmlFile) throws XMLException {
		file = xmlFile;
		getManager();
		getSociety();
		manager.setSociety(society);
		if(!(manager instanceof GameOfLife))
			getParameters();
	}

	/**
	 * A list of all the possible managers. Only one is chosen every time the
	 * file is read and it depends on the main information on the file.
	 */
	public static final List<Manager> MANAGERS = Arrays.asList(new Manager[] {
			getGameOfLife(),
			getFire(),
			getPredatorPrey(),
			getSegregation(),
			getSlimeMolds()
	});

	/**
	 * Gives a manager of simulation type Slime Molds.
	 * @return manager of type SlimeMolds
	 */
	private static Manager getSlimeMolds() {
		return new SlimeMolds();
	}

	/**
	 * Gives a manager of simulation type Segregation.
	 * @return manager of type Segregation
	 */
	private static Manager getSegregation() {
		return new Segregation();
	}

	/**
	 * Gives a manager of simulation type Predator Prey.
	 * @return manager of type PredatorPrey
	 */
	private static Manager getPredatorPrey() {
		return new PredatorPrey();
	}

	/**
	 * Gives a manager of simulation type Fire.
	 * @return manager of type Fire
	 */
	private static Manager getFire() {
		return new Fire();
	}

	/**
	 * Gives a manager of simulation type Game of Life.
	 * @return manager of type GameOfLife
	 */
	private static Manager getGameOfLife() {
		return new GameOfLife();
	}

	/**
	 * Gives the manager that is the result of reading the file.
	 * @return manager of type in xml file
	 */
	public Manager extractManager() {
		return manager;
	}

	/**
	 * Reads and saves the needed type of manager from the xml file.
	 * @throws XMLException if the file is not a file of type xml.
	 * 						or if the simulation type in file is not defined.
	 */
	private void getManager() throws XMLException {
		try{
		currentElement = getRootElement();
		if (isValidFile())
			manager = MANAGERS.get(Integer.parseInt(getAttribute(XMLWritter.SIMULATION_TYPE))).copy();
		else
			throw new XMLException("XML file does not represent %s", XMLWritter.SIMULATION_TYPE);
		} catch(Exception e){
			throw new XMLException("Simulation type in file not defined");
		}
	}

	/**
	 * Reads all the necessary parameters from the file and adds them to manager.
	 * @throws XMLException if any of the parameters that the manager needs is not found on the xml file.
	 */
	private void getParameters() throws XMLException {
		try{
			Node nNode = getRootElement().getElementsByTagName(XMLWritter.PARAMETER).item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				currentElement = (Element) nNode;
				Map<String, Double> map = new HashMap<>();
				for(String par: manager.getParametersLabel()){
					map.put(par, Double.parseDouble(getTextValue(par)));
				}
				manager.setParameters(map);
			} else
				throw new XMLException("XML file does not represent some necessary parameter values!");
		} catch(NullPointerException e){
			//If no parametrs, then the class doesn't have any parameters and that should be fine.
		}
	}

	/**
	 * Reads every Cell (its location, the figure type, and the state) and creates a map. Then
	 * it creates a society with the map and saves it in the society field.
	 * @throws XMLException if any of the cell types lacks any information above.
	 */
	private void getSociety() throws XMLException {
		Map<Location, Cell> grid = new HashMap<Location, Cell>();
		NodeList nList = getRootElement().getElementsByTagName(XMLWritter.CELL);
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				currentElement = (Element) nNode;
				Map<String, String> locationData = new HashMap<>();
				for (String field: Location.FIELDS)
					locationData.put(field, getTextValue(field));
				Location newLocation = new Location(locationData);
				Cell newCell = manager.getCellTypes().get(Integer.parseInt(getTextValue(XMLWritter.CELL_TYPE))).copy();
				grid.put(newLocation, newCell);

				society = new Society(grid);
			} else
				throw new XMLException("XML file does not represent some necessary cell values in a cell!");
		}


	}

	/**
	 * Reads the root note of the xml file.
	 * @return the root element
	 * @throws XMLException if the element root is not found or doesn't exist.
	 */
	private Element getRootElement() throws XMLException {
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(file);
			return xmlDocument.getDocumentElement();
		}
		catch (Exception e){
			throw new XMLException(e);
		}
	}

	/**
	 * Checks if the type of file has a valid type of manager
	 * @return true if it has a valid manager type and false if the type is not compatible.
	 */
	private boolean isValidFile () {
		int simType = Integer.parseInt(getAttribute(XMLWritter.SIMULATION_TYPE));
		return simType >= 0 & simType < MANAGERS.size();
	}

	/**
	 * Given a specific field on xml file in the current line, reads the value of that category.
	 * @param att the field value that is being read.
	 * @return the value related with the field being read.
	 */
	private String getAttribute (String att) {
		return currentElement.getAttribute(att);
	}

	/**
	 * Gets a the text related with a specific attribute
	 * @param att the attribute that is going to be found
	 * @return the value related to that attribute on the xml file.
	 * @throws XMLException if the attribute was not found.
	 */
	private String getTextValue (String att) throws XMLException {
		NodeList nodeList = currentElement.getElementsByTagName(att);
		if (nodeList != null && nodeList.getLength() > 0)
			return nodeList.item(0).getTextContent();
		else
			throw new XMLException("Couldn't get attribute for %s", att);
	}

	/**
	 * Creates the document Builder (what is needed to read a xml file)
	 * @return the machine needed to read the file
	 * @throws XMLException is it had problems creating given machine.
	 */
	private static DocumentBuilder getDocumentBuilder() throws XMLException {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e){
			throw new XMLException(e);
		}
	}

}