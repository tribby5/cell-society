package simulation.ForagingAnts;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Patch extends ForagingAntsCell{
	public static final int state = getState_Patch();
	public static final int priority = getPriority_Patch();
	public static final Color color = Color.GREEN; //TODO: color imports
	


	
	public Patch() {
		super(color, state, priority);
	}
	
	public Patch(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
	}

	@Override
	public Cell copy() {
		return new Patch();
	}
}
