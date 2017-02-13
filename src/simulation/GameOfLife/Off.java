package simulation.GameOfLife;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

/**
 * The off cell. It has a very simple rule for updating
 * 
 * @author Miguel Anderson (mra21)
 *
 */

public class Off extends GameOfLifeCell{
	public static final Color color = Color.WHITE;
	public static final int state = getState_Off();
	public static final int NEIGHBOR_THRESHOLD = 3;

	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Off() {
		super(color, state);
	}

	/**
	 * act method
	 * checks counts of neighbors
	 * returns by rules
	 */
	@Override
	public GameOfLifeCell act(List<Integer> neighborCount) {
		if (neighborCount.get(getState_On()) == NEIGHBOR_THRESHOLD){
			return new On();
		}
		return this;
	}

	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Off();
	}
	
	
}
