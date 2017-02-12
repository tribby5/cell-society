package simulation.ForagingAnts;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Nest extends ForagingAntsCell{
	public static final int state = getState_Nest();
	public static final int priority = getPriority_Nest();

	public static final Color color = Color.BROWN; //TODO: color imports
	
	public Nest() {
		super(color, state, priority);
	}

	
	@Override
	public Cell copy() {
		return new Nest();
	}

}
