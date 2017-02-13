package simulation.SlimeMolds;

import java.util.List;

import cellsociety_team13.Cell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

/**
 * The empty cell. It does not update
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class Empty extends SlimeMoldsCell{
	public static final Color color = Color.BLACK;
	public static final int priority = getPriority_Empty();
	public static final int state = getState_Empty();

	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Empty() {
		super(color, state, priority);
		setChemical_deposit_count(0);
	}

	/**
	 * act method, does nothing
	 */
	@Override
	public void act(Society currentSociety, Society newSociety, Location loc, List<Location> neighborsLoc,
			List<Integer> neighborCounts) {
		// do nothing
		
	}
	
	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Empty();
	}
}
