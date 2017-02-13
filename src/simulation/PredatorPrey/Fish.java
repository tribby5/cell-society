package simulation.PredatorPrey;

import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

/**
 * The fish cell. Fishes move and reproduce only
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class Fish extends SeaAnimal{
	public static final Color color = Color.GREEN;
	public static final int state = getState_Fish();
	public static final int priority = getPriority_Fish();
	
	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Fish() {
		super(color, state, priority);
	}

	/**
	 * act method. first tries to move to an open water spot.
	 * if it does move, it tries to reproduce as well
	 * these methods live in the seaanimal cell
	 */
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

	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Fish();
	}

}
