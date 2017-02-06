package simulation.Fire;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import referees.NoLocator;

public class Fire extends NoLocator{
	/*Rules:
	 * 
	 * side Neighbors
	 * 
	 * Tree:
	 *  - if any neighbors are burning
	 *     - check if random number [0,1] is less than probCatch
	 *       - if less -> burning
	 *       - else -> leave as tree
	 *  - else -> leave as tree
	 * 
	 * Burning:
	 *  - after one turn of existence -> empty
	 *       
	 * Empty:
	 * - stays empty
	 * 
	 * 
	 */

	private static final List<Cell> CELLS = Arrays.asList(new Cell[] {
			new FIRE_Dead(),
			new FIRE_Burning(),
			new FIRE_Alive()
	});

	@Override
	public Cell judge(Cell currentCell, List<Cell> neighborList) {
		return currentCell.change(countBurningNeighbors(neighborList));
	}
	

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}
		
	private int countBurningNeighbors(List<Cell> neighborList){
		int count = 0;
		for (Cell c : neighborList){
			if (c instanceof FIRE_Burning){
				count += 1;
			}
		}
		return count;
	}
}
