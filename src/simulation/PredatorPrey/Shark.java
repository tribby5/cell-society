package simulation.PredatorPrey;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public class Shark extends SeaAnimal{
	public static final Color color = Color.GREY;
	public static final int TURNS_TO_REPRODUCTION = 8;
	public static final int priority = 0;
	public static final int state = getState_Shark();
	private static final int FISH_NUTRITIONAL_VALUE = 2;
	
	private int energy;
	
	public Shark() {
		super(color, state, priority, TURNS_TO_REPRODUCTION);
		energy = 7;
	}
	
	public void act(Society currentSociety, Society newSociety, Location location, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		
		energy--;
		this.setTurns(this.getTurns() + 1);
		boolean hunted = false;
		boolean moved = false;
		if (neighborCounts.get(getState_Fish()) != 0){
			hunted = hunt(currentSociety, newSociety, location, neighborsLoc, neighborCounts.get(getState_Fish()));
		}
		
		if(hunted || energy == 0){
			newSociety.put(location, new Water());
			energy += FISH_NUTRITIONAL_VALUE;
		} else if (neighborCounts.get(getState_Water()) != 0){
			moved = move(currentSociety, newSociety, location, neighborsLoc, neighborCounts.get(getState_Water()));
		}
		
		if((hunted || moved)){
			tryToReproduce(newSociety, location, getState_Shark());
		}
		
		
	}

	private boolean hunt(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc,
			Integer fishCount) {
		return (swapWithRandomTarget(currentSociety, newSociety, loc, neighborsLoc, fishCount, getState_Fish()));
	}

	@Override
	public Cell copy() {
		return new Shark();
	}

}
