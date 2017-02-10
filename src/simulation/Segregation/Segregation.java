package simulation.Segregation;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Manager;
import managerTypes.NotInPlaceManager;

public class Segregation extends NotInPlaceManager{
	
	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Empty(),
			new O(),
			new X()
	});

	public List<Cell> getCellTypes() {
		return CELLS;
	}

}
