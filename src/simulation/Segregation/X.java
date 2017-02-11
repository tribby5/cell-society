package simulation.Segregation;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class X extends SegregationCell{
	public static final Color color = Color.RED;
	public static final int priority = getPriority_O();
	public static final int state = getState_X();
	
	public X() {
		super(color, state, priority);
	}

	@Override
	public Cell copy() {
		return new X();
	}



}
