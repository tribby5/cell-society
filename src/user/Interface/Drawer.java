package user.Interface;

import java.util.Map;

import cellsociety_team13.*;
import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class Drawer {
	
	public Group draw(Group root, Society mySociety, boolean notScaled){
		Map<Location, Cell> Grid = mySociety.getGrid();
		
		for(Location loc: Grid.keySet()){
			if(notScaled){
				loc.moveAndScaleShape(mySociety.getBottomRightPoint().subtract(mySociety.getTopLeftPoint()));
			}
			Polygon p = loc.getPolygon();
			p.setFill(mySociety.getGrid().get(loc).getState());
			root.getChildren().add(p);
		}
		return root;
	}
}