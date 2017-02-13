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

public class XMLWritter {

	public static final String SIMULATION_TYPE = "simulationType";

	public static final String CELL = "Cell";

	public static final String CELL_TYPE = "CellType";

	public static final String PARAMETER = "Parameter";
	
	private Document doc;
	
	private String add;

	private Society soc;
	
	private Manager man;

	private Element root;

	public XMLWritter(String pAddress, Manager pMan) throws XMLException {
		add = pAddress;
		doc = getDocumentBuilder().newDocument();
		man = pMan;
		soc = pMan.getSociety();
		writeElements();
	}
	
	private void writeElements() throws XMLException {
		writeRoot();
		writeParameters();
		writeSociety();
		saveFile();
	}

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

	private void writeParameters() {
		Element params = doc.createElement(PARAMETER);
		root.appendChild(params);

		for(String par: man.getParametersLabel()){
			Element param = doc.createElement(par);
			param.appendChild(doc.createTextNode(man.getParameterValue(par)));
			params.appendChild(param);
		}	
	}

	private void writeRoot() {
		root = doc.createElement("Simulation");
		Attr attr = doc.createAttribute(SIMULATION_TYPE);
		attr.setValue(man.getType()+"");
		root.setAttributeNode(attr);
		doc.appendChild(root);
	}
	
	private DocumentBuilder getDocumentBuilder() throws XMLException {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e){
			throw new XMLException(e);
		}
	}
}
