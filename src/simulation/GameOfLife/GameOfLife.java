package simulation.GameOfLife;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Referee;

public class GameOfLife extends Referee{
	/* Rules:
	 * 
	 * side Neighbors
	 * 
	 * Live:
	 *  - if less than 2 live neighbors -> dies
	 *  - if more than 3 live neighbors -> dies
	 * 
	 * Dead:
	 *  - if 3 live neighbors -> lives
	 */
	
	// use two state cells
	private static final List<Cell> CELLS = Arrays.asList(new Cell[] {
			new GOL_OffCell(),
			new GOL_OnCell()
	});

	public Cell judge(Cell currentCell, List<Cell> neighborList){
		int liveCount = getLiveNeighborCount(neighborList);
		return currentCell.change(liveCount);
	}

	public int getLiveNeighborCount(List<Cell> neighborList){
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