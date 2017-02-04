package simulation.Segregation;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Referee;

public class Segregation extends Referee{

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
