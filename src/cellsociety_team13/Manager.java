package cellsociety_team13;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import javafx.util.Pair;

/**
 * Abstract class which forms the basic of a Manager which is resopnsible for holding a Society
 * instance and updating its components based on rules. The actual rules are implemented on
 * extensions of Manager, allowing for flexibility for multiple simulation types.
 * Uses: Extend Manager to create the Manager instance to allow for the implementation of
 * another kind of simulation. Needed to update the components over time
 * @author Miguel Anderson (mra21), Andres Lebbos (afl13), Matthew Tribby (mrt28)
 */
public abstract class Manager {
	private Society currentSociety;
	private Map<String, List<Double>> parametersBounds;
	
	/**
	 * Sets the Society for the instance variable
	 * @param society
	 */
	public void setSociety(Society society) {
		this.currentSociety = society;
	}

	/**
	 * Returns the Society instance of the class
	 * @return society
	 */
	public Society getSociety() {
		return currentSociety;
	}

	/**
	 * Called to update the Society based on the rules that are implemented.
	 * Uses: Call when want to step to the next part of the simulation.
	 */
	public void update() {
		Society newSociety = currentSociety.copy();
		currentSociety = updateSociety(newSociety);
	}

	/**
	 * Return the cell types of the given simulation. Abstract because it depends on 
	 * the simulation
	 * @return List of the Cell instances of the simulation
	 */
	public abstract List<Cell> getCellTypes();

	/**
	 * Updates society based on the current society and neighbors. Goes through all the Locations
	 * and updates their values based on the rules which are implemented in the extensions of
	 * Managers
	 * @param newSociety Society which will be updated
	 * @return Society updated
	 */
	public Society updateSociety(Society newSociety) {		
		Queue<Location> toProcess = currentSociety.setProcessingOrder();

		while (!toProcess.isEmpty()) {
			Location currentLoc = toProcess.poll();
			Pair<Location, Cell> currentLocCell = new Pair<>(currentLoc, currentSociety.get(currentLoc));
			List<Location> neighborsLoc = pickNeighbors(currentSociety, currentLoc);
			List<Integer> neighborCounts = currentSociety.countNeighbors(neighborsLoc);
			if(!update(currentSociety, newSociety, currentLocCell, neighborsLoc, neighborCounts)){
				break;
			}
		}
		return newSociety;
	}
	
	/**
	 * Updates current Location Cell paired based on the neighbors and the rules of a
	 * extension of Manager
	 * @param currentSociety old society
	 * @param newSociety society which will be next displayed
	 * @param currentLocCell Location Cell pair to be evaluated
	 * @param neighborsLoc List of Neighbor locations
	 * @param neighborCounts List of Neighbor Counts
	 * @return
	 */
	protected abstract boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell, List<Location> neighborsLoc, List<Integer> neighborCounts);
	
	/**
	 * Necessary to create different unique instances of a Manager
	 * @return new Instance of Manager
	 */
	public abstract Manager copy();

	public List<Location> pickNeighbors(Society soc, Location loc) {
		// TODO: Pick neighbors
		return soc.getVertexNeighbors(loc);
		// return soc.getSideNeighbors(loc);
	}
	
	/**
	 * Returns labels for all the parameters of a given simulation type
	 * @return List of parameter labels
	 */
	public abstract List<String> getParametersLabel();
	
	/**
	 * Creates the bounds for parameters. Bounds means reasonable minimum and maximum
	 * values for the parameters which are intended to be  displayed when giving the user 
	 * the choice of affecting parameter values
	 */
	public abstract void createParametersBounds();
	
	/**
	 * Update a given parameter for a simulation
	 * @param parameterLabel String name of the parameter
	 * @param newValue Updated value for the parameter, double
	 */
	public abstract void updateParameter(String parameterLabel, double newValue);

	/**
	 * Initial setting of parameters for a simulation
	 * @param parameters Map of parameter names and values
	 */
	public abstract void setParameters(Map<String, Double> parameters);

	/**
	 * Sets a given Map of parameter bounds to the instance variable of Manager
	 * @param parametersBounds Map of String and List of doubles that will set the parameter
	 * bounds. The List of doubles should be implemented as follows: first number: Minimum
	 * value, second number: Starting value, third number: Maximum value
	 */
	public void setParametersBounds(Map<String, List<Double>> parametersBounds) {
		this.parametersBounds = parametersBounds;
	}
	
	/**
	 * Returns the Map of parameters defined by their name and initial, starting, and maximum
	 * values
	 * @return map which defines parameters and their bounds
	 */
	public Map<String, List<Double>> getParametersBounds(){
		return this.parametersBounds;
	}
	
	public abstract void setDefaultParameters();

	public abstract int getType();

	public abstract String getParameterValue(String par);
	
}
