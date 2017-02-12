package simulation.ForagingAnts;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Patch extends ForagingAntsCell{
	public static final int state = getState_Patch();
	public static final int priority = getPriority_Patch();
	public static final Color colorEmpty = Color.GREEN; //TODO: color imports
	
	public Patch() {
		super(colorEmpty, state, priority);
	}
	
	@Override
	public Cell copy() {
		return new Patch();
	}

	public void updateColor() {
		double antCount = this.getAntCount();
		if (antCount != 0){
			this.setColor(Color.color(255, 200 - (10 * antCount), 150 - (15 * antCount)));
		}
		
	}
}
