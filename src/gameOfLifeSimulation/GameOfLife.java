package referees;

import java.util.List;

import cells.OnCell;
import cells.OffCell;
import cellsociety_team13.Cell;

public class GameOfLife {
	// use two state cells
	
	public Cell judge(Cell currentCell, List<Cell> neighborList){
		int liveCount = getLiveNeighborCount(neighborList);
		
		if (currentCell instanceof OnCell){
			if (liveCount < 2 || liveCount > 3){
				currentCell = new OffCell((OnCell) currentCell); 
			}
		} else if (currentCell instanceof OffCell) {
			if (liveCount == 3){
				currentCell = new OnCell((OffCell) currentCell); 
			}
		}
		
		return currentCell;
	}
	
	int getLiveNeighborCount(List<Cell> neighborList){
		int liveCount = 0;
		for(Cell c : neighborList){
			if (c instanceof OnCell){
				liveCount += 1;
			}
		}
		return liveCount;
	}
}
