package cellsociety_team13;

import java.util.List;
import java.util.Queue;

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

	private Society updateSociety(Society newSociety) {		
		Queue<Location> toProcess = currentSociety.setProcessingOrder();

		while (!toProcess.isEmpty()) {
			Location currentLoc = toProcess.poll();
			List<Location> neighborsLoc = pickNeighbors(currentSociety, currentLoc);
			List<Integer> neighborCounts = currentSociety.countNeighbors(neighborsLoc);
			update(currentSociety, newSociety, currentLoc, neighborsLoc, neighborCounts);
		}
		return newSociety;
	}
	
	protected abstract void update(Society currentSociety, Society newSociety, Location currentLoc, List<Location> neighborsLoc, List<Integer> neighborCounts);

	public List<Location> pickNeighbors(Society soc, Location loc) {
		// TODO: Pick neighbors
		return soc.getVertexNeighbors(loc);
		// return soc.getSideNeighbors(loc);
	}

}
