package simulation.Segregation;

import cells.ThreeStateCell;
import cellsociety_team13.Location;
import cellsociety_team13.Society;
import javafx.scene.paint.Color;

public abstract class SegregationCell extends ThreeStateCell{
	public static final int stateX = 2;
	public static final int stateO = 1;
	public static final int stateEmpty = 0;
	public static final int priorityXO = 0;
	public static final int priorityEmpty = -1;

	public SegregationCell(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
	}

	public static int getState_X(){
		return stateX;
	}
	
	public static int getState_O(){
		return stateO;
	}
	public static int getState_Empty(){
		return stateEmpty;
	}
	
	public static int getPriority_X(){
		return priorityXO;
	}
	
	public static int getPriority_O(){
		return priorityXO;
	}
	
	public static int getPriority_Empty(){
		return priorityEmpty;
	}
	
	
	public int getDefaultEmptyState(){
		return getState_Empty();
	}
}
