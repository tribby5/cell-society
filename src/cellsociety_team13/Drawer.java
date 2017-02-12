package cellsociety_team13;

import java.util.HashMap;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Drawer {
	private HashMap<Integer, Color> states;
	
	public Group draw(Group root, Manager myManager, boolean notScaled){
		Society mySociety = myManager.getSociety();
		for(Location loc: mySociety.keySet()){
			if(notScaled){
				loc.moveAndScaleShape(mySociety.getBottomRightPoint().subtract(mySociety.getTopLeftPoint()));
				createStates(myManager);
			}
			Polygon p = loc.getPolygon();
			p.setOnMouseClicked(e -> updateCell(loc, mySociety, myManager));
			
			/*
				int count = 0;
				for(Cell cell : mySociety.values()){
					if(cell.getState() == 0){
						count++;
					}
				}
				System.out.println("Zero Count: " + count);
			*/
			
			p.setFill(mySociety.get(loc).getColor());
			root.getChildren().add(p);
			
		}
		return root;
	}
	
	private void updateCell(Location loc, Society society, Manager manager){
		Cell cell = society.get(loc);
		cell.changeState(states);
		society.put(loc, cell);
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
