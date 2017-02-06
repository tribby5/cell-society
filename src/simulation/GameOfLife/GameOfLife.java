package simulation.GameOfLife;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import referees.NoLocator;

public class GameOfLife extends NoLocator{
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
	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			getGOL_OffCell(),
			getGOL_OnCell()
	});
	

	private Cell getGOL_OnCell() {
		return new GOL_OnCell();
	}

	private Cell getGOL_OffCell() {
		return new GOL_OffCell();
	}
	
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

	@Override
	public List<Cell> pickNeighbors(Society soc, Location loc) {
		return soc.getVertexNeighbors(loc);
	}
}