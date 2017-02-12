package simulation.GameOfLife;

import java.util.Arrays;
import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

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
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell,
			List<Location> neighborsLoc, List<Integer> neighborCounts) {
		GameOfLifeCell currentCell = (GameOfLifeCell) currentLocCell.getValue();
		GameOfLifeCell updatedCell = currentCell.updateCell(neighborCounts);
		newSociety.put(currentLocCell.getKey(), updatedCell);
		return true;
	}

	@Override
	public Manager copy() {
		return new GameOfLife();
	}
	

}
