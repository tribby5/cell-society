package simulation.ForagingAnts;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class FoodSource extends ForagingAntsCell{
	public static final int state = getState_FoodSource();
	public static final int priority = getPriority_FoodSource();

	public static final Color color = Color.YELLOW; //TODO: color imports
	
	public FoodSource() {
		super(color, state, priority);
	}

	
	@Override
	public Cell copy() {
		return new FoodSource();
	}

}
