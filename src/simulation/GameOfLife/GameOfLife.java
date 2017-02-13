package simulation.GameOfLife;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Manager;
import cellsociety_team13.Society;
import javafx.util.Pair;

/**
 * Manager for the game of life simulation
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class GameOfLife extends Manager {

	private static final List<String> PARAMETERS = Arrays.asList(new String[] {});;

	private List<Cell> CELLS = Arrays.asList(new Cell[] { new Off(), new On() });

	/**
	 * the list of cell types for manager to create instances of
	 */
	@Override
	public List<Cell> getCellTypes() {
		return CELLS;
	}

	/**
	 * the update method first pulls out the current cell it is very simple
	 * because updates are in place. the new cell is only a result of neighbor
	 * cells
	 */
	@Override
	protected boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell,
			List<Location> neighborsLoc, List<Integer> neighborCounts) {
		GameOfLifeCell currentCell = (GameOfLifeCell) currentLocCell.getValue();
		GameOfLifeCell updatedCell = currentCell.updateCell(neighborCounts);
		newSociety.put(currentLocCell.getKey(), updatedCell);
		return true;
	}

	/**
	 * our copy method to ensure a new instance is created
	 */
	@Override
	public Manager copy() {
		return new GameOfLife();
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
		// no parameters
	}

	/**
	 * creates the parameters to control the user input and sets them in the
	 * superclass of manager
	 */
	@Override
	public void createParametersBounds() {
		setParametersBounds(null);
	}

	/**
	 * updates the parameter values from the user input
	 */
	@Override
	public void updateParameter(String parameterLabel, double newValue) {
		// no parameters
	}

	/**
	 * sets the default value for the dynamic parameters. used in the generator
	 * of custom simulations
	 */
	@Override
	public void setDefaultParameters() {
		// no parameters
	}

	/**
	 * returns an int that signifies which simulation this manager runs
	 */
	@Override
	public int getType() {
		return 0;
	}

	@Override
	public String getParameterValue(String par) {
		// TODO Auto-generated method stub
		return null;
	}
}
