package cellsociety_team13;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {

	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();

	private static final String SIMULATION_TYPE = "simulationType";

	private static final String CELL = "Cell";

	private static final String CELL_TYPE = "CellType";

	private File file;

	private Referee referee;

	private Society society;

	private Element currentElement; 

	public XMLReader(File xmlFile) {
		file = xmlFile;
		getReferee();
		getSociety();
	}

	public Manager getManager() {
		return new Manager(society, referee);
	}

	private void getReferee() {
		currentElement = getRootElement();
		if (isValidFile())
			referee = Referee.REFEREES.get(Integer.parseInt(getAttribute(SIMULATION_TYPE)));
		else
			throw new XMLException("XML file does not represent %s", SIMULATION_TYPE);
	}

	private void getSociety() {
		Map<Location, Cell> grid = new HashMap<Location, Cell>();
		NodeList nList = currentElement.getElementsByTagName(CELL);
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				currentElement = (Element) nNode;
				Map<String, String> locationData = new HashMap<>();
				for (String field: Location.FIELDS)
					locationData.put(field, getTextValue(field));
				System.out.println(locationData);
				grid.put(new Location(locationData), referee.getCellTypes().get(Integer.parseInt(getTextValue(CELL_TYPE))));
			} else
				throw new XMLException("XML file does not represent some necessary cell values!");
		}
		society = new Society(grid);
	}

	private Element getRootElement() {
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(file);
			return xmlDocument.getDocumentElement();
		}
		catch (SAXException | IOException e){
			throw new XMLException(e);
		}
	}

	private boolean isValidFile () {
		int simType = Integer.parseInt(getAttribute(SIMULATION_TYPE));
		return simType >= 0 & simType < Referee.REFEREES.size();
	}

	private String getAttribute (String att) {
		return currentElement.getAttribute(att);
	}

	private String getTextValue (String att) {
		NodeList nodeList = currentElement.getElementsByTagName(att);
		if (nodeList != null && nodeList.getLength() > 0)
			return nodeList.item(0).getTextContent();
		else
			throw new XMLException("Couldn't get attribute for %s", att);
	}

	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e){
			throw new XMLException(e);
		}
	}
}