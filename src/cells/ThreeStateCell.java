package cells;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

/**three state cells, used by most simulations
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public abstract class ThreeStateCell extends Cell {
	public static final int TOTAL_STATES = 3;
	
	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public ThreeStateCell(Color inputColor, int state, int priority) {
		super(inputColor, state, TOTAL_STATES, priority);
	}

	
}
