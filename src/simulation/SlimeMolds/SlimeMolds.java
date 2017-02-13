package simulation.SlimeMolds;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

public class SlimeMolds extends Manager {
	public static final String SNIFF_ANGLE_PARAMETER_LABEL = "par1";
	public static final String EVAPORATION_RATE_PARAMETER_LABEL = "par2";
	public static final String DIFFUSION_RATE_PARAMETER_LABEL = "par3";
	public static final String SNIFF_ANGLE_PARAMETER_LABEL_GUI = "Max Sniff Angle";
	public static final String EVAPORATION_RATE_PARAMETER_LABEL_GUI = "Evaporation Rate";
	public static final String DIFFUSION_RATE_PARAMETER_LABEL_GUI = "Diffusion Rate";

	private double sniffAngle;
	private double evaporationRate;
	private double diffusionRate;

	private List<Double> sniffAngleBounds = Arrays.asList(new Double[] { 0.0, sniffAngle, 360.0 });;
	private List<Double> evaporationRateBounds = Arrays.asList(new Double[] { 0.0, evaporationRate, 0.5 });;
	private List<Double> diffusionRateBounds = Arrays.asList(new Double[] { 0.0, diffusionRate, 0.1 });;

	private static final List<String> PARAMETERS = Arrays.asList(new String[] { SNIFF_ANGLE_PARAMETER_LABEL,
			EVAPORATION_RATE_PARAMETER_LABEL, DIFFUSION_RATE_PARAMETER_LABEL });;

	private List<Cell> CELLS = Arrays.asList(new Cell[] { new Empty(), new Patch(), new Slime() });

	/**
	 * the list of cell types for manager to create instances of
	 */
	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

	/**
	 * the update method. updates parameters and then sends the info to the
	 * cells
	 */
	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell,
			List<Location> neighborsLoc, List<Integer> neighborCounts) {
		SlimeMoldsCell currentCell = (SlimeMoldsCell) currentLocCell.getValue();

		currentCell.setDiffusion(diffusionRate);
		currentCell.setSniff_angle_max(sniffAngle);
		currentCell.setEvaporation_rate(evaporationRate);

		currentCell.update(currentSociety, newSociety, currentLocCell.getKey(), neighborsLoc, neighborCounts);
		return true;
	}

	/**
	 * our copy method to ensure a new instance is created
	 */
	public Manager copy() {
		return new SlimeMolds();
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
		sniffAngle = data.get(SNIFF_ANGLE_PARAMETER_LABEL);
		evaporationRate = data.get(EVAPORATION_RATE_PARAMETER_LABEL);
		diffusionRate = data.get(DIFFUSION_RATE_PARAMETER_LABEL);
		sniffAngleBounds.set(1, sniffAngle);
		evaporationRateBounds.set(1, evaporationRate);
		diffusionRateBounds.set(1, diffusionRate);

		createParametersBounds();
	}

	/**
	 * creates the parameters to control the user input and sets them in the
	 * superclass of manager
	 */
	@Override
	public void createParametersBounds() {
		Map<String, List<Double>> parametersBounds = new HashMap<>();
		parametersBounds.put(SNIFF_ANGLE_PARAMETER_LABEL_GUI, sniffAngleBounds);
		parametersBounds.put(EVAPORATION_RATE_PARAMETER_LABEL_GUI, evaporationRateBounds);
		parametersBounds.put(DIFFUSION_RATE_PARAMETER_LABEL_GUI, diffusionRateBounds);
		setParametersBounds(parametersBounds);

	}

	/**
	 * updates the parameter values from the user input
	 */
	@Override
	public void updateParameter(String parameterLabel, double newValue) {
		if (parameterLabel.equals(SNIFF_ANGLE_PARAMETER_LABEL_GUI)) {
			sniffAngle = newValue;
		} else if (parameterLabel.equals(EVAPORATION_RATE_PARAMETER_LABEL_GUI)) {
			evaporationRate = newValue;
		} else if (parameterLabel.equals(DIFFUSION_RATE_PARAMETER_LABEL_GUI)) {
			diffusionRate = newValue;
		}
	}

	/**
	 * sets the default value for the dynamic parameters. used in the generator
	 * of custom simulations
	 */
	@Override
	public void setDefaultParameters() {
		sniffAngle = (sniffAngleBounds.get(0) + sniffAngleBounds.get(2)) / 2;
		evaporationRate = (evaporationRateBounds.get(0) + evaporationRateBounds.get(2)) / 2;
		diffusionRate = (diffusionRateBounds.get(0) + diffusionRateBounds.get(2)) / 2;
	}

	/**
	 * returns an int that signifies which simulation this manager runs
	 */
	@Override
	public int getType() {
		return 4;
	}

	@Override
	public String getParameterValue(String par) {
		// TODO Auto-generated method stub
		return null;

	}
}
