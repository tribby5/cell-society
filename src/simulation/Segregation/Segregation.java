package simulation.Segregation;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

public class Segregation extends Manager{
	
	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Empty(),
			new O(),
			new X()
	});

	public List<Cell> getCellTypes() {
		return CELLS;
	}

	@Override
	protected void update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		// TODO Auto-generated method stub
		
	}

}
