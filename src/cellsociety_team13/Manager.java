package cellsociety_team13;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import javafx.util.Pair;

public abstract class Manager {
	private Society currentSociety;
	private Map<String, List<Double>> parametersBounds;

	public void setSociety(Society society) {
		this.currentSociety = society;
	}

	public Society getSociety() {
		return currentSociety;
	}

	public void update() {
		Society newSociety = currentSociety.copy();
		currentSociety = updateSociety(newSociety);
	}

	public abstract List<Cell> getCellTypes();

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
	
	protected abstract boolean update(Society currentSociety, Society newSociety, Pair<Location, Cell> currentLocCell, List<Location> neighborsLoc, List<Integer> neighborCounts);
	
	public abstract Manager copy();

	public List<Location> pickNeighbors(Society soc, Location loc) {
		// TODO: Pick neighbors
		return soc.getVertexNeighbors(loc);
		// return soc.getSideNeighbors(loc);
	}
	
	public abstract List<String> getParametersLabel();
	
	public abstract void createParametersBounds();
	
	public abstract void updateParameter(String parameterLabel, double newValue);

	public abstract void setParameters(Map<String, Double> parameters);

	public void setParametersBounds(Map<String, List<Double>> parametersBounds) {
		this.parametersBounds = parametersBounds;
	}
	
	public Map<String, List<Double>> getParametersBounds(){
		return this.parametersBounds;
	}
}
