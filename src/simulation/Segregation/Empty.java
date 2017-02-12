package simulation.Segregation;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Empty extends SegregationCell {
	public static final Color color = Color.GREY;
	public static final int priority = getPriority_Empty();
	public static final int state = getState_Empty();

	public Empty() {
		super(color, state, priority);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Cell copy() {
		return new Empty();
	}

}
