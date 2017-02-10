package simulation.Fire;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;


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

	@Override
	protected void update(Society currentSociety, Society newSociety, Location currentLoc,
			List<Location> neighborsLoc, List<Integer> neighborCounts) {
		FireCell currentCell = (FireCell) currentSociety.get(currentLoc);
		FireCell updatedCell = currentCell.updateCell(neighborCounts);
		newSociety.put(currentLoc, updatedCell);
	}

}
