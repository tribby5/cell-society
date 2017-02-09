package cellsociety_team13;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class Manager {
	private Society mySociety;
//	private Map<Location, Cell> grid;

	public void setSociety(Society society) {
		this.mySociety = society;
	}

	public Society getSociety() {
		return mySociety;
	}

	public void update() {
		Map<Location, Cell> newGrid = updateGrid(mySociety);
		mySociety.pushNewGrid(newGrid);
	}

	public abstract List<Cell> getCellTypes();

	public Map<Location, Cell> updateGrid(Society soc) {
		Map<Location, Cell> oldGrid = soc.getGridCopy();
		
		Map<Location, Cell> newGrid = new HashMap<>();
		
		Queue<Location> toProcess = setProcessingOrder(oldGrid);

		while (!toProcess.isEmpty()) {

			Location currentLoc = toProcess.poll();
			List<Location> neighborsLoc = pickNeighbors(soc, currentLoc);
			Cell updatedCell = update(oldGrid, currentLoc, neighborsLoc);
			newGrid.put(currentLoc, updatedCell);
		}

		return newGrid;
	}

	private Queue<Location> setProcessingOrder(Map<Location, Cell> grid) {
		PriorityQueue<Cell> qCells = new PriorityQueue<Cell>();
		Queue<Location> qLocs = new LinkedList<Location>();
		qCells.addAll(grid.values());
		while (!qCells.isEmpty()) {
			qLocs.add(getKeyFromValue(grid, qCells.poll()));
		}
		return qLocs;
	}

	private Location getKeyFromValue(Map<Location, Cell> map, Cell value) {
		for (Location key : map.keySet()) {
			if (map.get(key) == value) {
				return key;
			}
		}
		return null;
	}

	public List<Location> pickNeighbors(Society soc, Location loc) {
		// TODO: Pick neighbors
		return soc.getVertexNeighbors(loc);
		// return soc.getSideNeighbors(loc);
	}

	private Cell update(Map<Location, Cell> gridIn, Location loc, List<Location> neighborsLoc) {
		List<Integer> neighborCount = countNeighbors(gridIn, neighborsLoc);
		return gridIn.get(loc).updateCell(gridIn, neighborsLoc, neighborCount);

	}

	private List<Integer> countNeighbors(Map<Location, Cell> gridIn, List<Location> neighborsLoc) {
		Integer[] neighborCount = Collections.nCopies(gridIn.get(neighborsLoc.get(0)).getMaxState(), 0)
				.toArray(new Integer[0]);
		// from
		// http://stackoverflow.com/questions/2154251/any-shortcut-to-initialize-all-array-elements-to-zero/2154340
		for (Location neighborLoc : neighborsLoc) {
			neighborCount[gridIn.get(neighborLoc).getState()]++;
		}
		return Arrays.asList(neighborCount);

	}

}
