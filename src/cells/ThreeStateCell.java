package cells;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public abstract class ThreeStateCell extends Cell {
	public static final int TOTAL_STATES = 3;
	
	public ThreeStateCell(Color inputColor, int state, int priority) {
		super(inputColor, state, TOTAL_STATES, priority);
		// TODO Auto-generated constructor stub
	}

	
}
