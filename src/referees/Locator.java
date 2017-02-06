package referees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Referee;
import cellsociety_team13.Society;
import simulation.Segregation.Segregation_EmptyCell;

public abstract class Locator extends Referee {

	private Map<Location, Cell> grid;

	private Set<Cell> relocate;

	private List<Location> emptyPlaces;

	@Override
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

	@Override
	public Cell manageLocation(Society soc, Location loc) {
		Cell currentCell = grid.get(loc);
		List<Cell> neighborList = soc.getSideNeighbors(loc);
		Cell updatedCell = judge(currentCell, neighborList);
		addChangers(loc, currentCell, updatedCell);
		return updatedCell;
	}

	private void addChangers(Location loc, Cell currentCell, Cell updatedCell) {
		if(updatedCell instanceof Segregation_EmptyCell){
			emptyPlaces.add(loc);
			if(!(currentCell instanceof Segregation_EmptyCell))
				relocate.add(currentCell);
		}
	}
	
	@Override
	public Map<Location, Cell> getGrid() {
		return grid;
	}

	private void setRelocaters() {
		relocate = new TreeSet<>();
		emptyPlaces = new ArrayList<>();
	}

	private void relocate() {
		for(Cell c: relocate){
			int place = (new Random()).nextInt(emptyPlaces.size());
			grid.put(emptyPlaces.get(place), c);
			emptyPlaces.remove(place);
		}
	}
}
