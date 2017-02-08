package simulation.GameOfLife;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Referee;

public class GameOfLifeReferee extends Referee{
	
	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Off(),
			new On()
	});

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

}
