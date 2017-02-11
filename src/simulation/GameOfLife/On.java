package simulation.GameOfLife;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class On extends GameOfLifeCell{
	
	public static final Color color = Color.BLACK; //TODO: color imports
	public static final int state = getState_On();
	public static final int LOWER_THRESHOLD = 2;
	public static final int HIGHER_THRESHOLD = 3;

	public On() {
		super(color, state);
	}

	@Override
	public GameOfLifeCell act(List<Integer> neighborCount) {
		if (neighborCount.get(getState_On()) < LOWER_THRESHOLD || neighborCount.get(getState_On()) > HIGHER_THRESHOLD){
			return new Off();
		}
		return new On();
	}

	@Override
	public Cell copy() {
		return new On();
	}	
}
