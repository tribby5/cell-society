package cellsociety_team13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
	private Referee myReferee;
	private Society mySociety;
	

	Manager(Society society, Referee referee){
		myReferee = referee;
		mySociety = society;
		
	}


	public Society getSociety(){
		return mySociety;
	}


	public void update() {
		Map<Location, Cell> oldGrid = mySociety.getGrid(); 
		Map<Location, Cell> newGrid = new HashMap<Location, Cell>();
		
		for (Location loc : oldGrid.keySet()){
			Cell currentCell = oldGrid.get(loc);
			List<Cell> neighborList = mySociety.getNeighbors(loc);
			Cell updatedCell = myReferee.judge(currentCell, neighborList);
			newGrid.put(loc, updatedCell);
		}
		
		mySociety.updateGrid(newGrid);
		
	}
	
}
