package simulation.Segregation;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Referee;

public class Segregation extends Referee{
	/* Rules:
	 * 
	 * if X or O:
	 *  - check if satisfied (whether enough similar neighbors)
	 *     - yes -> no change
	 *     - no -> relocate depending on chosen algorithm 
	 *     
	 */
	
	private double satisficationPercentage;
	
	private static final List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Segregation_EmptyCell(),
			new Segregation_OCell(),
			new Segregation_XCell()
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
