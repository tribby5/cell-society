package simulation.Fire;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

/**
 * The burning tree cell. It has a very simple rule for updating. It lives for
 * only one turn. It will return a dead tree when called.
 * 
 * @author Miguel Anderson (mra21)
 *
 */

public class Burning extends FireCell {
	public static final Color color = Color.RED; // TODO: color imports
	public static final int priority = getPriority_Burning();
	public static final int state = getState_Burning();

	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Burning() {
		super(color, state, priority);
	}

	/**
	 * only lives one turn, when "acting" it dies
	 */
	@Override
	public FireCell act(List<Integer> neighborCount) {
		return new Dead();
	}

	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Burning();
	}
}
