package simulation.ForagingAnts;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

public class ForagingAnts extends Manager{
	
	private static final List<String> PARAMETERS = Arrays.asList(new String[] {
			"par1"
	});;
	
	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Patch(),
			new Nest(),
			new FoodSource(),
			new Obstacle()
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
	
	public Manager copy(){
		return new ForagingAnts();
	}
	
	public List<String> getParametersLabel(){
		return PARAMETERS;
	}

	@Override
	public void setParameters(Map<String, Double> data) {
		// TODO Auto-generated method stub
	}

	@Override
	public void createParametersBounds() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateParameter(String parameterLabel, double newValue) {
		// TODO Auto-generated method stub
		
	}

}
