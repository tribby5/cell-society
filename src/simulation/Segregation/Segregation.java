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

public class Segregation extends Manager {
	public static final String SATISFACTION_PARAMETER_LABEL = "par1";
	public static final String SATISFACTION_PARAMETER_LABEL_GUI = "Satisfaction Percentage";

	private Double satisfactionPercentage;

	private List<Double> satisfactionPercentageBounds = Arrays
			.asList(new Double[] { 0.0, satisfactionPercentage, 100.0 });;

	private static final List<String> PARAMETERS = Arrays.asList(new String[] { SATISFACTION_PARAMETER_LABEL });;

	private List<Cell> CELLS = Arrays.asList(new Cell[] { new Empty(), new O(), new X() });

	/**
	 * the list of cell types for manager to create instances of
	 */
	public List<Cell> getCellTypes() {
		return CELLS;
	}

	/**
	 * the update method first pulls out the current cell checks if this cell is
	 * satisfied if not, finds a random empty to swap with
	 */
	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell,
			List<Location> neighborsLoc, List<Integer> neighborCounts) {
		Cell currentCell = currentLocCell.getValue();
		if (!isSatisfied(neighborCounts.get(currentCell.getState()), neighborsLoc.size())) {
			int emptyCount = countEmpties(currentSociety);
			return (currentCell.swapWithRandomTarget(currentSociety, newSociety, currentLocCell.getKey(),
					currentSociety.keySet(), emptyCount, currentCell.getDefaultEmptyState()));
		}
		return true;

	}

	private int countEmpties(Society currentSociety) {
		int count = 0;
		for (Location loc : currentSociety.keySet()) {
			if (currentSociety.get(loc).getState() == currentSociety.get(loc).getDefaultEmptyState()) {
				count++;
			}
		}
		return count;
	}

	private boolean isSatisfied(Integer likeCount, Integer totalCount) {
		return (100 * likeCount > totalCount * satisfactionPercentage);
	}

	/**
	 * our copy method to ensure a new instance is created
	 */
	@Override
	public Manager copy() {
		return new Segregation();
	}

	/**
	 * gives the XML reader the parameter labels to look for in the xml file
	 */
	public List<String> getParametersLabel() {
		return PARAMETERS;
	}

	/**
	 * set the parameter values from the xml reader
	 */
	@Override
	public void setParameters(Map<String, Double> data) {
		satisfactionPercentage = data.get(SATISFACTION_PARAMETER_LABEL);
		satisfactionPercentageBounds.set(1, satisfactionPercentage);
		createParametersBounds();
	}

	/**
	 * creates the parameters to control the user input and sets them in the
	 * superclass of manager
	 */
	@Override
	public void createParametersBounds() {
		Map<String, List<Double>> parametersBounds = new HashMap<>();
		parametersBounds.put(SATISFACTION_PARAMETER_LABEL_GUI, satisfactionPercentageBounds);
		setParametersBounds(parametersBounds);

	}

	/**
	 * updates the parameter values from the user input
	 */
	@Override
	public void updateParameter(String parameterLabel, double newValue) {
		if (parameterLabel.equals(SATISFACTION_PARAMETER_LABEL_GUI)) {
			satisfactionPercentage = newValue;
		}
	}

	/**
	 * sets the default value for the dynamic parameters. used in the generator
	 * of custom simulations
	 */
	@Override
	public void setDefaultParameters() {
		satisfactionPercentage = (satisfactionPercentageBounds.get(0) + satisfactionPercentageBounds.get(2)) / 2;
	}

	/**
	 * returns an int that signifies which simulation this manager runs
	 */
	@Override
	public int getType() {
		return 3;
	}

	@Override
	public String getParameterValue(String par) {
		// TODO Auto-generated method stub
		return null;
	}
}
