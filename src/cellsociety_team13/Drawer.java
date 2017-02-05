package cellsociety_team13;

import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public class Drawer {
	
	public Group draw(Group root, Society mySociety){
		Map<Location, Cell> Grid = mySociety.getGrid();
		double scale = getScale(mySociety.getFurthestPoint());
		for(Location loc: Grid.keySet()){
			loc.multiply(scale);
			draw(loc.getPolygon());
		}
		return root;
	}

	private double getScale(Point2D point) {
		double xScale = Interface.GRID_WIDTH/point.getX();
		double yScale = Interface.GRID_HEIGHT/point.getY();
		return Math.max(xScale, yScale);
	}
}