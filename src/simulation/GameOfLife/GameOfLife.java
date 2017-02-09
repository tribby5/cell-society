package simulation.GameOfLife;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Manager;

public class GameOfLife extends Manager{

	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Off(),
			new On()
	});

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

}
