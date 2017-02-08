package simulation.Fire;

import cellTypes.ThreeStateCell;
import javafx.scene.paint.Color;

public abstract class FireCell extends ThreeStateCell{
	public static final int stateAlive = 2;
	public static final int stateBurning = 1;
	public static final int stateDead = 0;
	
	public FireCell(Color inputColor, int state) {
		super(inputColor, state);
		// TODO Auto-generated constructor stub
	}
	
	public static int getState_Alive(){
		return stateAlive;
	}
	
	public static int getState_Burning(){
		return stateBurning;
	}
	
	public static int getState_Dead(){
		return stateDead;
	}
}
