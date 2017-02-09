package simulation.PredatorPrey;

import cellTypes.ThreeStateCell;
import javafx.scene.paint.Color;

public abstract class PredatorPreyCell extends ThreeStateCell{
	public static final int stateShark = 2;
	public static final int stateFish = 1;
	public static final int stateWater = 0;


	public PredatorPreyCell(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
		// TODO Auto-generated constructor stub
	}
	
	public static int getState_Shark(){
		return stateShark;
	}
	
	public static int getState_Fish(){
		return stateFish;
	}

	public static int getState_Water(){
		return stateWater;
	}

}
