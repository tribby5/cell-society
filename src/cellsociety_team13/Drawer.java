package cellsociety_team13;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;

public class Drawer {
	private Group myRoot;

	Drawer(Group root){
		this.myRoot = root;
	}
	
	void draw(Node n){
		myRoot.getChildren().add(n);
	}
	
	void draw(Society mySociety){
		Map<Location, Cell> Grid = mySociety.getGrid();
		for(Location loc: Grid.keySet()){
			draw(loc.getPolygon());
		}
	}
	
	
}
