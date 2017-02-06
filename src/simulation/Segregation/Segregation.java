package simulation.Segregation;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Referee;

public class Segregation extends Referee{
	private double satisficationPercentage;
	
	public Segregation() {
		super();
		// TODO: set satisficationPercentage
		this.satisficationPercentage = 50.0;
	}

	/* Rules:
	 * 
	 * if X or O:
	 *  - check if satisfied (whether enough similar neighbors)
	 *     - yes -> no change
	 *     - no -> relocate depending on chosen algorithm 
	 *     
	 */
	
	private static final List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Segregation_EmptyCell(),
			new Segregation_OCell(),
			new Segregation_XCell()
	});
	

	@Override
	public Cell judge(Cell currentCell, List<Cell> neighborList) {
		if(currentCell.isNotEmpty()){
			if (!isCellSatisfied(currentCell, neighborList)){
				// TODO: relocate cell
			}
		} 
		return currentCell;
	}

	private boolean isCellSatisfied(Cell currentCell, List<Cell> neighborList) {
		int neighborNonEmptyCount = 0;
		int neighborSameCellCount = 0;
		for(Cell neighbor : neighborList){
			if(neighbor.isNotEmpty()){
				neighborNonEmptyCount++;
				if(neighbor.getClass().equals( currentCell.getClass())){
					neighborSameCellCount++;
				}
			}
		}
		
		return (neighborSameCellCount * 1.0 / neighborNonEmptyCount) >= satisficationPercentage ;
	}

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

}
