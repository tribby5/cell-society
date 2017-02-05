package cellsociety_team13;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class Drawer {
	public Group draw(Group root, Society mySociety){
		Map<Location, Cell> Grid = mySociety.getGrid();
		for(Location loc: Grid.keySet()){
			loc.scale(mySociety.getFurthestPoint());
			Polygon p = loc.getPolygon();
			p.setFill(Grid.get(loc).getState());
			root.getChildren().add(p);
		}
		return root;
	}
}