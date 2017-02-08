package cellsociety_team13;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Referee {
	
	public abstract List<Cell> getCellTypes();
	private Map<Location, Cell> grid;

	public void updateGrid(Society soc) {
		Map<Location, Cell> newGrid = new HashMap<>();
		grid = soc.getGrid();
		
		
		
		for (Location loc : grid.keySet()){
			Cell currentCell = grid.get(loc);	
			List<Cell> neighborList = pickNeighbors(soc, loc);
			Cell updatedCell = update(currentCell, neighborList); 
			newGrid.put(loc, updatedCell);
		}
		grid = newGrid;
	}

	public Map<Location, Cell> getGrid(){
		return grid;
	}

	public List<Cell> pickNeighbors(Society soc, Location loc){
		// TODO: Pick neighbors
		
		
		return soc.getVertexNeighbors(loc);
		
		//return soc.getSideNeighbors(loc);
	}
	
	private Cell update(Cell currentCell, List<Cell> neighbors){
		List<Integer> neighborCount = countNeighbors(neighbors);
		return currentCell.updateCell(neighbors, neighborCount);
		
	}
	
	private List<Integer> countNeighbors(List<Cell> neighbors){
		Integer[] neighborCount = Collections.nCopies(neighbors.get(0).getMaxState(), 0).toArray(new Integer[0]);
		// from http://stackoverflow.com/questions/2154251/any-shortcut-to-initialize-all-array-elements-to-zero/2154340
		for(Cell neighbor : neighbors){
			neighborCount[neighbor.getState()]++;
		}
		return Arrays.asList(neighborCount);
		
	}
	
}
