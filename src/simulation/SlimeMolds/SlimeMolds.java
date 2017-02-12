package simulation.SlimeMolds;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

public class SlimeMolds extends Manager{

	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Empty(),
			new Patch(),
			new Slime()
	});
	
	public SlimeMolds(){
		//TODO initialize variables
	}
	
	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell,
			List<Location> neighborsLoc, List<Integer> neighborCounts) {
		((SlimeMoldsCell) currentLocCell.getValue()).update(currentSociety, newSociety, currentLocCell.getKey(), neighborsLoc, neighborCounts);
		return true;
	}

	public Manager copy(){
		return new SlimeMolds();
	}
}
