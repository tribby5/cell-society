package simulation.Fire;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;


public class Fire extends Manager{


	private static final List<String> PARAMETERS = Arrays.asList(new String[] {
			"par1"
	});;
	
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
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell,
		List<Location> neighborsLoc, List<Integer> neighborCounts) {

		
		FireCell currentCell = (FireCell) currentLocCell.getValue();
		FireCell updatedCell = currentCell.updateCell(neighborCounts);
		newSociety.put(currentLocCell.getKey(), updatedCell);
		
		return true;
	}

	@Override
	public Manager copy() {
		return new Fire();
	}
	
	public List<String> getParametersLabel(){
		return PARAMETERS;
	}

	@Override
	public void setParameters(Map<String, Double> data) {
		// TODO Auto-generated method stub
		
	}

}
