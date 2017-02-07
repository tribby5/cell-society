package referees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Referee;
import simulation.Segregation.Segregation_EmptyCell;

public abstract class Locator extends Referee {

	private List<Cell> relocate;

	private List<Location> emptyPlaces;

	public Locator(boolean vertexneighbors, boolean torodialworld) {
		super(vertexneighbors, torodialworld);
	}

	@Override
	public void addChangers(Location loc, Cell currentCell, Cell updatedCell) {
		if(updatedCell instanceof Segregation_EmptyCell){
			emptyPlaces.add(loc);
			if(!(currentCell instanceof Segregation_EmptyCell))
				relocate.add(currentCell);
		}
	}

	@Override
	public void setRelocaters() {
		relocate = new ArrayList<>();
		emptyPlaces = new ArrayList<>();
	}
	
	@Override
	public void relocate() {
		for(Cell c: relocate){
			int place = (new Random()).nextInt(emptyPlaces.size());
			this.getGrid().put(emptyPlaces.get(place), c);
			emptyPlaces.remove(place);
		}
	}
}
