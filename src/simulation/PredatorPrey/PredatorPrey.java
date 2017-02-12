package simulation.PredatorPrey;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

public class PredatorPrey extends Manager{
	
	private static final List<String> PARAMETERS = Arrays.asList(new String[] {
			"par1",
			"par2"
	});;
	
	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Water(),
			new Fish(),
			new Shark()
	});

	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}
	
	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		PredatorPreyCell currentCell = (PredatorPreyCell) currentLocCell.getValue();
		currentCell.act(currentSociety, newSociety, currentLocCell.getKey(), neighborsLoc, neighborCounts);
		return true;
		
	}

	@Override
	public Manager copy() {
		return new PredatorPrey();
	}
	
	public List<String> getParametersLabel(){
		return PARAMETERS;
	}

	@Override
	public void setParameters(Map<String, Double> data) {
		// TODO Auto-generated method stub
		
	}
}
