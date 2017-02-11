package simulation.Fire;

import java.util.List;

import cells.ThreeStateCell;
import javafx.scene.paint.Color;

public abstract class FireCell extends ThreeStateCell{
	public static final int stateAlive = 2;
	public static final int stateBurning = 1;
	public static final int stateDead = 0;
	
	public FireCell(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
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
	
	public int getDefaultEmptyState(){
		return getState_Dead();
	}
	
	public FireCell updateCell(List<Integer> neighborCount){
		return this.act(neighborCount);
	}
	
	protected abstract FireCell act(List<Integer> neighborCount);
}
