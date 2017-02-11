package simulation.GameOfLife;

import java.util.List;

import cells.TwoStateCell;
import javafx.scene.paint.Color;

public abstract class GameOfLifeCell extends TwoStateCell{
	
	public static final int stateOn = 1;
	public static final int stateOff = 0;
	public static final int priority = 0;


	public GameOfLifeCell(Color inputColor, int state) {
		super(inputColor, state, priority);
	}
	
	public static int getState_On(){
		return stateOn;
	}
	
	public static int getState_Off(){
		return stateOff;
	}

	public GameOfLifeCell updateCell(List<Integer> neighborCount){
		return this.act(neighborCount);
	}
	
	public int getDefaultEmptyState(){
		return getState_Off();
	}
	
	protected abstract GameOfLifeCell act(List<Integer> neighborCount);

}
