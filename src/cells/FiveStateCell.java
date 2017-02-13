package cells;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

/**five state cells, used by foragingantscell but not implemented unfortunately
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public abstract class FiveStateCell extends Cell {
	public static final int TOTAL_STATES = 5;
	
	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public FiveStateCell(Color inputColor, int state, int priority) {
		super(inputColor, state, TOTAL_STATES, priority);
	}

}
