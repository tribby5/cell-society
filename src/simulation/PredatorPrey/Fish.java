package simulation.PredatorPrey;

import java.util.List;
import java.util.Map;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public class Fish extends SeaAnimal{
	public static final Color color = Color.GREEN;
	public static final int priority = 1;
	public static final int TURNS_TO_REPRODUCTION = 4;
	public static final int state = getState_Fish();

	
	public Fish() {
		super(color, state, priority, TURNS_TO_REPRODUCTION);
	}

	public void act(Society currentSociety, Society newSociety, Location location, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		if(isAlive()){
			boolean moved = false;
			this.setTurns(this.getTurns() + 1);
			if (neighborCounts.get(getState_Water()) != 0){
				moved = swapWithRandomTarget(currentSociety, newSociety, location, neighborsLoc, neighborCounts.get(getState_Water()), getState_Water());
			}
			if (moved){
				tryToReproduce(newSociety, location, getState_Fish());
			}
		}
	}

	@Override
	public Cell copy() {
		return new Fish();
	}

}
