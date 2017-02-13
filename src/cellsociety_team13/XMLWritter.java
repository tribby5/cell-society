package cellsociety_team13;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class in charge of saving the current state in a new xml file. It need the manager that it is
 * going to write and the address where it will create and save the file.
 * @author Andres Lebbos (afl13)
 */
public class XMLWritter {

	/**
	 * The tag for the type of simulation in the xml file.
	 */
	public static final String SIMULATION_TYPE = "simulationType";

	/**
	 * The tag for a new cell in the xml file.
	 */
	public static final String CELL = "Cell";

	/**
	 * The tag for the type of cell in the xml file.
	 */
	public static final String CELL_TYPE = "CellType";
	
	/**
	 * The tag for the parameters in the xml file.
	 */
	public static final String PARAMETER = "Parameter";
	
	/**
	 * The document where the file is being wrote.
	 */
	private Document doc;
	
	/**
	 * The address where the file is being saved.
	 */
	private String add;

	/**
	 * The society that is going to be saved.
	 */
	private Society soc;
	
	/**
	 * The manager that is going to be saved.
	 */
	private Manager man;

	/**
	 * The root element of the file when writing.
	 */
	private Element root;

	/**
	 * Constructs a class that has the objective of writing an XML file.
	 * @param pAddress where it should save the file in memory
	 * @param pMan what simulation it is saving in the file
	 * @throws XMLException if anything goes wrong.
	 */
	public XMLWritter(String pAddress, Manager pMan) throws XMLException {
		add = pAddress;
		doc = getDocumentBuilder().newDocument();
		man = pMan;
		soc = pMan.getSociety();
		writeElements();
	}
	
	/**
	 * Attempts to write the file and saves it
	 * @throws XMLException if anything goes wrong
	 */
	private void writeElements() throws XMLException {
		writeRoot();
		writeParameters();
		writeSociety();
		saveFile();
	}

	/**
	 * Saves the file in the givven address 
	 * @throws XMLException If the saving process goes wrong
	 */
	private void saveFile() throws XMLException {
		try{
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(add));

			transformer.transform(source, result);
	  } catch (Exception e) {
		  	throw new XMLException("Couldn't create file at: " + add);
	  } 
	}

	/**
	 * Writes everything related to the cells in the map in the file
	 */
	private void writeSociety() {
		Map<Location, Cell> grid = soc.getGridCopy();
		for(Location loc: grid.keySet()){
			Element cell = doc.createElement(CELL);
			root.appendChild(cell);

			for(String field: Location.FIELDS){
				Element info = doc.createElement(field);
				info.appendChild(doc.createTextNode(loc.getInfo(field)));
				cell.appendChild(info);
			}
			
			Element cellType = doc.createElement(CELL_TYPE);
			cellType.appendChild(doc.createTextNode(grid.get(loc).getState()+""));
			cell.appendChild(cellType);
		}
	}

	/**
	 * Writes all the parameters specific to the simulation on the file
	 */
	private void writeParameters() {
		Element params = doc.createElement(PARAMETER);
		root.appendChild(params);

		for(String par: man.getParametersLabel()){
			Element param = doc.createElement(par);
			param.appendChild(doc.createTextNode(man.getParameterValue(par)));
			params.appendChild(param);
		}	
	}

	/**
	 * Writes the main root of the file.
	 */
	private void writeRoot() {
		root = doc.createElement("Simulation");
		Attr attr = doc.createAttribute(SIMULATION_TYPE);
		attr.setValue(man.getType()+"");
		root.setAttributeNode(attr);
		doc.appendChild(root);
	}
	
	/**
	 * Creates the document Builder (what is needed to read a xml file)
	 * @return the machine needed to read the file
	 * @throws XMLException is it had problems creating given machine.
	 */
	private DocumentBuilder getDocumentBuilder() throws XMLException {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e){
			throw new XMLException(e);
		}
	}
}
