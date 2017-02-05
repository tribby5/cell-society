package simulation.PredatorPrey;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Referee;

public class PredatorPrey extends Referee{
	
	/* Rules:
	 * 
	 * only side Neighbors
	 * 
	 * Fish:
	 *  - will move to a random adjacent water cell
	 *     - if enough energy, places new fish where it was (reset energy) 
	 * 
	 * Shark:
	 * - will eat one random neighbor fish
	 * 		- gains energy of fish
	 * - else: will move to a random adjacent water cell
	 * - at zero energy, it dies
	 * - 
	 * 
	 * Reproduction:
	 * - fish or shark will reproduce if they have survived enough turns
	 * - new fish or shark will be placed in random neighbor water cell, if one is available
	 * 
	 * 
	 */

	private static final List<Cell> CELLS = Arrays.asList(new Cell[] {
			new PredatorPrey_WaterCell(),
			new PredatorPrey_FishCell(),
			new PredatorPrey_SharkCell()
	});

	@Override
	public Cell judge(Cell currentCell, List<Cell> neighborList) {
		if(currentCell.isNotEmpty()){
			//If needed put into specific cell
			//int waterNeighborCount = countNeighborType(neighborList, CELLS.get(0));
			//int fishNeighborCount = countNeighborType(neighborList, CELLS.get(1));
			currentCell = currentCell.surroundChange(currentCell, neighborList);
		}
		return currentCell;
	}

	/*private int countNeighborType(List<Cell> neighborList, Cell testCell) {
		int count = 0;
		for (Cell neighbor : neighborList){
			if (neighbor.getClass().equals( testCell.getClass())){
				count++;
			}
		}
		return count;
	}*/

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

}