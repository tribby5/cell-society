package gameOfLifeSimulation;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Referee;

public class GameOfLife extends Referee{
	// use two state cells
	private static final List<Cell> CELLS = Arrays.asList(new Cell[] {
			new GOL_OffCell(),
			new GOL_OnCell()
		});
	
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

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}
}
