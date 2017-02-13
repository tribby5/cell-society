package simulation.GameOfLife;

import java.util.List;

import cells.TwoStateCell;
import javafx.scene.paint.Color;

public abstract class GameOfLifeCell extends TwoStateCell{
	
	public static final int stateOn = 1;
	public static final int stateOff = 0;
	public static final int priority = 0;

	/**
	 * basic constructor, sends parameters from subclass to superclass
	 * 
	 * @param inputColor
	 *            : color
	 * @param state
	 * @param priority
	 */
	public GameOfLifeCell(Color inputColor, int state) {
		super(inputColor, state, priority);
	}
	
	/**
	 * 
	 * @return states of cells used to avoid all instanceof and
	 *         ensures no errors in casting
	 */
	public static int getState_On(){
		return stateOn;
	}
	
	public static int getState_Off(){
		return stateOff;
	}
	
	public int getDefaultEmptyState(){
		return getState_Off();
	}

	/**
	 * update cells, only by neighbor count. super simple
	 * @param neighborCount
	 * @return
	 */
	public GameOfLifeCell updateCell(List<Integer> neighborCount){
		return this.act(neighborCount);
	}
	
	/**
	 * action method. called by update method. stores actions of each cell
	 * @param neighborCount
	 * @return
	 */
	protected abstract GameOfLifeCell act(List<Integer> neighborCount);

}
