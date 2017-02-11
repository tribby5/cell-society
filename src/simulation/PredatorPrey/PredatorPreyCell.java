package simulation.PredatorPrey;

import java.util.List;

import cells.ThreeStateCell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public abstract class PredatorPreyCell extends ThreeStateCell{
	public static final int stateShark = 2;
	public static final int stateFish = 1;
	public static final int stateWater = 0;
	public static final int priorityShark = 0;
	public static final int priorityFish = 1;
	public static final int priorityWater = -1;


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
	
	public static int getPriority_Shark(){
		return priorityShark;
	}
	
	public static int getPriority_Fish(){
		return priorityFish;
	}
	
	public static int getPriority_Water(){
		return priorityWater;
	}
	
	public int getDefaultEmptyState(){
		return getState_Water();
	}
	
	public abstract void act(Society currentSociety, Society newSociety, Location location, List<Location> neighborsLoc,
			List<Integer> neighborCounts);

}
