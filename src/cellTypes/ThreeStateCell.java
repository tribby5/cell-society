package cellTypes;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public abstract class ThreeStateCell extends Cell {
	public static final int TOTAL_STATES = 3;
	
	public ThreeStateCell(Color inputColor, int state) {
		super(inputColor, state, TOTAL_STATES);
		// TODO Auto-generated constructor stub
	}

	
}
