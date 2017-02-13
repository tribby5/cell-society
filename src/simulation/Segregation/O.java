package simulation.Segregation;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

/**
 * The O cell. the updating rules do not live here so there is no code.
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class O extends SegregationCell{
	public static final Color color = Color.BLUE;
	public static final int priority = getPriority_O();
	public static final int state = getState_O();
	
	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public O() {
		super(color, state, priority);
	}


	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new O();
	}
}
