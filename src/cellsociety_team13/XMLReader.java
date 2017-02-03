package cellsociety_team13;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.effect.Light.Point;

public class XMLReader {

	public XMLReader(File xmlFile) {
		// TODO Auto-generated constructor stub

	}
	
	public Point getMaxPoint( ) {
		return null;
	}

	public Manager getManager() {
		// TODO Auto-generated method stub
		return new Manager(getSociety(), getReferee());
	}
	
	public Society getSociety() {
		Map<Location, Cell> grid = new HashMap<Location, Cell>();
		Society s = new Society(grid);
		return null;
	}

	public Referee getReferee() {
		// TODO Auto-generated method stub
		return null;
	}


}
