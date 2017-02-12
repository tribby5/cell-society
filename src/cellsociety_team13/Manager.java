package cellsociety_team13;

import java.util.List;
import java.util.Queue;

import javafx.util.Pair;

public abstract class Manager {
	private Society currentSociety;
//	private Map<Location, Cell> grid;

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
	
	
}
