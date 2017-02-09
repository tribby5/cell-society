package simulation.Fire;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Manager;


public class Fire extends Manager{


	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Dead(),
			new Burning(),
			new Alive()
	});

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

}
