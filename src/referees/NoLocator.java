package referees;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Referee;

public abstract class NoLocator extends Referee {

	@Override
	public void addChangers(Location loc, Cell currentCell, Cell updatedCell) {
		// do nothing
	}

	@Override
	public void setRelocaters() {
		// do nothing
	}
	
	@Override
	public void relocate() {
		// do nothing
	}
	
}
