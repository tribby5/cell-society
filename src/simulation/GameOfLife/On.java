package simulation.GameOfLife;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class On extends GameOfLifeCell{
	
	public static final Color color = Color.BLACK; //TODO: color imports
	public static final int state = getState_On();

	public On() {
		super(color, state);
	}

	@Override
	public GameOfLifeCell act(List<Integer> neighborCount) {
		if (neighborCount.get(getState_On()) < 2 || neighborCount.get(getState_On()) > 3){
			return new Off();
		}
		return new On();
	}

	@Override
	public Cell copy() {
		return new On();
	}	
}
