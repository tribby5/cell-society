package simulation.Fire;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

/**
 * Manager for the Fire simulation
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class Fire extends Manager {
	public static final String PROBCATCH_PARAMETER_LABEL = "par1";
	public static final String PROBCATCH_PARAMETER_LABEL_GUI = "Probability of Catching Fire";
	private double probCatch;
	private List<Double> probCatchBounds = Arrays.asList(new Double[] { 0.0, probCatch, 100.0 });;

	private static final List<String> PARAMETERS = Arrays.asList(new String[] { PROBCATCH_PARAMETER_LABEL });;

	private List<Cell> CELLS = Arrays.asList(new Cell[] { new Dead(), new Burning(), new Alive() });

	/**
	 * gives probability catch to the cells that will use it
	 * 
	 * @return
	 */
	public double getProbCatch() {
		return probCatch;
	}

	/**
	 * the list of cell types for manager to create instances of
	 */
	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

	/**
	 * the update method first pulls out the current cell then operates on it
	 * this simulation is in place so there is no need to change any cell but
	 * the current cell on a given turn
	 */
	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell,
			List<Location> neighborsLoc, List<Integer> neighborCounts) {

		FireCell currentCell = (FireCell) currentLocCell.getValue();

		// set parameters
		currentCell.setProbCatch(probCatch);

		FireCell updatedCell = currentCell.updateCell(neighborCounts);
		newSociety.put(currentLocCell.getKey(), updatedCell);

		return true;
	}

	/**
	 * our copy method to ensure a new instance is created
	 */
	@Override
	public Manager copy() {
		return new Fire();
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
		probCatch = data.get(PROBCATCH_PARAMETER_LABEL);
		probCatchBounds.set(1, probCatch);
		createParametersBounds();
	}

	/**
	 * updates the parameter values from the user input
	 */
	@Override
	public void updateParameter(String parameterLabel, double newValue) {
		if (parameterLabel.equals(PROBCATCH_PARAMETER_LABEL_GUI)) {
			probCatch = newValue;
		}

	}

	/**
	 * creates the parameters to control the user input and sets them in the
	 * superclass of manager
	 */
	@Override
	public void createParametersBounds() {
		Map<String, List<Double>> parametersBounds = new HashMap<String, List<Double>>();
		parametersBounds.put(PROBCATCH_PARAMETER_LABEL_GUI, probCatchBounds);
		setParametersBounds(parametersBounds);
	}

	/**
	 * sets the default value for the dynamic parameters. used in the generator
	 * of custom simulations
	 */
	@Override
	public void setDefaultParameters() {
		probCatch = (probCatchBounds.get(0) + probCatchBounds.get(2)) / 2;
	}

	/**
	 * returns an int that signifies which simulation this manager runs
	 */
	@Override
	public int getType() {
		return 1;
	}

	/**
	 * returns the
	 */
	@Override
	public String getParameterValue(String par) {
		// TODO Auto-generated method stub
		return null;
	}
}
