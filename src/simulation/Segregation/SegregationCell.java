package simulation.Segregation;

import cells.ThreeStateCell;
import javafx.scene.paint.Color;

public abstract class SegregationCell extends ThreeStateCell{
	public static final int stateX = 2;
	public static final int stateO = 1;
	public static final int stateEmpty = 0;
	public static final int priorityXO = 0;
	public static final int priorityEmpty = -1;

	/**
	 * basic constructor, sends parameters from subclass to superclass
	 * @param inputColor : color
	 * @param state
	 * @param priority
	 */
	public SegregationCell(Color inputColor, int state, int priority) {
		super(inputColor, state, priority);
	}

	/**
	 * 
	 * @return states and priorities of cells
	 * used to avoid all instanceof and ensures no errors in casting
	 */
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
