package referees;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Referee;
import gameOfLifeSimulation.GOL_OffCell;
import gameOfLifeSimulation.GOL_OnCell;

public class Predator_Prey extends Referee{

	private static final List<Cell> CELLS = Arrays.asList(new Cell[] {
			
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
