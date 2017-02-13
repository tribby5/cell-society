package simulation.PredatorPrey;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

public class PredatorPrey extends Manager {
	public static final String FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL = "par1";
	public static final String SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL = "par2";
	public static final String SHARK_INITIAL_ENERGY_PARAMETER_LABEL = "par3";
	public static final String FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI = "Number of turns for fish to reproduce";
	public static final String SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI = "Number of turns for shark to reproduce";
	public static final String SHARK_INITIAL_ENERGY_PARAMETER_LABEL_GUI = "Initial energy for the shark";

	private int fishTurnsToReproduce;
	private int sharkTurnsToReproduce;
	private int sharkInitialEnergy;

	private List<Double> fishTurnsToReproduceBounds = Arrays
			.asList(new Double[] { 1.0, (double) fishTurnsToReproduce, 10.0 });;

	private List<Double> sharkTurnsToReproduceBounds = Arrays
			.asList(new Double[] { 1.0, (double) sharkTurnsToReproduce, 20.0 });;

	private List<Double> sharkInitialEnergyBounds = Arrays
			.asList(new Double[] { 1.0, (double) sharkInitialEnergy, 20.0 });;

	private static final List<String> PARAMETERS = Arrays.asList(new String[] { FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL,
			SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL, SHARK_INITIAL_ENERGY_PARAMETER_LABEL });;

	private List<Cell> CELLS = Arrays.asList(new Cell[] { new Water(), new Fish(), new Shark() });

	/**
	 * the list of cell types for manager to create instances of
	 */
	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

	/**
	 * the update method first pulls out the current cell then operates on it
	 * this simulation is not in place. The sharks and fish can move into
	 * neighbor cells so the current cell can affect its neighbors
	 */
	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell,
			List<Location> neighborsLoc, List<Integer> neighborCounts) {
		PredatorPreyCell currentCell = (PredatorPreyCell) currentLocCell.getValue();

		// set parameters
		currentCell.setFishTurnsToReproduce(fishTurnsToReproduce);
		currentCell.setSharkInitialEnergy(sharkInitialEnergy);
		currentCell.setSharkTurnsToReproduce(sharkTurnsToReproduce);

		currentCell.act(currentSociety, newSociety, currentLocCell.getKey(), neighborsLoc, neighborCounts);
		return true;

	}

	/**
	 * return the parameter values. used by the cells
	 * 
	 * @return
	 */
	public int getFishTurnsToReproduce() {
		return fishTurnsToReproduce;
	}

	public int getSharkTurnsToReproduce() {
		return sharkTurnsToReproduce;
	}

	public int getSharkInitialEnergy() {
		return sharkInitialEnergy;
	}

	/**
	 * our copy method to ensure a new instance is created
	 */
	@Override
	public Manager copy() {
		return new PredatorPrey();
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
		fishTurnsToReproduce = data.get(FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL).intValue();
		sharkTurnsToReproduce = data.get(SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL).intValue();
		sharkInitialEnergy = data.get(SHARK_INITIAL_ENERGY_PARAMETER_LABEL).intValue();
		fishTurnsToReproduceBounds.set(1, (double) fishTurnsToReproduce);
		sharkTurnsToReproduceBounds.set(1, (double) sharkTurnsToReproduce);
		sharkInitialEnergyBounds.set(1, (double) sharkInitialEnergy);
		createParametersBounds();
	}

	/**
	 * creates the parameters to control the user input and sets them in the
	 * superclass of manager
	 */
	@Override
	public void createParametersBounds() {
		Map<String, List<Double>> parametersBounds = new HashMap<>();
		parametersBounds.put(FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI, fishTurnsToReproduceBounds);
		parametersBounds.put(SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI, sharkTurnsToReproduceBounds);
		parametersBounds.put(SHARK_INITIAL_ENERGY_PARAMETER_LABEL_GUI, sharkInitialEnergyBounds);
		setParametersBounds(parametersBounds);

	}

	/**
	 * updates the parameter values from the user input
	 */
	@Override
	public void updateParameter(String parameterLabel, double newValue) {
		if (parameterLabel.equals(FISH_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI)) {
			fishTurnsToReproduce = (int) newValue;
		} else if (parameterLabel.equals(SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI)) {
			sharkTurnsToReproduce = (int) newValue;
		} else if (parameterLabel.equals(SHARK_TURNS_TO_REPRODUCE_PARAMETER_LABEL_GUI)) {
			sharkInitialEnergy = (int) newValue;
		}
	}

	/**
	 * sets the default value for the dynamic parameters. used in the generator
	 * of custom simulations
	 */
	@Override
	public void setDefaultParameters() {
		fishTurnsToReproduce = 4;
		sharkTurnsToReproduce = 10;
		sharkInitialEnergy = 10;
	}

	/**
	 * returns an int that signifies which simulation this manager runs
	 */
	@Override
	public int getType() {
		return 2;
	}

	@Override
	public String getParameterValue(String par) {
		// TODO Auto-generated method stub
		return null;
	}
}
