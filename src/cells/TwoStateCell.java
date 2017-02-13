package cells;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

/**two state cells, used only by game of life
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public abstract class TwoStateCell extends Cell{
	public static final int TOTAL_STATES = 2;
	
	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public TwoStateCell(Color inputColor, int state, int priority) {
		super(inputColor, state ,TOTAL_STATES, priority);
	}

}
