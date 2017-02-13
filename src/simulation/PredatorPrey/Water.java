package simulation.PredatorPrey;

import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

/**
 * The water cell. It does not update at all
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class Water extends PredatorPreyCell{
	public static final Color color = Color.BLUE;
	public static final int priority = getPriority_Water();
	public static final int state = getState_Water();
	
	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Water() {
		super(color, state, priority);
	}

	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Water();
	}


	/**
	 * does nothing
	 */
	@Override
	public void act(Society currentSociety, Society newSociety, Location location, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		// does nothing
		
	}

}
