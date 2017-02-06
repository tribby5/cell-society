package referees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Referee;
import cellsociety_team13.Society;

public abstract class NoLocator extends Referee {
	
	private Map<Location, Cell> grid;
	
	@Override
	public void giveSociety(Society soc){
		Map<Location, Cell> newGrid = new HashMap<>();
		grid = soc.getGrid();
		for (Location loc : grid.keySet()){
			Cell currentCell = grid.get(loc);
			List<Cell> neighborList = soc.getSideNeighbors(loc);
			Cell updatedCell = judge(currentCell, neighborList);
			newGrid.put(loc, updatedCell);
		}
		grid = newGrid;
	}
	
	@Override
	public Map<Location, Cell> getGrid(){
		return grid;
	}
}
