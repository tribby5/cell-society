package simulation.GameOfLife;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

public class Off extends GameOfLifeCell{
	public static final Color color = Color.WHITE; //TODO: color imports
	public static final int state = getState_Off();

	public Off() {
		super(color, state);
	}

	@Override
	public GameOfLifeCell act(List<Integer> neighborCount) {
		if (neighborCount.get(getState_On()) == 3){
			return new On();
		}
		return this;
	}

	@Override
	public Cell copy() {
		return new Off();
	}
	
	
}
