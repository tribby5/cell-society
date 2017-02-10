package cellsociety_team13;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class Drawer {
	
	public Group draw(Group root, Society mySociety, boolean notScaled){
		for(Location loc: mySociety.keySet()){
			if(notScaled){
				loc.moveAndScaleShape(mySociety.getBottomRightPoint().subtract(mySociety.getTopLeftPoint()));
			}
			Polygon p = loc.getPolygon();
			p.setFill(mySociety.get(loc).getColor());
			root.getChildren().add(p);
		}
		return root;
	}
}