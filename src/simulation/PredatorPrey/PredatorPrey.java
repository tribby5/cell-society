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
	 * 
	 * Shark:
	 * - will eat one random neighbor fish
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

}
