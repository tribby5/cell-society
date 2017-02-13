package cells;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

/**four state cells, not used but shown as a sign of the generality able in this program
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public abstract class FourStateCell extends Cell{
	public static final int TOTAL_STATES = 4;
	
	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public FourStateCell(Color inputColor, int state, int priority) {
		super(inputColor, state, TOTAL_STATES, priority);
	}

}
