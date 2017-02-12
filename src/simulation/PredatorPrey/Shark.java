package simulation.PredatorPrey;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public class Shark extends SeaAnimal{
	public static final Color color = Color.GREY;
	public static final int priority = getPriority_Shark();
	public static final int state = getState_Shark();
	
	public static final int TURNS_TO_REPRODUCTION = 10;
	public static final int INITIAL_ENERGY = 5;
	private static final int FISH_NUTRITIONAL_VALUE = 2;
	
	private int energy;
	
	public Shark() {
		super(color, state, priority, TURNS_TO_REPRODUCTION);
		energy = INITIAL_ENERGY;
	}
	
	public void act(Society currentSociety, Society newSociety, Location location, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		if(isAlive()){
			energy--;
			this.setTurns(this.getTurns() + 1);
			boolean hunted = false;
			boolean moved = false;
			if (neighborCounts.get(getState_Fish()) != 0){
				hunted = swapWithRandomTarget(currentSociety, newSociety, location, neighborsLoc, neighborCounts.get(getState_Fish()), getState_Fish());
			}
			
			if(hunted || energy == 0){
				((SeaAnimal) newSociety.get(location)).dies();
				newSociety.put(location, new Water());
				energy += FISH_NUTRITIONAL_VALUE;
			} else if (neighborCounts.get(getState_Water()) != 0){
				moved = swapWithRandomTarget(currentSociety, newSociety, location, neighborsLoc, neighborCounts.get(getState_Water()), getState_Water());
			}
			
			if(hunted || moved){
				tryToReproduce(newSociety, location, getState_Shark());
			}
		}	
	}

	@Override
	public Cell copy() {
		return new Shark();
	}

}
