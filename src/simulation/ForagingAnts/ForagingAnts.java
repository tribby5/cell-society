package simulation.ForagingAnts;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

public class ForagingAnts extends Manager{
	
	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Obstacle(),
			new Nest(),
			new FoodSource(),
			new Patch()
	});

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell,
			List<Location> neighborsLoc, List<Integer> neighborCounts) {
		((ForagingAntsCell) currentLocCell.getValue()).update(currentSociety, newSociety, currentLocCell.getKey(), neighborsLoc);
		return true;
	}

}
