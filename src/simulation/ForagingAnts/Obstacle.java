package simulation.ForagingAnts;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Obstacle extends ForagingAntsCell{
	public static final int state = getState_Obstacle();
	public static final int priority = getPriority_Obstacle();
	
	public static final Color color = Color.BLACK; //TODO: color imports

	public Obstacle() {
		super(color, state, priority);
	}

	
	@Override
	public Cell copy() {
		return new Obstacle();
	}

}
