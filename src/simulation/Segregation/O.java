package simulation.Segregation;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class O extends SegregationCell{
	public static final Color color = Color.BLUE;
	public static final int priority = getPriority_O();
	public static final int state = getState_O();
	
	public O() {
		super(color, state, priority);
	}


	@Override
	public Cell copy() {
		return new O();
	}


}
