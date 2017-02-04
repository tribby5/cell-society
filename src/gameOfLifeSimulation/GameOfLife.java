package gameOfLifeSimulation;

import java.util.List;

import cells.TwoStateCell;
import cellsociety_team13.Cell;
import cellsociety_team13.Referee;

public class GameOfLife extends Referee{
	// use two state cells
	
	public Cell judge(Cell currentCell, List<Cell> neighborList){
		int liveCount = getLiveNeighborCount(neighborList);
		return currentCell.change(liveCount);
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
