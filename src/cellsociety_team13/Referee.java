package cellsociety_team13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Referee {
	
	private Map<Location, Cell> grid;
	private boolean torodialWorld;
	private boolean vertexNeighbor;

	public abstract List<Cell> getCellTypes();
	
	protected Referee(boolean vertexNeighborInput, boolean torodialWorldInput){
		this.torodialWorld = torodialWorldInput;
		this.vertexNeighbor = vertexNeighborInput;
	}

	public void giveSociety(Society soc) {
		Map<Location, Cell> newGrid = new HashMap<>();
		grid = soc.getGrid();
		setRelocaters();
		for (Location loc : grid.keySet()){
			Cell updatedCell = manageLocation(soc, loc); 
			newGrid.put(loc, updatedCell);
		}
		grid = newGrid;
		relocate();
	}

	public Map<Location, Cell> getGrid(){
		return grid;
	}
	
	public Cell manageLocation(Society soc, Location loc) {
		Cell currentCell = grid.get(loc);
		List<Cell> neighborList = pickNeighbors(soc, loc);
		Cell updatedCell = judge(currentCell, neighborList);
		addChangers(loc, currentCell, updatedCell);
		return updatedCell;
	}

	public List<Cell> pickNeighbors(Society soc, Location loc){
		if (isVertexNeighbor()){
			return soc.getVertexNeighbors(loc);
		}
		return soc.getSideNeighbors(loc);
	}
	
	public abstract Cell judge(Cell currentCell, List<Cell> neighborList);

	public abstract void addChangers(Location loc, Cell currentCell, Cell updatedCell);

	public abstract void relocate();

	public abstract void setRelocaters();

	public boolean isTorodialWorld(){
		return torodialWorld;
	}
	
	public boolean isVertexNeighbor() {
		return vertexNeighbor;
	}
}
