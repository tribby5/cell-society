package cellsociety_team13;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class Drawer {
	public Group drawBoard(Group root, Society soc){
		Map<Location, Cell> grid = soc.getGrid();
		for(Location loc: grid.keySet()){
			drawPolygon(root, soc, loc);
		}
		return root;
	}

	private void drawPolygon(Group root, Society soc, Location loc) {
		loc.scale(soc.getFurthestPoint());
		Polygon p = loc.getPolygon();
		p.setFill(soc.getGrid().get(loc).getState());
		root.getChildren().add(p);
	}
}