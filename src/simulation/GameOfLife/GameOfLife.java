package simulation.GameOfLife;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;

public class GameOfLife extends Manager{

	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Off(),
			new On()
	});

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}
	
	@Override
	protected void update(Society currentSociety, Society newSociety, Location currentLoc,
			List<Location> neighborsLoc, List<Integer> neighborCounts) {
		GameOfLifeCell currentCell = (GameOfLifeCell) currentSociety.get(currentLoc);
		GameOfLifeCell updatedCell = currentCell.updateCell(neighborCounts);
		newSociety.put(currentLoc, updatedCell);
	}
	

}
