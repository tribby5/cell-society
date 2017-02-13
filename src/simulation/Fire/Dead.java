package simulation.Fire;

import java.util.List;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

/**
 * The dead tree cell. It does nothing.
 * 
 * @author Miguel Anderson (mra21)
 *
 */
public class Dead extends FireCell{
	public static final Color color = Color.BLACK; //TODO: color imports
	public static final int priority = getPriority_Dead();
	public static final int state = getState_Dead();
	
	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Dead() {
		super(color, state, priority);
	}

	/**
	 * does nothing on each turn
	 */
	@Override
	public FireCell act(List<Integer> neighborCount){
		return this;
	}

	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Dead();
	}

}
