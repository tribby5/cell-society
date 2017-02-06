package cellsociety_team13;

import java.util.Map;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class Drawer {
	
	public Group draw(Group root, Society mySociety, boolean first){
		Map<Location, Cell> Grid = mySociety.getGrid();
		
		for(Location loc: Grid.keySet()){
			if(first){
			loc.moveCenter(mySociety.getFurthestPoint());
			loc.resetShape(mySociety.getFurthestPoint());
			}
			
			
			//loc.getPoly().setSideLength(Shape.INITIAL_SIDE_LENGTH * (Interface.GRID_WIDTH/mySociety.getFurthestPoint().getX()));
			//loc.getPoly().recalculateVertices();
			//loc.regeneratePolygon();
			Polygon p = loc.getPolygon();
			p.setFill(Grid.get(loc).getState());
			root.getChildren().add(p);
		}
		return root;
	}
}