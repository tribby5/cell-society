package simulation.Segregation;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import referees.Locator;

public class Segregation extends Locator{

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
			new Segregation_OCell(),
			new Segregation_XCell(),
			new Segregation_EmptyCell()
	});

	public Segregation() {
		super();
		// TODO: set satisficationPercentage
		this.satisficationPercentage = 50.0;
	}


	@Override
	public Cell judge(Cell currentCell, List<Cell> neighborList) {
		if (!isCellSatisfied(currentCell, neighborList)){
			currentCell = currentCell.change(1);
		}
		return currentCell;
	}

	private boolean isCellSatisfied(Cell currentCell, List<Cell> neighborList) {
		int busyNeig = 0;
		int sameNeig = 0;
		for(Cell neighbor : neighborList)
			if(neighbor.isNotEmpty()){
				busyNeig++;
				if(neighbor.getClass().equals(currentCell.getClass()))
					sameNeig++;
			}

		return (sameNeig * 1.0 / busyNeig) >= satisficationPercentage ;
	}

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}
}
