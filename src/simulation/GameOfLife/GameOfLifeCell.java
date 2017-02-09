package simulation.GameOfLife;

import cellTypes.TwoStateCell;
import javafx.scene.paint.Color;

public abstract class GameOfLifeCell extends TwoStateCell{
	
	public static final int stateOn = 1;
	public static final int stateOff = 0;
	public static final int priority = 0;


	public GameOfLifeCell(Color inputColor, int state) {
		super(inputColor, state, priority);
		// TODO Auto-generated constructor stub
	}
	
	public static int getState_On(){
		return stateOn;
	}
	
	public static int getState_Off(){
		return stateOff;
	}



}
