package cellsociety_team13;

import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;

public class Drawer {
	private Group myRoot;

	public Drawer(Group root){
		this.myRoot = root;
	}
	
	private void draw(Node n){
		myRoot.getChildren().add(n);// TODO JAVA THROWS EXCEPTION: Repeated elements on root?
	}
	
	public void draw(Society mySociety){
		Map<Location, Cell> Grid = mySociety.getGrid();
		double scale = getScale(mySociety.getFurthestPoint());
		for(Location loc: Grid.keySet()){
			draw(loc./*multiply(scale).*/getPolygon());// TODO THIS LINE IS WRONG CAREFUL
		}
	}

	private double getScale(Point2D point) {
		double xScale = Interface.WIDTH/point.getX();
		double yScale = Interface.HEIGHT/point.getY();
		return Math.max(xScale, yScale);
	}
	
	
}
