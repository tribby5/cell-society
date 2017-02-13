package simulation.Segregation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

public class Segregation extends Manager{
	public static final String SATISFACTION_PARAMETER_LABEL = "par1";
	public static final String SATISFACTION_PARAMETER_LABEL_GUI = "Satisfaction Percentage";
	
	private Double satisfactionPercentage;
		
	private List<Double> satisfactionPercentageBounds = Arrays.asList(new Double[] {
			0.0,
			satisfactionPercentage,
			100.0
	});;
	
	private static final List<String> PARAMETERS = Arrays.asList(new String[] {
			SATISFACTION_PARAMETER_LABEL
	});;
	
	private List<Cell> CELLS = Arrays.asList(new Cell[] {
			new Empty(),
			new O(),
			new X()
	});

	public List<Cell> getCellTypes() {
		return CELLS;
	}

	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		Cell currentCell = currentLocCell.getValue();
		if(!isSatisfied(neighborCounts.get(currentCell.getState()), neighborsLoc.size())){
			int emptyCount = countEmpties(currentSociety);
			return (currentCell.swapWithRandomTarget(currentSociety, newSociety, currentLocCell.getKey(), currentSociety.keySet(), emptyCount, currentCell.getDefaultEmptyState()));
		}
		return true;
		
	}
	
	private int countEmpties(Society currentSociety) {
		int count = 0;
		for(Location loc : currentSociety.keySet()){
			if(currentSociety.get(loc).getState() == currentSociety.get(loc).getDefaultEmptyState()){
				count++;
			}
		}
		return count;
	}

	private boolean isSatisfied(Integer likeCount, Integer totalCount) {
		return (100 * likeCount > totalCount * satisfactionPercentage);
	}

	@Override
	public Manager copy() {
		return new Segregation();
	}
	
	public List<String> getParametersLabel(){
		return PARAMETERS;
	}

	@Override
	public void setParameters(Map<String, Double> data) {
		satisfactionPercentage = data.get(SATISFACTION_PARAMETER_LABEL);
		satisfactionPercentageBounds.set(1, satisfactionPercentage);
		createParametersBounds();
	}

	@Override
	public void createParametersBounds() {
		Map<String, List<Double>> parametersBounds = new HashMap<>();
		parametersBounds.put(SATISFACTION_PARAMETER_LABEL_GUI, satisfactionPercentageBounds);
		setParametersBounds(parametersBounds);
		
	}

	@Override
	public void updateParameter(String parameterLabel, double newValue) {
		if(parameterLabel.equals(SATISFACTION_PARAMETER_LABEL_GUI)){
			satisfactionPercentage = newValue;
		}
	}

	@Override
	public void setDefaultParameters() {
		satisfactionPercentage = (satisfactionPercentageBounds.get(0) + satisfactionPercentageBounds.get(2))/2;
	}
}
