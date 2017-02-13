package simulation.Segregation;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

/**
 * The empty cell. It does not update
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class Empty extends SegregationCell {
	public static final Color color = Color.GREY;
	public static final int priority = getPriority_Empty();
	public static final int state = getState_Empty();

	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Empty() {
		super(color, state, priority);
	}

	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Empty();
	}

}
