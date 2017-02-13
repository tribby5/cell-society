package cellsociety_team13;

import java.util.HashMap;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Drawer {
	private HashMap<Integer, Color> states;
	
	/**
	 * Draws the grid of cells onto the root. 
	 * @param root Group which the polygons will be added to
	 * @param myManager Manager of the specific simulation
	 * @param notScaled Boolean which represents whether or not the points have been scaled yet
	 * @return root that is updated with grid of cells
	 */
	public Group draw(Group root, Manager myManager, boolean notScaled){
		Society mySociety = myManager.getSociety();
		for(Location loc: mySociety.keySet()){
			if(notScaled){
				loc.moveAndScaleShape(mySociety.getBottomRightPoint().subtract(mySociety.getTopLeftPoint()));
				createStates(myManager);
			}
			Polygon p = loc.getPolygon();
			p.setOnMouseClicked(e -> updateCell(loc, mySociety, myManager));
			p.setFill(mySociety.get(loc).getColor());
			root.getChildren().add(p);
			
		}
		return root;
	}
	
	private void updateCell(Location loc, Society society, Manager manager){
		Integer oldCellState = society.get(loc).getState();
		Integer newCellState = (oldCellState + 1) %  society.get(loc).getMaxState();
		Cell newCell = manager.getCellTypes().get(newCellState).copy();
		
		society.put(loc, newCell);
		loc.applyColorStateToPolygon(society.get(loc).getColor());
		manager.setSociety(society);
	}
	
	private void createStates(Manager manager){
		states = new HashMap<Integer, Color>();
		List<Cell> cellTypes =  manager.getCellTypes();
		for(int i = 0; i < cellTypes.size(); i++){
			if(!states.containsKey(cellTypes.get(i).getState())){
				states.put(cellTypes.get(i).getState(),cellTypes.get(i).getColor());
			}
		}
	}
}
