package gameOfLifeSimulation;

import java.util.List;

import cellsociety_team13.Cell;

public class GameOfLife {
	// use two state cells
	
	public Cell judge(Cell currentCell, List<Cell> neighborList){
		int liveCount = getLiveNeighborCount(neighborList);
		
		if (currentCell instanceof GOL_OnCell){
			if (liveCount < 2 || liveCount > 3){
				currentCell = new GOL_OffCell((GOL_OnCell) currentCell); 
			}
		} else if (currentCell instanceof GOL_OffCell) {
			if (liveCount == 3){
				currentCell = new GOL_OnCell((GOL_OffCell) currentCell); 
			}
		}
		
		return currentCell;
	}
	
	int getLiveNeighborCount(List<Cell> neighborList){
		int liveCount = 0;
		for(Cell c : neighborList){
			if (c instanceof GOL_OnCell){
				liveCount += 1;
			}
		}
		return liveCount;
	}
}
