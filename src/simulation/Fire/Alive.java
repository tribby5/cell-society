package simulation.Fire;

import java.util.List;
import java.util.Random;

import cellsociety_team13.Cell;
import javafx.scene.paint.Color;

/**
 * The Alive tree cell. It has a very simple rule for updating
 * 
 * @author Miguel Anderson (mra21)
 *
 */

public class Alive extends FireCell {
	public static final Color color = Color.GREEN; // TODO: color imports
	public static final int priority = getPriority_Alive();
	public static final int state = getState_Alive();

	/**
	 * basic constructor, sends preset values to the superclass
	 */
	public Alive() {
		super(color, state, priority);
	}

	/**
	 * the main method of the class, controls how the alive tree becomes burning
	 */
	@Override
	public FireCell act(List<Integer> neighborCount) {
		if (neighborCount.get(getState_Burning()) != 0) {
			Random rand = new Random();
			if (this.getProbCatch() > rand.nextDouble() * 100) {
				return new Burning();
			}
		}
		return this;
	}

	/**
	 * our copy cell method that creates a new instance
	 */
	@Override
	public Cell copy() {
		return new Alive();
	}

}
